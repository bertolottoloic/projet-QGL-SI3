package fr.unice.polytech.si3.qgl.zecommit.entite;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Classe correspondant à la voile du bateau
 * @author Nathan
 */
public class Sail extends Entity {

    private boolean openned;

    @JsonCreator
    public Sail(@JsonProperty("x")int x, @JsonProperty("y")int y, Boolean openned){
        super("sail",x,y);
        this.openned = openned;
    }

    //------------------------------GETTER-------------------------//

    public boolean isOpenned() {
        return openned;
    }

    //------------------------------SETTER-------------------------//

    public void setOpenned(boolean openned) {
        this.openned = openned;
    }
}

