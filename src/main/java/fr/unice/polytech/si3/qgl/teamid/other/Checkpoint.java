package fr.unice.polytech.si3.qgl.teamid.other;

import fr.unice.polytech.si3.qgl.teamid.Position;
import fr.unice.polytech.si3.qgl.teamid.shape.Shape;

/**
 * Classe indiquant les la position et forme des checkpoints
 * @author Nathan
 */
public class Checkpoint {
    Position position;
    Shape shape;

    public Checkpoint(Position position, Shape shape){
        this.position = position;
        this.shape = shape;
    }

    //------------------------------GETTER-------------------------//

    public Position getPosition() {
        return position;
    }

    public Shape getShape() {
        return shape;
    }
}
