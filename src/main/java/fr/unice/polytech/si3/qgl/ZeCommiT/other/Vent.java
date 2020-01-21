package fr.unice.polytech.si3.qgl.ZeCommiT.other;

/**
 * Classe correspondant au vent
 * @author Nathan
 */
public class Vent {
    private double orientation;
    private double strength;

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
}
