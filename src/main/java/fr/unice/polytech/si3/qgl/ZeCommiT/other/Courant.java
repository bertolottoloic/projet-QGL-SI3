package fr.unice.polytech.si3.qgl.ZeCommiT.other;

import com.fasterxml.jackson.annotation.JsonCreator;
import fr.unice.polytech.si3.qgl.ZeCommiT.Position;
import fr.unice.polytech.si3.qgl.ZeCommiT.shape.Shape;

/**
 * Classe modélisant les courants marins
 * @author Nathan
 */
public class Courant {

    private Position position;
    private Shape shape;
    private double strength;

    @JsonCreator
    public Courant(Position position, Shape shape, double strength) {
        this.position = position;
        this.shape = shape;
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
