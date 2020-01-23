package fr.unice.polytech.si3.qgl.zecommit;
import fr.unice.polytech.si3.qgl.zecommit.goal.Regatta;

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
    private NextRound nextRound;

    public Captain(InitGame initGame){
        this.ship=initGame.getShip();
        this.regatta=(Regatta) initGame.getGoal();
        this.sailorList=new ArrayList<>(initGame.getSailors());
    }

    //---------------------------GETTER-----------------------------------------


    public List<Sailor> getSailorList() {
        return sailorList;
    }

    public NextRound getNextRound() {
        return nextRound;
    }

    public Regatta getRegatta() {
        return regatta;
    }

    public Ship getShip() {
        return ship;
    }
    //-------------------------SETTER------------------------------


    public void setNextRound(NextRound nextRound) {
        this.nextRound = nextRound;
    }
}
