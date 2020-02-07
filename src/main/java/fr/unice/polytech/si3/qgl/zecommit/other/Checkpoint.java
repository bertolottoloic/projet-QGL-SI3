package fr.unice.polytech.si3.qgl.zecommit.other;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import fr.unice.polytech.si3.qgl.zecommit.boat.Position;
import fr.unice.polytech.si3.qgl.zecommit.shape.Circle;
import fr.unice.polytech.si3.qgl.zecommit.shape.Shape;

/**
 * Classe indiquant la position et forme d'un checkpoint
 * @author Nathan
 */
public class Checkpoint {
    @JsonProperty("position")Position position;
    @JsonProperty("shape")Shape shape;

    @JsonCreator
    public Checkpoint(@JsonProperty("position")Position position, @JsonProperty("shape")Shape shape){
        this.position = position;
        this.shape = shape;
    }

    @Override
    public String toString() {
        return "Checkpoint{" +
                "position=" + position +
                ", shape=" + shape +
                '}';
    }

    @JsonIgnore
    public boolean isCircle() {
        return shape.isCircle();
    }

    @JsonIgnore
    public double getCircleRadius() {
        if (this.isCircle()) {
            Circle circle = (Circle) this.getShape();
            return circle.getRadius();
        }
        return 0;
    }

    //------------------------------GETTER-------------------------//

    public Position getPosition() {
        return position;
    }

    public Shape getShape() {
        return shape;
    }



    //------------------------------SETTER-------------------------//


    public void setPosition(Position position) {
        this.position = position;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }
}
