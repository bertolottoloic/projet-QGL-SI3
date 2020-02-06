package fr.unice.polytech.si3.qgl.zecommit.crew;

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

    public CaptainMate(){
        this.actionList= new ArrayList<>();
    }



    /**
     * Déplace le sailor de la distance demandée.
     * Si la distance dépasse 5 l'action est annulée, ceci est pris en charge dans le constructeur de Moving
     * @param xdistance
     * @param ydistance
     */
    public void moveSailor(Sailor sailor, int xdistance, int ydistance) {
        Moving action = new Moving(sailor, xdistance, ydistance);
        if(action.getXDistance()!=0 || action.getYDistance()!=0){
            actionList.add(action);
            Logs.add("\nS" + sailor.getId() + " is moving to (" + sailor.getX() + "," + sailor.getY() +")");
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
        List<Sailor> sailorTmp = new ArrayList<>(sailors);
        List<Entity> oars = new ArrayList<>();
        oars.addAll(ship.getOars());
        sailorTmp.sort(Comparator.comparingInt(a->a.distanceToNearestEntity(oars)));
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
        if(sailor.isOnEntity() && sailor.getEntity()==oar && (!oar.isUsed())){
            ToOar action = new ToOar(sailor.getId());
            oar.setUsed(true);
            actionList.add(action);
            Logs.add("\nS" +sailor.getId() + " is oaring from " + "("+oar.getX() +","+ oar.getY() +")");
            
        }
    }

    //--------------------------------GETTER-------------------------------

    public List<Action> getActionList() {
        return actionList;
    }
}
