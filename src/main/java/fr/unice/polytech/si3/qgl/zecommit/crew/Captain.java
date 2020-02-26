package fr.unice.polytech.si3.qgl.zecommit.crew;

import fr.unice.polytech.si3.qgl.zecommit.Game;
import fr.unice.polytech.si3.qgl.zecommit.Logs;
import fr.unice.polytech.si3.qgl.zecommit.boat.Deck;
import fr.unice.polytech.si3.qgl.zecommit.boat.Ship;
import fr.unice.polytech.si3.qgl.zecommit.entite.Entity;
import fr.unice.polytech.si3.qgl.zecommit.entite.Oar;
import fr.unice.polytech.si3.qgl.zecommit.goal.Goal;
import fr.unice.polytech.si3.qgl.zecommit.goal.Regatta;
import fr.unice.polytech.si3.qgl.zecommit.maths.Compo;
import fr.unice.polytech.si3.qgl.zecommit.maths.OrientationTable;
import fr.unice.polytech.si3.qgl.zecommit.maths.Road;
import fr.unice.polytech.si3.qgl.zecommit.other.Wind;
import fr.unice.polytech.si3.qgl.zecommit.visible.VisibleEntity;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Captain implements CaptainInterface {

    private Ship ship;
    private Deck deck;
    private Regatta goal;
    OrientationTable orientationTable;
    private List<Sailor> rightSailorList;
    private List<Sailor> leftSailorList;
    private Wind wind;
    private List<VisibleEntity> visibleEntities;

    public Captain(Game game) {
        this.ship = game.getShip();
        this.deck = ship.getDeck();
        this.goal= (Regatta) game.getGoal();
        this.orientationTable = new OrientationTable(deck.getOars().size());
        this.visibleEntities= game.getVisibleEntities();
        this.leftSailorList=new ArrayList<>();
        this.rightSailorList=new ArrayList<>();
        this.wind=game.getWind();
    }

    @Override
    public void attributeEntitiesToSailors() {
        List<Sailor> sailors = deck.getSailors();
        List<Sailor> sailorsTmp = new ArrayList<>(sailors);
        List<Entity> oars = new ArrayList<>(deck.getOars());
        sailorsTmp.sort(Comparator.comparingInt(a -> a.distanceToNearestEntity(oars)));
        if(sailorsTmp.size()>4){
            sailorsTmp.remove(sailorsTmp.size()-1).setOnEntity(deck.getRudder());
            if(sailorsTmp.size()%2>0 && !deck.getSails().isEmpty()){
                sailorsTmp.remove(sailorsTmp.size()-1).setOnEntity(deck.getSails().get(0));
            }
        }
        for (Sailor tmp : sailorsTmp) {
            deck.getOars().sort(Comparator.comparingInt(a -> tmp.distanceToEntity(a)));
            Oar closestOar = deck.getOars().get(0);
            if (!closestOar.hasSailorOn() && tmp.distanceToEntity(closestOar) <= 5 && !tmp.hasEntity()) {
                tmp.setOnEntity(closestOar);
            }
        }
        for (Sailor tmp : sailorsTmp) {
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
        return new ArrayList<>();
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
            return new SimpleEntry<>(deck.getRudder().getSailorOn(),angle);
        return null;


    }

    @Override
    public List<Sailor> doLiftSail() {
        return new ArrayList<>();
    }

    @Override
    public List<Sailor> doLowerSail() {
        return new ArrayList<>();
    }

    @Override
    public boolean pursueGame() {
        return !(ship.isInCheckpoint(goal.getCheckpoints().get(goal.getCheckpoints().size()-1)) && goal.getCheckpoints().size()==1);
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
    public List<Sailor> activateSailors(Compo compo) {
        ArrayList<Sailor> usedSailors= new ArrayList<>();
        // Activation des marins de gauche
        int l = 0;
        while (l < compo.getSailorsLeft()) {
            usedSailors.add(leftSailorList.get(l));
            l++;
        }


        // Activation des marins de droite
        int r = 0;
        while (r < compo.getSailorsRight()) {
            usedSailors.add(rightSailorList.get(r));
            r++;
        }
        return usedSailors;
    }

    private List<Sailor> decisionOrientation(Road road, int chosenAngle){
        boolean isNear = road.distanceToGoal() < (165-goal.getFirstCheckpoint().getCircleRadius());

        //boolean upSail = upSail();
        rightSailorList = deck.getUsedOars().stream().filter(oar -> !deck.isLeft(oar)).map(oar -> oar.getSailorOn()).collect(Collectors.toList());
        leftSailorList = deck.getUsedOars().stream().filter(oar -> deck.isLeft(oar)).map(oar -> oar.getSailorOn()).collect(Collectors.toList());
        int nbSailorsRight = rightSailorList.size();
        int nbSailorsLeft = leftSailorList.size();

        if(!isNear){//si le bateau est loin
            return activateSailors(orientationTable.getGoodCompo(orientationTable.getLastCompo(chosenAngle), nbSailorsRight, nbSailorsLeft));//on choisit la compo permettant d'aller le plus vite
        }
        else
           return activateSailors(orientationTable.getGoodCompo(orientationTable.getCompo(chosenAngle, 0),nbSailorsRight, nbSailorsLeft));//on choisit la compo permettant d'aller le plus lentement
        

    }

    /**
     * Méthode indiquant quand activer la voile
     * @return
     */
    public boolean upSail(){
        return (wind != null && Math.abs(ship.getPosition().getOrientation()-wind.getStrength())>0 && Math.abs(ship.getPosition().getOrientation()-wind.getStrength())<Math.PI/2);
    }

    public void refreshData(Game game){
        this.ship = game.getShip();
        this.deck = ship.getDeck();
        this.visibleEntities=game.getVisibleEntities();
        this.wind=game.getWind();
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
