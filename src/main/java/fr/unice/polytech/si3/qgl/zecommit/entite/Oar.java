package fr.unice.polytech.si3.qgl.zecommit.entite;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import fr.unice.polytech.si3.qgl.zecommit.boat.Deck;

/**
 * Entite definissant l'objet Rame sur le bateau
 * @author Clement P
 *
 */

public class Oar extends Entity {
    private boolean used;
    @JsonCreator
    public Oar(@JsonProperty("x")int x, @JsonProperty("y")int y){
        super(EntityType.OAR,x,y);
        this.used=false;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }
}
