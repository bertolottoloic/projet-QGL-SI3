package fr.unice.polytech.si3.qgl.zecommit.other;


import fr.unice.polytech.si3.qgl.zecommit.boat.Position;
import fr.unice.polytech.si3.qgl.zecommit.shape.Shape;

/**
 * Classe modélisant les courants marins
 * @author Nathan
 */
public class Current extends VisibleEntitie{

    private Position position;
    private Shape shape;
    private double strength;

    public Current(Position position,  Shape shape,  double strength) {
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
