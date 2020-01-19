package fr.unice.polytech.si3.qgl.ZeCommiT;


import fr.unice.polytech.si3.qgl.ZeCommiT.entite.Entite;
import fr.unice.polytech.si3.qgl.ZeCommiT.other.Vent;

import java.util.List;

/**
 *
 * @author Nathan
 */

public class NextRound {

    private Ship ship;
    private Vent wind;
    private List<Entite> visibleEntities;

    public NextRound(Ship ship, Vent wind, List<Entite> visibleEntities) {
        this.ship = ship;
        this.wind = wind;
        this.visibleEntities = visibleEntities;
    }

    //------------------------------GETTER-------------------------//
    public Ship getShip() {
        return ship;
    }

    public Vent getWind() {
        return wind;
    }

    public List<Entite> getVisibleEntities() {
        return visibleEntities;
    }

    //------------------------------SETTER-------------------------//

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public void setWind(Vent wind) {
        this.wind = wind;
    }

    public void setVisibleEntities(List<Entite> visibleEntities) {
        this.visibleEntities = visibleEntities;
    }
}
