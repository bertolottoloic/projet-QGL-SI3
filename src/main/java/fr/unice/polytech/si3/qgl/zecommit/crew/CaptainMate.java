package fr.unice.polytech.si3.qgl.zecommit.crew;

import fr.unice.polytech.si3.qgl.zecommit.action.Turn;
import fr.unice.polytech.si3.qgl.zecommit.entite.EntityType;
import fr.unice.polytech.si3.qgl.zecommit.entite.Rudder;
import fr.unice.polytech.si3.qgl.zecommit.strategy.Compo;
import fr.unice.polytech.si3.qgl.zecommit.Game;
import fr.unice.polytech.si3.qgl.zecommit.Logs;
import fr.unice.polytech.si3.qgl.zecommit.action.Action;
import fr.unice.polytech.si3.qgl.zecommit.action.Moving;
import fr.unice.polytech.si3.qgl.zecommit.action.ToOar;
import fr.unice.polytech.si3.qgl.zecommit.boat.Ship;
import fr.unice.polytech.si3.qgl.zecommit.entite.Entity;
import fr.unice.polytech.si3.qgl.zecommit.entite.Oar;

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

    public void initAttibuteOarToSailors(List<Sailor> sailors, Ship ship){
        refreshGame(ship);
        sailors.forEach(s->s.reinitializeEntity());
        List<Sailor> sailorTmp = new ArrayList<>(sailors);
        List<Entity> oars = new ArrayList<>();
        oars.addAll(ship.getOars());
        sailorTmp.sort(Comparator.comparingInt(a->a.distanceToNearestEntity(oars)));
        Sailor sailor;
        if(sailorTmp.size()%2!=0 && ship.getRudder()!=null){
            sailor = sailorTmp.remove(sailorTmp.size()-1);
            sailor.setOnEntity(ship.getRudder());
        }
        for(Sailor tmp : sailorTmp){   
            ship.getOars().sort(Comparator.comparingInt( a -> tmp.distanceToEntity(a)));
            Oar closestOar = ship.getOars().get(0);
            if(!closestOar.hasSailorOn() && tmp.distanceToEntity(closestOar)<=5 && !tmp.hasEntity()) {
                tmp.setOnEntity(closestOar);             
            }
        }
        for(Sailor tmp : sailorTmp){
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
            moveSailor(sailor, ship.getRudder().getX() , ship.getRudder().getY());
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


    public void toTurn(Sailor sailor, Rudder rudder, double angle){

        if(sailor.isOnEntity() && sailor.getEntity()==rudder){
            Turn action = new Turn(sailor.getId(), angle);
            actionList.add(action);
            Logs.add("\nS" +sailor.getId() + " turn from " + "("+rudder.getX() +","+ rudder.getY() +")");

        }
    }

    /**
     * Transmet l'ordre d'activation des marins au second
     * @param compo
     */
    public void activateSailors(Compo compo, double angle){

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
        if(ship.getRudder()!=null)
            toTurn(ship.getRudder().getSailorOn(), ship.getRudder(), angle);

    }


    /**
     *
     * @return la liste des marins à gauche du bateau.
     */
    public List<Sailor> getLeftSailors(){
        ArrayList<Sailor> sailors = new ArrayList<>();
        for(Sailor sailor : sailorList){
            if(sailor.getY()<ship.getDeck().getWidth()/2)
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
            if(sailor.getY()>=((ship.getDeck().getWidth()/2)+(ship.getDeck().getWidth()%2)))
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
