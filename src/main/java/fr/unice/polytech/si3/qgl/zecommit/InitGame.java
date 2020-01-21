package fr.unice.polytech.si3.qgl.zecommit;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.unice.polytech.si3.qgl.zecommit.goal.Goal;
import fr.unice.polytech.si3.qgl.zecommit.other.Checkpoint;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nathan
 */
public class InitGame {
    private Goal goal;
    private Ship ship;
    private List<Sailor> sailors;
    private int shipCount;

    @JsonCreator
    public InitGame(Goal goal, Ship ship, List<Sailor> sailors, int shipCount) {
        this.goal = goal;
        this.ship = ship;
        this.sailors = sailors;
        this.shipCount = shipCount;
    }

    public InitGame() {
        this.goal = null;
        this.ship = null;
        this.sailors = null;
        this.shipCount = 0;
    }

    @Override
    public String toString(){
        String chaine = "goal : "+this.goal.toString()+
                "\nship : "+this.ship.toString()+
                "\nsailors : "+this.sailors.toString()+
                "\nshipCount : "+this.shipCount;
        return chaine;
    }

    //--------------------GETTER -------------------------//


    public Goal getGoal() {
        return goal;
    }

    public Ship getShip() {
        return ship;
    }

    public List<Sailor> getSailors() {
        return sailors;
    }

    public int getShipCount() {
        return shipCount;
    }

    //--------------------SETTER -------------------------//

    public void setGoal(Goal goal) {
        this.goal = goal;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public void setSailors(List<Sailor> sailors) {
        this.sailors = sailors;
    }

    public void setShipCount(int shipCount) {
        this.shipCount = shipCount;
    }
}
