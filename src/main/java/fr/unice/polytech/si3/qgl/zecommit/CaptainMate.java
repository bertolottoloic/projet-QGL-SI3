package fr.unice.polytech.si3.qgl.zecommit;

import fr.unice.polytech.si3.qgl.zecommit.action.Action;
import fr.unice.polytech.si3.qgl.zecommit.action.Moving;
import fr.unice.polytech.si3.qgl.zecommit.action.ToOar;
import fr.unice.polytech.si3.qgl.zecommit.entite.Oar;
import fr.unice.polytech.si3.qgl.zecommit.entite.Sail;
import fr.unice.polytech.si3.qgl.zecommit.other.Checkpoint;

import java.util.ArrayList;
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
     * Déplace le sailor de la distance demandé.
     * Si la distance dépasse 5 l'action est annulée, ceci est prit en charge dans le constructeur de Moving
     * @param xdistance
     * @param ydistance
     */
    public void moveSailor(Sailor sailor, int xdistance, int ydistance) {
        Moving action = new Moving(sailor.getId(), xdistance, ydistance);
        sailor.setX(sailor.getX() + action.getXDistance());
        sailor.setY(sailor.getY() + action.getYDistance());
        if(action.getXDistance()!=0 && action.getYDistance()!=0){
            actionList.add(action);
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
        }
    }

    //--------------------------------GETTER-------------------------------

    public List<Action> getActionList() {
        return actionList;
    }
}
