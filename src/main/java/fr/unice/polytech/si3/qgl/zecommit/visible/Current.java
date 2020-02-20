package fr.unice.polytech.si3.qgl.zecommit.visible;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import fr.unice.polytech.si3.qgl.zecommit.boat.Position;
import fr.unice.polytech.si3.qgl.zecommit.shape.Shape;

/**
 * Classe modélisant les courants marins
 * @author Nathan
 */
public class Current extends VisibleEntity {
    @JsonProperty("strength")
    private double strength;

    @JsonCreator
    public Current(@JsonProperty("position") Position position, @JsonProperty("shape") Shape shape, @JsonProperty("strength") double strength) {
        super(VisibleEntityType.CURRENT,position, shape);
        this.strength = strength;
    }

    //------------------------------GETTER-------------------------//


    public double getStrength() {
        return strength;
    }


    //------------------------------SETTER-------------------------//


    public void setStrength(double strength) {
        this.strength = strength;
    }
}
