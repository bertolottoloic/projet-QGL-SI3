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
    private Game game;

    public Captain(Game game){
        this.ship=game.getShip();
        this.regatta=(Regatta) game.getGoal();
        this.sailorList=new ArrayList<>(game.getSailors());
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
    //-------------------------SETTER------------------------------


    public void setGame(Game game) {
        this.game = game;
    }
}
