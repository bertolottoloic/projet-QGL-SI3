package fr.unice.polytech.si3.qgl.teamid;


import fr.unice.polytech.si3.qgl.teamid.entite.Entite;
import fr.unice.polytech.si3.qgl.teamid.other.Vent;

import java.util.List;

/**
 *
 * @author Nathan
 */

public class NextRound {

    private Bateau ship;
    private Vent wind;
    private List<Entite> visibleEntities;

    public NextRound(Bateau ship, Vent wind, List<Entite> visibleEntities) {
        this.ship = ship;
        this.wind = wind;
        this.visibleEntities = visibleEntities;
    }
}
