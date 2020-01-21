package fr.unice.polytech.si3.qgl.zecommit;

import fr.unice.polytech.si3.qgl.zecommit.action.Action;
import fr.unice.polytech.si3.qgl.zecommit.action.Oar;
import fr.unice.polytech.si3.qgl.zecommit.other.Checkpoint;

import java.util.ArrayList;
import java.util.List;

public class Captain {
    private NextRound nextRound;
    private int nbSailors;

    public Captain(NextRound nextRound, int nbSailors){
        this.nextRound = nextRound;
        this.nbSailors = nbSailors;

    }

    public List<Action> actions(List<Checkpoint> checkpoints ) {
        List<Action> actions = new ArrayList<Action>();
        if(!nextRound.getShip().estDedans(checkpoints.get(0))) {//TODO plusieurs checkpoints
            //TODO : cas nb marin impair
            for (int i = 0; i < nbSailors; i++) {
                //TODO : vérifier le nombre de rames présentes
                actions.add(i, new Oar(i));
            }
        }
        return actions;
    }

}