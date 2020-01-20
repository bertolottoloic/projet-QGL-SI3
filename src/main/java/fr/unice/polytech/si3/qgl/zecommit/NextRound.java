package fr.unice.polytech.si3.qgl.ZeCommiT;


import com.fasterxml.jackson.annotation.JsonProperty;
import fr.unice.polytech.si3.qgl.ZeCommiT.entite.Entite;
import fr.unice.polytech.si3.qgl.ZeCommiT.other.Vent;
import fr.unice.polytech.si3.qgl.ZeCommiT.other.VisibleEntitie;

import java.util.List;

/**
 *
 * @author Nathan
 */

public class NextRound {
    @JsonProperty("ship")
    private Ship ship;
    @JsonProperty("wind")
    private Vent wind;
    @JsonProperty("visibleEntities")
    private List<VisibleEntitie> visibleEntities;

    public NextRound(@JsonProperty("ship") Ship ship,@JsonProperty("wind") Vent wind,@JsonProperty("visibleEntities") List<VisibleEntitie> visibleEntities) {
        this.ship = ship;
        this.wind = wind;
        this.visibleEntities = visibleEntities;
    }

    public NextRound() {
        this.ship = null;
        this.wind = null;
        this.visibleEntities = null;
    }


    @Override
    public String toString() {
        return "NextRound{" +
                "ship=" + ship +
                ", wind=" + wind +
                ", visibleEntities=" + visibleEntities +
                '}';
    }

    //------------------------------GETTER-------------------------//
    public Ship getShip() {
        return ship;
    }

    public Vent getWind() {
        return wind;
    }

    public List<VisibleEntitie> getVisibleEntities() {
        return visibleEntities;
    }


    //------------------------------SETTER-------------------------//

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public void setWind(Vent wind) {
        this.wind = wind;
    }

    public void setVisibleEntities(List<VisibleEntitie> visibleEntities) {
        this.visibleEntities = visibleEntities;
    }



}
