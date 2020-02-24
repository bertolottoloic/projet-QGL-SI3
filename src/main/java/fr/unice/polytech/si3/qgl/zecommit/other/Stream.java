package fr.unice.polytech.si3.qgl.zecommit.other;


import fr.unice.polytech.si3.qgl.zecommit.boat.Position;
import fr.unice.polytech.si3.qgl.zecommit.shape.Shape;

import java.util.Objects;

/**
 * Classe modélisant les courants marins
 * @author Nathan
 */
public class Stream extends VisibleEntitie{

    private Position position;
    private Shape shape;
    private double strength;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stream stream = (Stream) o;
        return Double.compare(stream.strength, strength) == 0 &&
                Objects.equals(position, stream.position) &&
                Objects.equals(shape, stream.shape);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, shape, strength);
    }

    public Stream(Position position, Shape shape, double strength) {
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
