package fr.unice.polytech.si3.qgl.zecommit.crew;

import fr.unice.polytech.si3.qgl.zecommit.action.*;
import fr.unice.polytech.si3.qgl.zecommit.entite.*;
import fr.unice.polytech.si3.qgl.zecommit.shape.Point;
import fr.unice.polytech.si3.qgl.zecommit.strategy.Compo;
import fr.unice.polytech.si3.qgl.zecommit.Game;
import fr.unice.polytech.si3.qgl.zecommit.Logs;
import fr.unice.polytech.si3.qgl.zecommit.boat.Ship;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author Clement P
 * Classe qui realise les actions du Capitaine
 *
 */
public class CaptainMate {
    private List<Action> actionList;
    private List<Sailor> rightSailorList;
    private List<Sailor> leftSailorList;
    private List<Sailor> sailorList;
    private Ship ship;

    public CaptainMate(Game game){
        this.actionList= new ArrayList<>();
        this.sailorList = new ArrayList<>(game.getSailors());
        this.ship = game.getShip();
        this.leftSailorList = getLeftSailors();
        this.rightSailorList = getRightSailors();
    }



    /**
     * Déplace le sailor de la distance demandée.
     * Si la distance dépasse 5 l'action est annulée, ceci est pris en charge dans le constructeur de Moving
     * @param xdistance
     * @param ydistance
     */
    public void moveSailor(Sailor sailor, int xdistance, int ydistance) {
        Moving action = new Moving(sailor.getId(), xdistance, ydistance);
        sailor.move(action.getXDistance(),action.getYDistance());
        if(action.getXDistance()!=0 || action.getYDistance()!=0){
            actionList.add(action);
            Logs.add("\nS" + sailor.getId() + " is moving to (" + sailor.getX() + "," + sailor.getY() +")");
            refreshSailorsListPosition();
        }
    }


    /**
     * Place tous les marins sur une rame lors du premier tour.
     */
    public void initMoveSailor(List<Sailor> sailors){
        for(Sailor sailor : sailors){
            if(sailor.hasEntity() && !sailor.isOnEntity())
                moveSailor(sailor, sailor.getEntity().getX()-sailor.getX(), sailor.getEntity().getY()-sailor.getY());
        }
    }

    public void initAttibuteEntityToSailors(List<Sailor> sailors, Ship ship){
        refreshGame(ship);
        sailors.forEach(s->s.reinitializeEntity());
        List<Sailor> sailorsTmp = new ArrayList<>(sailors);
        List<Entity> oars = new ArrayList<>();
        oars.addAll(ship.getOars());
        sailorsTmp.sort(Comparator.comparingInt(a->a.distanceToNearestEntity(oars)));
        Sailor sailor;
        if(sailorsTmp.size()>4){
            sailorsTmp.remove(sailorsTmp.size()-1).setOnEntity(ship.getRudder());
            if(sailorsTmp.size()%2>0 && !ship.getSails().isEmpty()){
                sailorsTmp.remove(sailorsTmp.size()-1).setOnEntity(ship.getSails().get(0));;      
            }
        }
        for(Sailor tmp : sailorsTmp){   
            ship.getOars().sort(Comparator.comparingInt( a -> tmp.distanceToEntity(a)));
            Oar closestOar = ship.getOars().get(0);
            if(!closestOar.hasSailorOn() && tmp.distanceToEntity(closestOar)<=5 && !tmp.hasEntity()) {
                tmp.setOnEntity(closestOar);             
            }
        }
        for(Sailor tmp : sailorsTmp){
            ship.getOars().sort(Comparator.comparingInt( a -> tmp.distanceToEntity(a)));
            for(Oar oar:ship.getOars()){
                if(!oar.hasSailorOn() && !tmp.hasEntity()){
                    tmp.setOnEntity(oar);
                }
            }
        }

    }

    public void moveSailorToRudder(Sailor sailor){
        if(ship.getRudder()!=null){
            moveSailor(sailor,ship.getRudder().getX() , ship.getRudder().getY());
            sailor.setOnEntity(ship.getRudder());
        }
    }

    public boolean sailorsAreOnTheirEntity(List<Sailor> sailors){
        for(Sailor sailor : sailors){
            if (!sailor.isOnEntity() && sailor.hasEntity())
                return false;
        }
        return true;
    }

    public boolean oarsSailorAreOn(List<Sailor> sailors){
        for (Sailor sailor : sailors) {
            if(!sailor.isOnEntity() && sailor.hasEntity() && sailor.getEntity().getType()==EntityType.OAR){
                return false;
            }
        }
        return true;
    }

    /**
     * Fait ramer le marin en utilisant l'entité rame.
     * Si la position du marin est la meme que celle de la rame
     * @param oar
     * @param sailor
     */
    public void toOar(Sailor sailor,Oar oar){

        if(sailor.isOnEntity() && sailor.getEntity()==oar){
            ToOar action = new ToOar(sailor.getId());
            actionList.add(action);
            Logs.add("\nS" +sailor.getId() + " is oaring from " + "("+oar.getX() +","+ oar.getY() +")");

        }
    }

    /**
     * Fait lever la voile par le marin sur la case
     * @param sailor
     * @param sail
     */
    public void toLiftSail(Sailor sailor, Sail sail) {
        LiftSail action = new LiftSail(sailor.getId());
        actionList.add(action);
        Logs.add("\nS" +sailor.getId() + " is lifting the sail from " + "("+sail.getX() +","+ sail.getY() +")");
    }
    /**
     * Fait baisser la voile par le marin sur la case
     * @param sailor
     * @param sail
     */
    public void toLowerSail(Sailor sailor, Sail sail) {
        LowerSail action = new LowerSail(sailor.getId());
        actionList.add(action);
        Logs.add("\nS" +sailor.getId() + " is lowering the sail from " + "("+sail.getX() +","+ sail.getY() +")");
    }


    public void toTurn(Sailor sailor, Rudder rudder, double angle){

        if(sailor.isOnEntity() && sailor.getEntity()==rudder){
            Turn action = new Turn(sailor.getId(), angle);
            actionList.add(action);
            Logs.add("\nS" +sailor.getId() + " turn from " + "("+rudder.getX() +","+ rudder.getY() +")");

        }
    }

    /**
     * Effectue l'ordre d'activation des marins aux rames et au gouvernail
     * @param compo
     */
    public void activateSailors(Compo compo, double angle){
        refreshSailorsListPosition();
        // Activation des marins de gauche
        int l = 0;
        while (l<compo.getSailorsLeft()) {
            toOar(leftSailorList.get(l), (Oar) leftSailorList.get(l).getEntity());
            l++;
        }

        // Activation des marins de droite
        int r = 0;
        while(r<compo.getSailorsRight()) {
            toOar(rightSailorList.get(r), (Oar) rightSailorList.get(r).getEntity());
            r++;
        }

        //Activation du gouvernail
        if(ship.getRudder()!=null && ship.getRudder().hasSailorOn())
            toTurn(ship.getRudder().getSailorOn(), ship.getRudder(), angle);
    }

    /**
     * Effectue l'ordre d'activation du marin à la voile
     */
    public void activateLiftSail(Sail sail){
        for(Sailor sailor : sailorList) {
            if (sailor.hasEntity() && sailor.getEntity().getType().equals(EntityType.SAIL)
                && sail.hasSailorOn()) {
                toLiftSail(sail.getSailorOn(), sail);
            }
        }
    }

    public void activateLowerSail(Sail sail) {
        for (Sailor sailor : sailorList) {
            if (sailor.hasEntity() && sailor.getEntity().getType().equals(EntityType.SAIL)
                && sail.hasSailorOn()) {
                toLowerSail(sail.getSailorOn(), sail);
            }
        }
    }


    /**
     *
     * @return la liste des marins à gauche du bateau.
     */
    public List<Sailor> getLeftSailors(){
        ArrayList<Sailor> sailors = new ArrayList<>();
        for(Sailor sailor : sailorList){
            if(sailor.getY()<ship.getDeck().getWidth()/2 && sailor.isOnEntity() && sailor.getEntity().getType()==EntityType.OAR)
                sailors.add(sailor);
        }
        return sailors;
    }

    /**
     *
     * @return la liste des marins à droite du bateau.
     */
    public List<Sailor> getRightSailors(){
        ArrayList<Sailor> sailors = new ArrayList<>();
        for(Sailor sailor : sailorList){
            if(sailor.getY()>=((ship.getDeck().getWidth()/2)+(ship.getDeck().getWidth()%2)) && sailor.isOnEntity() && sailor.getEntity().getType()==EntityType.OAR)
                sailors.add(sailor);
        }
        return sailors;
    }

    public void refreshGame(Ship ship){
        this.ship=ship;
    }

    public void refreshSailorsListPosition(){
        this.rightSailorList = getRightSailors();
        this.leftSailorList = getLeftSailors();
    }


    //--------------------------------GETTER-------------------------------

    public List<Action> getActionList() {
        return actionList;
    }



}
