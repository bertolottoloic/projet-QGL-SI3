package fr.unice.polytech.si3.qgl.teamid.other;

import fr.unice.polytech.si3.qgl.teamid.Position;
import fr.unice.polytech.si3.qgl.teamid.shape.Shape;

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
