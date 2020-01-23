package fr.unice.polytech.si3.qgl.zecommit;

import fr.unice.polytech.si3.qgl.zecommit.action.Action;
import fr.unice.polytech.si3.qgl.zecommit.action.ToOar;
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
    private Captain captain;

    public CaptainMate(Captain captain){
        this.captain= captain;
        this.actionList= new ArrayList<>();
    }


    public void actions(List<Checkpoint> checkpoints ) {
        this.actionList.removeAll(actionList);
        System.out.println(checkpoints.get(0));
        if(!captain.getNextRound().getShip().estDedans(checkpoints.get(0))) {//TODO plusieurs checkpoints
            //TODO : cas nb marin impair
            for (int i = 0; i < captain.getSailorList().size(); i++) {
                //TODO : vérifier le nombre de rames présentes
                actionList.add(i, new ToOar(i));
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
