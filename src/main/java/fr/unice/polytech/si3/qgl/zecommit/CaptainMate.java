package fr.unice.polytech.si3.qgl.zecommit;

import fr.unice.polytech.si3.qgl.zecommit.action.Action;
import fr.unice.polytech.si3.qgl.zecommit.action.Oar;
import fr.unice.polytech.si3.qgl.zecommit.other.Checkpoint;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Clement P
 * Classe qui realise les actions du Capitaine
 *
 */
public class CaptainMate {
    private List<Action> actionList;
    private Captain captain;

    public CaptainMate(Captain captain){
        this.captain= captain;
        this.actionList= new ArrayList<Action>();
    }


    public void actions(List<Checkpoint> checkpoints ) {
        this.actionList.removeAll(actionList);
        if(!captain.getNextRound().getShip().estDedans(checkpoints.get(0))) {//TODO plusieurs checkpoints
            //TODO : cas nb marin impair
            for (int i = 0; i < captain.getSailorList().size(); i++) {
                //TODO : vérifier le nombre de rames présentes
                actionList.add(i, new Oar(i));
            }
        }
    }

    //--------------------------------GETTER-------------------------------

    public Captain getCaptain() {
        return captain;
    }

    public List<Action> getActionList() {
        return actionList;
    }
}
