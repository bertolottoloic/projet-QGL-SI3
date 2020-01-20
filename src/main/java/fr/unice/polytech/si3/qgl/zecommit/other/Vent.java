package fr.unice.polytech.si3.qgl.ZeCommiT.other;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Classe correspondant au vent
 * @author Nathan
 */
public class Vent {
    private double orientation;
    private double strength;

    @JsonCreator
    public Vent(double orientation, double strength) {
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
