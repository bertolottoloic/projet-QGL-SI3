package fr.unice.polytech.si3.qgl.zecommit.parser;

import fr.unice.polytech.si3.qgl.zecommit.boat.Ship;
import fr.unice.polytech.si3.qgl.zecommit.other.VisibleEntitie;
import fr.unice.polytech.si3.qgl.zecommit.other.Wind;

import java.util.List;

public class NextRound {
    private Ship ship;
    private List<VisibleEntitie> visibleEntities;
    private Wind wind;

    public NextRound(Ship ship, List visibleEntities, Wind wind) {
        this.ship = ship;
        this.visibleEntities = visibleEntities;
        this.wind = wind;
    }

    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public List<VisibleEntitie> getVisibleEntities() {
        return visibleEntities;
    }

    public void setVisibleEntities(List<VisibleEntitie> visibleEntities) {
        this.visibleEntities = visibleEntities;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }
}
