package fr.unice.polytech.si3.qgl.zecommit.crew;
import fr.unice.polytech.si3.qgl.zecommit.Game;
import fr.unice.polytech.si3.qgl.zecommit.Logs;
import fr.unice.polytech.si3.qgl.zecommit.OrientationTable;
import fr.unice.polytech.si3.qgl.zecommit.Road;
import fr.unice.polytech.si3.qgl.zecommit.boat.Ship;
import fr.unice.polytech.si3.qgl.zecommit.entite.Entity;
import fr.unice.polytech.si3.qgl.zecommit.entite.Oar;
import fr.unice.polytech.si3.qgl.zecommit.goal.Regatta;

import fr.unice.polytech.si3.qgl.zecommit.shape.Compo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Clement P
 *
 *Classe qui definit le capitaine
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
    private Logs logs;
    boolean initGame=true;
    private List<Sailor> rightSailorList;
    private List<Sailor> leftSailorList;



    public Captain(Game game, CaptainMate CM, Logs logs){
        this.ship = game.getShip();
        this.regatta = (Regatta) game.getGoal();
        this.sailorList = new ArrayList<>(game.getSailors());
        this.captainMate = CM;
        this.oarList = ship.getOars();
        this.oarsNb = ship.getOarsNb();

        sortEntities(game.getEntityList());
        this.logs=logs;
        this.rightSailorList = getRightSailors();
        this.leftSailorList = getLeftSailors();
    }

    /**
     * main du capitaine
     */
    public void actions() {
        if(initGame){
            captainMate.initAttibuteOarToSailors(sailorList, ship);
            initGame=false;
        }

        captainMate.getActionList().removeAll(captainMate.getActionList());
    
        if(!captainMate.sailorsAreOnTheirEntity(sailorList)){
            captainMate.initMoveSailor(sailorList);
        }

        if(ship.isInCheckpoint(regatta.getFirstCheckpoint()) && regatta.getCheckpoints().size()>1) {
            regatta.validateCommonCheckpoint();
            logs.add("Checkpoint done");
        }

        if(!game.getShip().isInCheckpoint(regatta.getFirstCheckpoint())) {
            refreshSailorsListPosition();
            Road road = new Road(ship.getPosition(),regatta.getFirstCheckpoint().getPosition());
            int chosenAngle = findClosestPossibleAngle(road.orientationToGoal());
            logs.add(ship.getPosition().getOrientation() +" - " + chosenAngle + "");
            decisionOrientation(road, chosenAngle);
        }
    }

    /**
     * Méthode renvoyant la tranche dans laquelle se situe l'angle souhaité
     */
    public int findClosestPossibleAngle(double angleToReach){
        double step = Math.PI/(2*oarsNb);
        //System.out.println(ship.getPosition().getOrientation());
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

        boolean isNear = road.DistanceYToGoal() > (165-regatta.getFirstCheckpoint().getCircleRadius());

        if(!isNear){//si le bateau est loin
            //System.out.println(chosenAngle);
            //System.out.println(orientationTable);
            //System.out.println(orientationTable.getLastCompo(chosenAngle));
            activateSailors(orientationTable.getLastCompo(chosenAngle));//on choisit la compo permettant d'aller le plus vite
        }
        else
            activateSailors(orientationTable.getCompo(chosenAngle, 0));//on choisit la compo permettant d'aller le plus lentement
    }

    /**
     * Transmet l'ordre d'activation des marins au second
     * @param compo
     */
    public void activateSailors(Compo compo){

        // Activation des marins de gauche
        int l = 0;
        while (l<compo.getSailorsLeft()) {
            captainMate.toOar(leftSailorList.get(l), findOarAssociated(leftSailorList.get(l)));
            l++;
        }

        // Activation des marins de droite
        int r = 0;
        while(r<compo.getSailorsRight()) {
            captainMate.toOar(rightSailorList.get(r), findOarAssociated(rightSailorList.get(r)));
            r++;
        }

    }


    public Oar findOarAssociated(Sailor sailor) {
        for (int j = 0; j < oarList.size(); j++) {
            if (sailor.getX() == oarList.get(j).getX() && sailor.getY() == oarList.get(j).getY()) {
                return oarList.get(j);
            }
        }
        return null;
    }



    /**
     * Tri les differentes entites donnees et les ajoute a la liste correspondante
     */
    public void sortEntities(List<Entity> entityList){
        for (Entity entity : entityList){
            switch (entity.getType()){
                case oar:
                    this.oarList.add((Oar) entity);
                    oarList.get(oarList.size()-1).setUsed(false);
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
        this.rightSailorList = getRightSailors();
        this.leftSailorList = getLeftSailors();
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

    /**
     *
     * @return la liste des marins à gauche du bateau.
     */
    public ArrayList<Sailor> getLeftSailors(){
        ArrayList<Sailor> sailors = new ArrayList<>();
        for(Sailor sailor : sailorList){
            if(sailor.getY()==0)
                sailors.add(sailor);
        }
        return sailors;
    }

    /**
     *
     * @return la liste des marins à droite du bateau.
     */
    public ArrayList<Sailor> getRightSailors(){
        ArrayList<Sailor> sailors = new ArrayList<>();
        for(Sailor sailor : sailorList){
            if(sailor.getY()!=0)
                sailors.add(sailor);
        }
        return sailors;
    }

    //-------------------------SETTER------------------------------


    public void setGame(Game game) {
        this.game = game;
    }
}
