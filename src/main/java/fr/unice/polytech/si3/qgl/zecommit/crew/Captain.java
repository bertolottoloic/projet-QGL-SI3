package fr.unice.polytech.si3.qgl.zecommit.crew;

import fr.unice.polytech.si3.qgl.zecommit.*;
import fr.unice.polytech.si3.qgl.zecommit.boat.Ship;
import fr.unice.polytech.si3.qgl.zecommit.entite.Entity;
import fr.unice.polytech.si3.qgl.zecommit.entite.EntityType;
import fr.unice.polytech.si3.qgl.zecommit.entite.Oar;
import fr.unice.polytech.si3.qgl.zecommit.goal.Regatta;

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
    private int sailorsNb;
    private List<Oar> oarList;
    private int oarsNb;
    private CaptainMate captainMate;
    private Game game;
    boolean initGame=true;
    private List<Sailor> rightSailorList;
    private List<Sailor> leftSailorList;



    public Captain(Game game, CaptainMate cM){//TODO à embellir
        this.ship = game.getShip();
        this.regatta = (Regatta) game.getGoal();
        this.sailorList = new ArrayList<>(game.getSailors());
        this.sailorsNb = sailorList.size();
        this.captainMate = cM;
        this.oarList = ship.getOars();
        this.oarsNb = ship.getOarsNb();
        Logs.add("oarsNb:"+oarsNb);

        sortEntities(game.getEntityList());
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

        if(initGame){
            captainMate.initAttibuteOarToSailors(sailorList, ship);
            initGame=false;
        }

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
        double orientation = ship.getPosition().getOrientation();
        for (int k = 0; k<2*oarsNb; k ++){

            if(k*step-Math.PI/2 + orientation <= angleToReach && angleToReach <= (k+1)*step-Math.PI/2 + orientation )

                res = k;
        }
        if(turnAroundLeft(angleToReach))
            return 0;
        if(turnAroundRight(angleToReach))
            return oarsNb;

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
        if(angle > Math.PI/2 && angle <= Math.PI)
            return true;
        return false;
    }


    /**
     * demi tour droite ?
     * @return
     */
    public boolean turnAroundRight(Double angle){
        if(angle < -Math.PI/2 && angle > -Math.PI)
            return true;
        return false;
    }


    public void decisionOrientation(Road road, int chosenAngle){
        OrientationTable orientationTable = new OrientationTable(oarsNb);
        Logs.add(orientationTable.toString());
        Logs.add(chosenAngle +"");

        boolean isNear = road.yDistanceToGoal() < (165-regatta.getFirstCheckpoint().getCircleRadius());
        int nbSailorsRight = rightSailorList.size();
        int nbSailorsLeft = leftSailorList.size();


        if(!isNear){//si le bateau est loin
            activateSailors(orientationTable.getGoodCompo(orientationTable.getLastCompo(chosenAngle), nbSailorsRight, nbSailorsLeft));//on choisit la compo permettant d'aller le plus vite
        }
        else
            activateSailors(orientationTable.getGoodCompo(orientationTable.getCompo(chosenAngle, 0),nbSailorsRight, nbSailorsLeft));//on choisit la compo permettant d'aller le plus lentement
    }

    /**
     * Transmet l'ordre d'activation des marins au second
     * @param compo
     */
    public void activateSailors(Compo compo){
        Logs.add(compo.toString());
        captainMate.activateSailors(compo);
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
        this.oarList.removeAll(oarList);
        this.game=game;
        ship=game.getShip();
        sortEntities(game.getEntityList());
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
