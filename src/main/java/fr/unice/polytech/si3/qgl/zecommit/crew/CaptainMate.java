package fr.unice.polytech.si3.qgl.zecommit.crew;

import fr.unice.polytech.si3.qgl.zecommit.Logs;
import fr.unice.polytech.si3.qgl.zecommit.action.Action;
import fr.unice.polytech.si3.qgl.zecommit.action.Moving;
import fr.unice.polytech.si3.qgl.zecommit.action.ToOar;
import fr.unice.polytech.si3.qgl.zecommit.boat.Ship;
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
    private Logs logs;

    public CaptainMate(Logs logs){
        this.actionList= new ArrayList<>();
        this.logs = logs;
    }



    /**
     * Déplace le sailor de la distance demandé.
     * Si la distance dépasse 5 l'action est annulée, ceci est prit en charge dans le constructeur de Moving
     * @param xdistance
     * @param ydistance
     */
    public void moveSailor(Sailor sailor, int xdistance, int ydistance) {
        Moving action = new Moving(sailor, xdistance, ydistance);
        if(action.getXDistance()!=0 || action.getYDistance()!=0){
            actionList.add(action);
            logs.add("\nS" + sailor.getId() + " is moving to (" + sailor.getX() + "," + sailor.getY() +")");
        }
    }


    /**
     * Place tous les marins sur une rame lors du premier tour.
     */
    public void initMoveSailor(List<Sailor> sailors, Ship ship){
        List<Sailor> sailorTmp = new ArrayList<>(sailors);
        for(Oar oar : ship.getOars()){
            sailorTmp.sort(Comparator.comparingInt( a -> a.distanceToEntity(oar)));
            if(sailors.get(0).distanceToEntity(oar)<=5) {
                moveSailor(sailors.get(0), oar.getX()-sailors.get(0).getX(), oar.getY()-sailors.get(0).getY());
                sailorTmp.remove(0);
            }
        }
    }

    /**
     * Fait ramer le marin en utilisant l'entité rame.
     * Si la position du marin est la meme que celle de la rame
     * @param oar
     * @param sailor
     */
    public void toOar(Sailor sailor,Oar oar){
        if(oar.getX()==sailor.getX() && oar.getY()==sailor.getY() && (!oar.isUsed())){
            ToOar action = new ToOar(sailor.getId());
            oar.setUsed(true);
            actionList.add(action);
            logs.add("\nS" +sailor.getId() + " is oaring from " + "("+oar.getX() +","+ oar.getY() +")");
            
        }
    }

    //--------------------------------GETTER-------------------------------

    public List<Action> getActionList() {
        return actionList;
    }
}
