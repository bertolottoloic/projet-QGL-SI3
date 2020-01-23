package fr.unice.polytech.si3.qgl.zecommit;

import com.fasterxml.jackson.annotation.JsonCreator;
import fr.unice.polytech.si3.qgl.zecommit.goal.Goal;

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
        return  "goal : "+this.goal.toString()+
                "\nship : "+this.ship.toString()+
                "\nsailors : "+this.sailors.toString()+
                "\nshipCount : "+this.shipCount;
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

    public String getGoalMode(){
        return goal.getMode();
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
