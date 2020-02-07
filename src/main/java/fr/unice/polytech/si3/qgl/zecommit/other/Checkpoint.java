package fr.unice.polytech.si3.qgl.zecommit.other;


import fr.unice.polytech.si3.qgl.zecommit.boat.Position;
import fr.unice.polytech.si3.qgl.zecommit.shape.Circle;
import fr.unice.polytech.si3.qgl.zecommit.shape.Shape;

/**
 * Classe indiquant la position et forme d'un checkpoint
 * @author Nathan
 */
public class Checkpoint {
    Position position;
    Shape shape;

    public Checkpoint(Position position,Shape shape){
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

    public boolean isCircle() {
        return shape.isCircle();
    }

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
