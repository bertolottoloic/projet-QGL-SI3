package fr.unice.polytech.si3.qgl.zecommit.shape;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

/**
 * Forme definissant un cercle
 * @author  Clement P
 */
public class Circle extends Shape {
    @JsonProperty("radius")private double radius;

    @JsonCreator
    public Circle(@JsonProperty("radius")double radius){
        super(ShapeType.CIRCLE);
        this.radius=radius;
        setCircle(true);
    }

    @Override
    public int hashCode() {
        return Objects.hash(radius);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Circle)) {
            return false;
        }
        Circle circle = (Circle) obj;
        return(circle.radius == this.radius && circle.getType() == this.getType());
    }

    //--------------------GETTER -------------------------//

    /**
     * Getter du rayon du cercle
     * @return radius
     */
    public double getRadius() {
        return radius;
    }

    //--------------------SETTER -------------------------//

    public void setRadius(double radius) {
        this.radius = radius;
    }
}
