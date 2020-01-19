package fr.unice.polytech.si3.qgl.teamid;

import fr.unice.polytech.si3.qgl.teamid.goal.Goal;

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

    public InitGame(Goal goal, Ship ship, List<Sailor> sailors, int shipCount) {
        this.goal = goal;
        this.ship = ship;
        this.sailors = sailors;
        this.shipCount = shipCount;
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

}
