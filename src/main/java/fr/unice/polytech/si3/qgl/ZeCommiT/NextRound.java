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
}
