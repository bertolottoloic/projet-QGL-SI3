package fr.unice.polytech.si3.qgl.zecommit.other;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import fr.unice.polytech.si3.qgl.zecommit.Position;
import fr.unice.polytech.si3.qgl.zecommit.shape.Shape;

/**
 * Classe modélisant les courants marins
 * @author Nathan
 */
public class Courant extends VisibleEntitie{

    @JsonProperty("position")
    private Position position;

    @JsonProperty("shape")
    private Shape shape;

    @JsonProperty("strength")
    private double strength;

    @JsonCreator
    public Courant(@JsonProperty("position") Position position,@JsonProperty("shape") Shape shape,@JsonProperty("strength") double strength) {
        super(position, shape);
        this.strength = strength;
    }

    //------------------------------GETTER-------------------------//

    public Position getPosition() {
        return position;
    }

    public Shape getShape() {
        return shape;
    }

    public double getStrength() {
        return strength;
    }


    //------------------------------SETTER-------------------------//


    public void setPosition(Position position) {
        this.position = position;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    public void setStrength(double strength) {
        this.strength = strength;
    }
}
