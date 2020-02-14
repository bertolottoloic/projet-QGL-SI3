package fr.unice.polytech.si3.qgl.zecommit.other;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Classe correspondant au vent
 * @author Nathan
 */
public class Wind {
    @JsonProperty("orientation") private double orientation;
    @JsonProperty("strength") private double strength;

    @JsonCreator
    public Wind(@JsonProperty("orientation")double orientation, @JsonProperty("strength")double strength) {
        this.orientation = orientation;
        this.strength = strength;
    }

    //------------------------------GETTER-------------------------//

    public double getOrientation() {
        return orientation;
    }

    public double getStrength() {
        return strength;
    }


    //------------------------------SETTER-------------------------//


    public void setOrientation(double orientation) {
        this.orientation = orientation;
    }

    public void setStrength(double strength) {
        this.strength = strength;
    }
}
