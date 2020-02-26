package fr.unice.polytech.si3.qgl.zecommit.crew;

import fr.unice.polytech.si3.qgl.zecommit.Game;
import fr.unice.polytech.si3.qgl.zecommit.Logs;
import fr.unice.polytech.si3.qgl.zecommit.boat.Deck;
import fr.unice.polytech.si3.qgl.zecommit.boat.Ship;
import fr.unice.polytech.si3.qgl.zecommit.entite.Entity;
import fr.unice.polytech.si3.qgl.zecommit.entite.Oar;
import fr.unice.polytech.si3.qgl.zecommit.goal.Goal;
import fr.unice.polytech.si3.qgl.zecommit.goal.Regatta;
import fr.unice.polytech.si3.qgl.zecommit.other.Wind;
import fr.unice.polytech.si3.qgl.zecommit.strategy.Compo;
import fr.unice.polytech.si3.qgl.zecommit.strategy.OrientationTable;
import fr.unice.polytech.si3.qgl.zecommit.strategy.Road;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Captain implements CaptainInterface {

    private Ship ship;
    private Regatta goal;
    private OrientationTable orientationTable;
    private Wind wind;

    public Captain(Game game) {
        this.ship = game.getShip();
        this.goal= (Regatta) game.getGoal();
        this.orientationTable = new OrientationTable(ship.getDeck().getOars().size());
        this.wind=game.getWind();
    }

    @Override
    public void attributeEntitiesToSailors() {
        List<Sailor> sailors = ship.getDeckSailors();
        List<Sailor> sailorsTmp = new ArrayList<>(sailors);
        List<Entity> oars = new ArrayList<>(ship.getDeckOars());
        sailorsTmp.sort(Comparator.comparingInt(a -> a.distanceToNearestEntity(oars)));
        if(sailorsTmp.size()>4){
            sailorsTmp.remove(sailorsTmp.size()-1).setOnEntity(ship.getDeckRudder());
            if(sailorsTmp.size()%2>0 && !ship.getDeck().getSails().isEmpty()){
                sailorsTmp.remove(sailorsTmp.size()-1).setOnEntity(ship.getDeckSails().get(0));
            }
        }
        for (Sailor tmp : sailorsTmp) {
            ship.getDeckOars().sort(Comparator.comparingInt(a -> tmp.distanceToEntity(a)));
            Oar closestOar = ship.getDeckOars().get(0);
            if (!closestOar.hasSailorOn() && tmp.distanceToEntity(closestOar) <= 5 && !tmp.hasEntity()) {
                tmp.setOnEntity(closestOar);
            }
        }
        for (Sailor tmp : sailorsTmp) {
            ship.getDeckOars().sort(Comparator.comparingInt(a -> tmp.distanceToEntity(a)));
            for (Oar oar : ship.getDeckOars()) {
                if (!oar.hasSailorOn() && !tmp.hasEntity()) {
                    tmp.setOnEntity(oar);
                }
            }
        }


    }

    @Override
    public List<Sailor> doMoveSailors() {
        if (!ship.getDeck().sailorsAreOnTheirEntity())
            return ship.getDeckSailors();
        return new ArrayList<Sailor>();
    }

    @Override
    public List<Sailor> doActivateOars() {
        if(ship.isInCheckpoint(goal.getFirstCheckpoint()) && goal.getCheckpoints().size()>1) {
            goal.validateCommonCheckpoint();
            Logs.add("Checkpoint done");
        }
        Road road = new Road(ship.getPosition(), goal.getFirstCheckpoint().getPosition());
        int chosenAngle = road.findClosestPossibleAngle(ship.getDeckOars().size());
        return decisionOrientation(road,chosenAngle);
    }

    @Override
    public SimpleEntry<Sailor, Double> doTurn() {
        Road road = new Road(ship.getPosition(), goal.getFirstCheckpoint().getPosition());
        double angle = road.orientationToGoal() - orientationTable.getAngleTable().get(road.findClosestPossibleAngle(ship.getDeck().getOars().size()));
        if (ship.getDeckRudder() != null && ship.getDeckRudder().hasSailorOn())
            return new SimpleEntry<Sailor,Double>(ship.getDeckRudder().getSailorOn(),angle);
        return null;


    }

    @Override
    public List<Sailor> doLiftSail() {
        return new ArrayList<Sailor>();
    }

    @Override
    public List<Sailor> doLowerSail() {
        return new ArrayList<Sailor>();
    }

    @Override
    public boolean pursueGame() {
        return !(ship.isInCheckpoint(goal.getCheckpoints().get(goal.getCheckpoints().size() - 1)) && goal.getCheckpoints().size() == 1);
    }


    /**
     * Effectue l'ordre d'activation des marins aux rames et au gouvernail
     *
     * @param compo
     */
    public ArrayList<Sailor> activateSailors(Compo compo, double angle) {
        ArrayList<Sailor> usedSailors= new ArrayList<>();
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

    private ArrayList<Sailor> decisionOrientation(Road road, int chosenAngle){
        Logs.add(chosenAngle +"");
        boolean isNear = road.distanceToGoal() < (165-goal.getFirstCheckpoint().getCircleRadius());
        boolean upSail = upSail();
        List<Sailor> rightSailorList = ship.getDeck().getUsedOars().stream().filter(oar -> !ship.getDeck().isLeft(oar)).map(oar -> oar.getSailorOn()).collect(Collectors.toList()); //TODO comprend pas
        for (Sailor sailor : rightSailorList) {
            ship.getDeck().addSailor(sailor);
        }
        List<Sailor> leftSailorList = ship.getDeck().getUsedOars().stream().filter(oar -> ship.getDeck().isLeft(oar)).map(oar -> oar.getSailorOn()).collect(Collectors.toList());
        for (Sailor sailor : leftSailorList) {
            ship.getDeck().addSailor(sailor);
        }

        int nbSailorsRight = ship.getDeck().getNumberRightSailors();
        int nbSailorsLeft = ship.getDeck().getNumberLeftSailors();

        if(!isNear){//si le bateau est loin
            return activateSailors(orientationTable.getGoodCompo(orientationTable.getLastCompo(chosenAngle), nbSailorsRight, nbSailorsLeft), road.orientationToGoal());//on choisit la compo permettant d'aller le plus vite
        }
        else
           return activateSailors(orientationTable.getGoodCompo(orientationTable.getCompo(chosenAngle, 0),nbSailorsRight, nbSailorsLeft),road.orientationToGoal());//on choisit la compo permettant d'aller le plus lentement
        

    }

    /**
     * Méthode indiquant quand activer la voile
     *
     * @return
     */
    public boolean upSail(){
        return (wind != null && Math.abs(ship.getPosition().getOrientation()-wind.getStrength())>0 && Math.abs(ship.getPosition().getOrientation()-wind.getStrength())<Math.PI/2);
    }



    ////////////////////// GETTER ////////////////////////////////////////////

    public Deck getDeck(){
        return this.ship.getDeck();
    }

    public Goal getGoal(){
        return this.goal;
    }

    /**
     * @return the ship
     */
    public Ship getShip() {
        return ship;
    }



}
