package fr.unice.polytech.si3.qgl.ZeCommiT.other;

import fr.unice.polytech.si3.qgl.ZeCommiT.Position;
import fr.unice.polytech.si3.qgl.ZeCommiT.shape.Shape;

/**
 * Classe indiquant la position et forme d'un checkpoint
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
