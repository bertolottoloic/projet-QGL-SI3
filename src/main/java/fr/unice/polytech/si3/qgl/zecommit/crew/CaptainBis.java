package fr.unice.polytech.si3.qgl.zecommit.crew;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.AbstractMap.SimpleEntry;
import java.util.stream.Collectors;

import fr.unice.polytech.si3.qgl.zecommit.Game;
import fr.unice.polytech.si3.qgl.zecommit.Logs;
import fr.unice.polytech.si3.qgl.zecommit.boat.Deck;
import fr.unice.polytech.si3.qgl.zecommit.boat.Ship;
import fr.unice.polytech.si3.qgl.zecommit.entite.Entity;
import fr.unice.polytech.si3.qgl.zecommit.entite.Oar;
import fr.unice.polytech.si3.qgl.zecommit.entite.Sail;
import fr.unice.polytech.si3.qgl.zecommit.goal.Goal;
import fr.unice.polytech.si3.qgl.zecommit.goal.Regatta;
import fr.unice.polytech.si3.qgl.zecommit.strategy.Compo;
import fr.unice.polytech.si3.qgl.zecommit.strategy.OrientationTable;
import fr.unice.polytech.si3.qgl.zecommit.strategy.Road;

public class CaptainBis implements CaptainInterface {

    private Ship ship;
    private Deck deck;
    private Regatta goal;
    OrientationTable orientationTable;

    private List<Sailor> rightSailorList;
    private List<Sailor> leftSailorList;

    public CaptainBis(Game game) {
        this.ship = game.getShip();
        this.deck = ship.getDeck();
        this.goal= (Regatta) game.getGoal();
        this.orientationTable = new OrientationTable(deck.getOars().size());

        this.leftSailorList=new ArrayList<>();
        this.rightSailorList=new ArrayList<>();
    }

    @Override
    public void attributeEntitiesToSailors() {
        List<Sailor> sailors = deck.getSailors();
        sailors.forEach(s -> s.reinitializeEntity());
        List<Sailor> sailorTmp = new ArrayList<>(sailors);
        List<Entity> oars = new ArrayList<>(deck.getOars());
        sailorTmp.sort(Comparator.comparingInt(a -> a.distanceToNearestEntity(oars)));
        Sailor sailor;
        if (sailorTmp.size() % 2 != 0 && deck.getRudder() != null) {
            sailor = sailorTmp.remove(sailorTmp.size() - 1);
            sailor.setOnEntity(deck.getRudder());
        }
        for (Sailor tmp : sailorTmp) {
            deck.getOars().sort(Comparator.comparingInt(a -> tmp.distanceToEntity(a)));
            Oar closestOar = deck.getOars().get(0);
            if (!closestOar.hasSailorOn() && tmp.distanceToEntity(closestOar) <= 5 && !tmp.hasEntity()) {
                tmp.setOnEntity(closestOar);
            }
        }
        for (Sailor tmp : sailorTmp) {
            deck.getOars().sort(Comparator.comparingInt(a -> tmp.distanceToEntity(a)));
            for (Oar oar : deck.getOars()) {
                if (!oar.hasSailorOn() && !tmp.hasEntity()) {
                    tmp.setOnEntity(oar);
                }
            }
        }

    }

    @Override
    public List<Sailor> doMoveSailors() {
        if (!deck.sailorsAreOnTheirEntity())
            return deck.getSailors();
        return new ArrayList<Sailor>();
    }

    @Override
    public List<Sailor> doActivateOars() {
        if(ship.isInCheckpoint(goal.getFirstCheckpoint()) && goal.getCheckpoints().size()>1) {
            goal.validateCommonCheckpoint();
            Logs.add("Checkpoint done");
        }
        Road road = new Road(ship.getPosition(), goal.getFirstCheckpoint().getPosition());
        int chosenAngle = road.findClosestPossibleAngle(deck.getOars().size());
        return decisionOrientation(road,chosenAngle);
    }

    @Override
    public SimpleEntry<Sailor, Double> doTurn() {
        Road road = new Road(ship.getPosition(), goal.getFirstCheckpoint().getPosition());
        double angle = road.orientationToGoal() - orientationTable.getAngleTable().get(road.findClosestPossibleAngle(deck.getOars().size()));
        if (deck.getRudder() != null && deck.getRudder().hasSailorOn())
            return new SimpleEntry<Sailor,Double>(deck.getRudder().getSailorOn(),angle);
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
        boolean z = !ship.isInCheckpoint(goal.getFirstCheckpoint());
        return !ship.isInCheckpoint(goal.getCheckpoints().get(goal.getCheckpoints().size()-1));
    }



    /**
     * Met a jour les informations du capitaine récupérées par le parseurNext
     * @param game
     */
    public void refreshGame(Game game){
        ship=game.getShip();
        this.deck.setOars( ship.getDeck().getOars());
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
            usedSailors.add(leftSailorList.get(l));
            //toOar(leftSailorList.get(l), (Oar) leftSailorList.get(l).getEntity());
            l++;
        }

        // Activation des marins de droite
        int r = 0;
        while (r < compo.getSailorsRight()) {
            usedSailors.add(rightSailorList.get(r));

            //toOar(rightSailorList.get(r), (Oar) rightSailorList.get(r).getEntity());
            r++;
        }
        return usedSailors;

        // Activation du gouvernail
        //if (deck.getRudder() != null && deck.getRudder().hasSailorOn())
           // toTurn(deck.getRudder().getSailorOn(), deck.getRudder(), angle);
    }

    private ArrayList<Sailor> decisionOrientation(Road road, int chosenAngle){
        Logs.add(chosenAngle +"");
        boolean isNear = road.yDistanceToGoal() < (165-((Regatta)goal).getFirstCheckpoint().getCircleRadius());
        boolean upSail = upSail();
        rightSailorList = deck.getUsedOars().stream().filter(oar -> !deck.isLeft(oar)).map(oar -> oar.getSailorOn()).collect(Collectors.toList());
        leftSailorList = deck.getUsedOars().stream().filter(oar -> deck.isLeft(oar)).map(oar -> oar.getSailorOn()).collect(Collectors.toList());
        int nbSailorsRight = rightSailorList.size();
        int nbSailorsLeft = leftSailorList.size();

        if(!isNear){//si le bateau est loin
            return activateSailors(orientationTable.getGoodCompo(orientationTable.getLastCompo(chosenAngle), nbSailorsRight, nbSailorsLeft), road.orientationToGoal());//on choisit la compo permettant d'aller le plus vite
        }
        else
           return activateSailors(orientationTable.getGoodCompo(orientationTable.getCompo(chosenAngle, 0),nbSailorsRight, nbSailorsLeft),road.orientationToGoal());//on choisit la compo permettant d'aller le plus lentement
        
    }

    /**
     * Méthode indiquant quand activer la voile
     * @return
     */
    public boolean upSail(){
        //TODO condition permettant de lever la voile
        return false;
    }

    public void refreshData(Game game){
        this.ship = game.getShip();
        this.deck = ship.getDeck();
    }


    ////////////////////// GETTER ////////////////////////////////////////////

    public Deck getDeck(){
        return this.deck;
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