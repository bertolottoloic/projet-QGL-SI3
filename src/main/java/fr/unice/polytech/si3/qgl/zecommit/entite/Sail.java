package fr.unice.polytech.si3.qgl.zecommit.entite;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

/**
 * Classe correspondant à la voile du bateau
 * @author Nathan
 */
public class Sail extends Entity {

    private boolean openned;

    @JsonCreator
    public Sail(@JsonProperty("x")int x, @JsonProperty("y")int y,@JsonProperty("openned") Boolean openned){
        super(EntityType.SAIL,x,y);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Sail sail = (Sail) o;
        return openned == sail.openned;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), openned);
    }
}

