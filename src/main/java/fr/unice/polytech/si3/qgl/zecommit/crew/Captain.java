package fr.unice.polytech.si3.qgl.zecommit.crew;

import fr.unice.polytech.si3.qgl.zecommit.Game;
import fr.unice.polytech.si3.qgl.zecommit.Logs;
import fr.unice.polytech.si3.qgl.zecommit.boat.Deck;
import fr.unice.polytech.si3.qgl.zecommit.boat.Ship;
import fr.unice.polytech.si3.qgl.zecommit.entite.Entity;
import fr.unice.polytech.si3.qgl.zecommit.entite.Oar;
import fr.unice.polytech.si3.qgl.zecommit.entite.Rudder;
import fr.unice.polytech.si3.qgl.zecommit.entite.Sail;
import fr.unice.polytech.si3.qgl.zecommit.goal.Goal;
import fr.unice.polytech.si3.qgl.zecommit.goal.Regatta;
import fr.unice.polytech.si3.qgl.zecommit.maths.Compo;
import fr.unice.polytech.si3.qgl.zecommit.maths.OrientationTable;
import fr.unice.polytech.si3.qgl.zecommit.maths.Predictions;
import fr.unice.polytech.si3.qgl.zecommit.maths.Road;
import fr.unice.polytech.si3.qgl.zecommit.other.Reef;
import fr.unice.polytech.si3.qgl.zecommit.other.VisibleEntitie;
import fr.unice.polytech.si3.qgl.zecommit.other.Wind;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Captain implements CaptainInterface {

    private Ship ship;
    private Regatta goal;
    private OrientationTable orientationTable;
    private Wind wind;
    private List<VisibleEntitie> visibleEntities;
    private boolean needToSlowDown;
    private int chosenAngle;
    private double orientationToGoal;
    private int chosenAngleAlteration;

    public Captain(Game game) {
        this.ship = game.getShip();
        this.goal = (Regatta) game.getGoal();
        this.orientationTable = new OrientationTable(ship.getDeck().getOars().size());
        this.wind = game.getWind();
        this.visibleEntities = game.getVisibleEntities();
        this.needToSlowDown = false;
    }

    @Override
    public void attributeEntitiesToSailors() {
        List<Sailor> sailors = new ArrayList<>(ship.getDeckSailors());
        List<Entity> oars = new ArrayList<>(ship.getDeckOars());
        List<Sail> sails = new ArrayList<>(ship.getDeckSails());
        Optional<Rudder> rudder = ship.getDeckRudder();
        sailors.sort(Comparator.comparingInt(a -> a.distanceToNearestEntity(oars)));
        if (sailors.size() > 4 && rudder.isPresent()) {
            sailors.remove(sailors.size() - 1).setOnEntity(rudder.get());
        }
        for (int i = 0; i < oars.size(); i++) {
            Oar oar = (Oar)oars.get(i);
            Optional<Sailor> closestSailor = sailors.stream().min(Comparator.comparingInt(a -> a.distanceToEntity(oar)));
            if (closestSailor.isPresent() && !closestSailor.get().hasEntity()) {
                closestSailor.get().setOnEntity(oar);
                oars.remove(oar);
                sailors.remove(closestSailor.get());
                i--;
            }
        }
        if (!sailors.isEmpty() && !sails.isEmpty()) {
            for (Sailor sailor : sailors) {
                Optional<Sail> sail = sails.stream().min(Comparator.comparingInt(a -> sailor.distanceToEntity(a)));
                if (sail.isPresent() && !sail.get().hasSailorOn()) {
                    sailor.setOnEntity(sail.get());
                    sails.remove(sail.get());
                }
            }
        }
    }

    @Override
    public List<Sailor> doMoveSailors() {
        if (!ship.getDeck().sailorsAreOnTheirEntity())
            return ship.getDeckSailors();
        return new ArrayList<>();
    }

    @Override
    public List<Sailor> doActivateOars() {
        if (ship.isInCheckpoint(goal.getFirstCheckpoint()) && goal.getCheckpoints().size() > 1) {
            goal.validateCommonCheckpoint();
            Logs.add("Checkpoint done");
        }
        needToSlowDown = false;
        Road road = new Road(ship.getPosition(), goal.getFirstCheckpoint().getPosition());
        orientationToGoal = road.getOrientation();
        chosenAngle = road.findClosestPossibleAngle(ship.getDeckOars().size(),ship.getDeck().canUseRudder());
        return decisionOrientation(road);
    }

    @Override
    public SimpleEntry<Sailor, Double> doTurn() {
        Road road = new Road(ship.getPosition(), goal.getFirstCheckpoint().getPosition());
        Optional<Rudder> res = ship.getDeckRudder();
        double angle = road.orientationToGoal()
                - orientationTable.getAngleTable().get(road.findClosestPossibleAngle(ship.getDeck().getOars().size(), ship.getDeck().canUseRudder()));

        if (res.isPresent() && ship.getDeck().canUseRudder()) {
            if (needToSlowDown && chosenAngleAlteration > 0)
                angle = -Math.PI / 4;
            if (needToSlowDown && chosenAngleAlteration < 0)
                angle = Math.PI / 4;
            if (chosenAngle == 0)
                angle = 0;

            return new SimpleEntry<>(res.get().getSailorOn(), angle);
        }
        return null;
    }

    @Override
    public List<Sailor> doLiftSail() {
        if (upSail()) {
            return ship.getDeckSails().stream().filter(sail -> !sail.isOpenned() && sail.hasSailorOn() && sail.getSailorOn().isOnEntity()).map(sail -> sail.getSailorOn()).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    @Override
    public List<Sailor> doLowerSail() {
        if (!upSail()) {
            return ship.getDeckSails().stream().filter(sail -> sail.isOpenned() && sail.hasSailorOn() && sail.getSailorOn().isOnEntity()).map(sail -> sail.getSailorOn()).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    @Override
    public boolean pursueGame() {
        return !(ship.isInCheckpoint(goal.getCheckpoints().get(goal.getCheckpoints().size() - 1))
                && goal.getCheckpoints().size() == 1);
    }

    /**
     * Effectue l'ordre d'activation des marins aux rames et au gouvernail
     *
     * @param compo
     */
    public List<Sailor> activateSailors(Compo compo) {
        ArrayList<Sailor> usedSailors = new ArrayList<>();
        // Activation des marins de gauche
        int l = 0;
        while (l < compo.getSailorsLeft()) {
            usedSailors.add(ship.getDeck().getLeftSailors().get(l));
            l++;
        }

        // Activation des marins de droite
        int r = 0;
        while (r < compo.getSailorsRight()) {
            usedSailors.add(ship.getDeck().getRightSailors().get(r));
            r++;
        }
        return usedSailors;
    }

    private List<Sailor> decisionOrientation(Road road) {
        chosenAngleAlteration = 0;

        boolean isNear = road.distanceToGoal() < (165 - goal.getFirstCheckpoint().getCircleRadius());
        List<Sailor> rightSailorList = ship.getDeck().getUsedOars().stream().filter(oar -> !ship.getDeck().isLeft(oar))
                .map(oar -> oar.getSailorOn()).collect(Collectors.toList());
        for (Sailor sailor : rightSailorList) {
            ship.getDeck().addSailor(sailor);
        }
        List<Sailor> leftSailorList = ship.getDeck().getUsedOars().stream().filter(oar -> ship.getDeck().isLeft(oar))
                .map(oar -> oar.getSailorOn()).collect(Collectors.toList());
        for (Sailor sailor : leftSailorList) {
            ship.getDeck().addSailor(sailor);
        }

        int nbSailorsRight = ship.getDeck().getNumberRightSailors();
        int nbSailorsLeft = ship.getDeck().getNumberLeftSailors();

        Predictions predictions = new Predictions(leftSailorList, rightSailorList, ship, visibleEntities, chosenAngle, wind);
        if (predictions.checkCollision() || predictions.checkFutureCollision() || predictions.verify()) {
            Logs.add("Votre Capitaine a detecté un iceberg et tente de l'éviter");
            needToSlowDown = true;
            chosenAngleAlteration += chosenAngle;
            Optional<Reef> reefOp = predictions.getFirstReef();
            if(reefOp.isPresent()) {
                Reef reef = reefOp.get();
                if (road.orientationToGoal() <= predictions.getAngleToCenterOfReef(reef)) {
                    orientationToGoal = ship.getPosition().getOrientation() + predictions.getAngleToCenterOfReef(reef) - predictions.getAngleToEndOfReef(reef);
                    chosenAngle = predictions.findClosestPossibleAngle(ship.getDeckOars().size(), ship.getDeck().canUseRudder(), orientationToGoal);
                }
                if (road.orientationToGoal() > predictions.getAngleToCenterOfReef(reef)) {
                    orientationToGoal = ship.getPosition().getOrientation() + predictions.getAngleToCenterOfReef(reef) + predictions.getAngleToEndOfReef(reef);
                    chosenAngle = predictions.findClosestPossibleAngle(ship.getDeckOars().size(), ship.getDeck().canUseRudder(), orientationToGoal);
                }

                Logs.add("ancien cap : " + chosenAngleAlteration + ", nouveau cap : " + chosenAngle);
                chosenAngleAlteration -= chosenAngle;

            }


        }

        if (needToSlowDown) {
            return activateSailors(orientationTable.getGoodCompo(orientationTable.getSlowDownCompo(chosenAngle),
                    nbSailorsRight, nbSailorsLeft));
        }

        if (!isNear) {// si le bateau est loin du checkpoint
            return activateSailors(orientationTable.getGoodCompo(orientationTable.getLastCompo(chosenAngle),
                    nbSailorsRight, nbSailorsLeft));// on choisit la compo permettant d'aller le plus vite
        }
        else {
            return activateSailors(orientationTable.getGoodCompo(orientationTable.getCompo(chosenAngle, 0),
                    nbSailorsRight, nbSailorsLeft));// on choisit la compo permettant d'aller le plus lentement

        }

    }

    /**
     * Méthode indiquant quand activer la voile
     *
     * @return
     */
    public boolean upSail() {
        Road road = new Road(ship.getPosition(), goal.getFirstCheckpoint().getPosition());
        List<Sail> activeSails = ship.getDeckSails().stream().filter(sail -> sail.hasSailorOn() && sail.getSailorOn().isOnEntity()).collect(Collectors.toList());
        return (!needToSlowDown && wind != null && Math.abs(ship.getPosition().getOrientation() - wind.getOrientation()) >= 0
                && Math.abs(ship.getPosition().getOrientation() - wind.getOrientation()) < Math.PI / 2)
                && road.distanceToGoal() > (165 + wind.getStrength()*activeSails.size());
    }

    ////////////////////// GETTER ////////////////////////////////////////////

    public Deck getDeck() {
        return this.ship.getDeck();
    }

    public Goal getGoal() {
        return this.goal;
    }

    /**
     * @return the ship
     */
    public Ship getShip() {
        return ship;
    }

    public OrientationTable getOrientationTable() {
        return orientationTable;
    }

    /**
     * @param ship the ship to set
     */
    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public void setWind(Wind wind){
        this.wind = wind;
    }

    public void setVisibleEntities(List<VisibleEntitie> visibleEntities) {
        this.visibleEntities = visibleEntities;
    }
}
