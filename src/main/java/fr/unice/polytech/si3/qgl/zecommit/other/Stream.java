package fr.unice.polytech.si3.qgl.zecommit.other;


import fr.unice.polytech.si3.qgl.zecommit.boat.Position;
import fr.unice.polytech.si3.qgl.zecommit.shape.Shape;

import java.util.Objects;

/**
 * Classe modélisant les courants marins
 * @author Nathan
 */
public class Stream extends VisibleEntitie {

    private Position position;
    private Shape shape;
    private double strength;

    public Stream(Position position, Shape shape, double strength) {
        super(VisibleEntityType.stream, position, shape);
        this.strength = strength;
    }



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



    //------------------------------GETTER-------------------------//

    public double getStrength() {
        return strength;
    }


    //------------------------------SETTER-------------------------//

    public void setStrength(double strength) {
        this.strength = strength;
    }
}
