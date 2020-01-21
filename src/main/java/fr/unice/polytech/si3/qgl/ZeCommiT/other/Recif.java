package fr.unice.polytech.si3.qgl.ZeCommiT.other;

import fr.unice.polytech.si3.qgl.ZeCommiT.Position;
import fr.unice.polytech.si3.qgl.ZeCommiT.shape.Shape;

/**
 * Classe correspondant aux récifs
 * @author Nathan
 */
public class Recif {
    private Position position;
    private Shape shape;

    public Recif(Position position, Shape shape) {
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
