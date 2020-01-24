package fr.unice.polytech.si3.qgl.zecommit;

import fr.unice.polytech.si3.qgl.zecommit.goal.Goal;
import fr.unice.polytech.si3.qgl.zecommit.other.VisibleEntitie;
import fr.unice.polytech.si3.qgl.zecommit.other.Wind;

import java.util.List;

public class Game {
    private Goal goal;
    private Ship ship;
    private List<Sailor> sailors;
    private int shipCount;
    private Wind wind;
    private List<VisibleEntitie> visibleEntities;


    //------------------------GETTER-------------------------------------------


    public Ship getShip() {
        return ship;
    }

    public Goal getGoal() {
        return goal;
    }

    public int getShipCount() {
        return shipCount;
    }

    public List<Sailor> getSailors() {
        return sailors;
    }

    public List<VisibleEntitie> getVisibleEntities() {
        return visibleEntities;
    }

    public Wind getWind() {
        return wind;
    }


    public boolean isRegatta(){
        if(goal.getMode().equals("REGATTA"))
            return true;
        return false;
    }

    //------------------------SETTER-----------------------------

    public void setGoal(Goal goal) {
        this.goal = goal;
    }

    public void setSailors(List<Sailor> sailors) {
        this.sailors = sailors;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public void setShipCount(int shipCount) {
        this.shipCount = shipCount;
    }

    public void setVisibleEntities(List<VisibleEntitie> visibleEntities) {
        this.visibleEntities = visibleEntities;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }
}

