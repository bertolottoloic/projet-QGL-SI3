package fr.unice.polytech.si3.qgl.zecommit.crew;

import fr.unice.polytech.si3.qgl.zecommit.Game;
import fr.unice.polytech.si3.qgl.zecommit.Logs;
import fr.unice.polytech.si3.qgl.zecommit.boat.Ship;
import fr.unice.polytech.si3.qgl.zecommit.entite.Entity;
import fr.unice.polytech.si3.qgl.zecommit.entite.EntityType;
import fr.unice.polytech.si3.qgl.zecommit.entite.Oar;
import fr.unice.polytech.si3.qgl.zecommit.entite.Sail;
import fr.unice.polytech.si3.qgl.zecommit.goal.Regatta;
import fr.unice.polytech.si3.qgl.zecommit.other.Wind;
import fr.unice.polytech.si3.qgl.zecommit.strategy.Compo;
import fr.unice.polytech.si3.qgl.zecommit.strategy.OrientationTable;
import fr.unice.polytech.si3.qgl.zecommit.strategy.Road;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Clement P
 *
 * Classe qui definit le capitaine
 * Le capitaine se charge de la decision.
 */

public class Captain {
    private Ship ship;
    private Regatta regatta;
    private List<Sailor> sailorList;
    private List<Oar> oarList;
    private int oarsNb;
    private CaptainMate captainMate;
    private Game game;
    boolean initGame=true;
    private List<Sailor> rightSailorList;
    private List<Sailor> leftSailorList;
    private Wind wind;



    public Captain(Game game, CaptainMate cM){//TODO à embellir
        this.ship = game.getShip();
        this.regatta = (Regatta) game.getGoal();
        this.sailorList = new ArrayList<>(game.getSailors());
        this.captainMate = cM;
        this.oarList = ship.getOars();
        this.oarsNb = ship.getOarsNb();
        this.wind = game.getWind();
        Logs.add("oarsNb:"+oarsNb);
    }

    /**
     * main du capitaine
     */
    public void actions() {
        captainMate.getActionList().removeAll(captainMate.getActionList());

        if(ship.isInCheckpoint(regatta.getFirstCheckpoint()) && regatta.getCheckpoints().size()>1) {
            regatta.validateCommonCheckpoint();
            Logs.add("Checkpoint done");
        }

        
        captainMate.initAttibuteEntityToSailors(sailorList, ship);
        initGame=false;
        

        if(!captainMate.sailorsAreOnTheirEntity(sailorList)) {
            captainMate.initMoveSailor(sailorList);
        }
        else {
            if (!game.getShip().isInCheckpoint(regatta.getFirstCheckpoint())) {
                refreshSailorsListPosition();
                Road road = new Road(ship.getPosition(), regatta.getFirstCheckpoint().getPosition());
                int chosenAngle = findClosestPossibleAngle(road.orientationToGoal());
                Logs.add(ship.getPosition().getOrientation() + " - " + chosenAngle + "");
                Logs.add(ship.getPosition().toString());
                decisionOrientation(road, chosenAngle);
            }
        }
    }

    /**
     * Méthode renvoyant la tranche dans laquelle se situe l'angle souhaité
     */
    public int findClosestPossibleAngle(double angleToReach){
        double step = Math.PI/(2*oarsNb);
        int res = 0;
        for (int k = 0; k<2*oarsNb; k ++){

            if(k*step-Math.PI/2 <= angleToReach && angleToReach <= (k+1)*step-Math.PI/2 )

                res = k;
        }
        if(turnAroundLeft(angleToReach))
            return oarsNb;
        if(turnAroundRight(angleToReach))
            return 0;

        if(res==0)
            return 0;
        if(res==2*oarsNb-1)
            return oarsNb;
        else
            return (res+1)/2;
    }

    /**
     * demi tour gauche ?
     * @return
     */
    public boolean turnAroundLeft(Double angle){
            return (angle > Math.PI/2 && angle <= Math.PI);
    }


    /**
     * demi tour droite ?
     * @return
     */
    public boolean turnAroundRight(Double angle){
        return (angle < -Math.PI/2 && angle > -Math.PI);
    }


    public void decisionOrientation(Road road, int chosenAngle){
        OrientationTable orientationTable = new OrientationTable(oarsNb);
        Logs.add(chosenAngle +"");

        boolean isNear = road.distanceToGoal() < (165-regatta.getFirstCheckpoint().getCircleRadius());
        boolean upSail = upSail();
        int nbSailorsRight = rightSailorList.size();
        int nbSailorsLeft = leftSailorList.size();

        //TODO à corriger si marin <= 4
        if(road.orientationToGoal()>-Math.PI/4 && road.orientationToGoal()<Math.PI/4){
            chosenAngle = findClosestPossibleAngle(0); //on donne l'ordre aller tout droit, le gouvernail gère les virages
        }

        if(ship.hasSail())
            activateSail(upSail, isNear, ship.getSails());//Activation de la voile

        if(!isNear){//si le bateau est loin
            activateSailors(orientationTable.getGoodCompo(orientationTable.getLastCompo(chosenAngle), nbSailorsRight, nbSailorsLeft), road.orientationToGoal());//on choisit la compo permettant d'aller le plus vite
        }
        else
            activateSailors(orientationTable.getGoodCompo(orientationTable.getCompo(chosenAngle, 0),nbSailorsRight, nbSailorsLeft),road.orientationToGoal());//on choisit la compo permettant d'aller le plus lentement
    }

    /**
     * Transmet l'ordre d'activation des marins au second
     * @param compo
     */
    public void activateSailors(Compo compo, double angle){
        Logs.add(compo.toString());
        captainMate.activateSailors(compo, angle);
    }
    /**
     * Transmet l'ordre d'activation de la voile au second
     */

    public void activateSail(boolean upSail, boolean isNear, List<Sail> sails){
        if(!isNear && upSail)
            captainMate.activateLiftSail(sails.get(0));
        else {
            captainMate.activateLowerSail(sails.get(0));
        }
    }


    /**
     * Méthode indiquant quand activer la voile
     * @return
     */
    public boolean upSail(){
        if(wind != null && Math.abs(ship.getPosition().getOrientation()-wind.getStrength())>0 && Math.abs(ship.getPosition().getOrientation()-wind.getStrength())<Math.PI/2)
            return true;
        return false;
    }





    /**
     * Tri les differentes entites donnees et les ajoute a la liste correspondante
     */
    public void sortEntities(List<Entity> entityList){
        for (Entity entity : entityList){
            if (entity.getType().equals(EntityType.OAR)) {
                this.oarList.add((Oar) entity);
            }
        }
    }

    /**
     * Met a jour les informations du capitaine récupérées par le parseurNext
     * @param game
     */
    public void refreshGame(Game game){
        this.game=game;
        ship=game.getShip();
        this.oarList = ship.getOars();
    }

    public void refreshSailorsListPosition(){
        this.rightSailorList = captainMate.getRightSailors();
        this.leftSailorList = captainMate.getLeftSailors();
    }

    //---------------------------GETTER-----------------------------------------


    public List<Sailor> getSailorList() {
        return sailorList;
    }

    public Game getGame() {
        return game;
    }

    public Regatta getRegatta() {
        return regatta;
    }

    public Ship getShip() {
        return ship;
    }

    public int getOarsNb() {
        return oarsNb;
    }

    //-------------------------SETTER------------------------------


    public void setGame(Game game) {
        this.game = game;
    }
}
