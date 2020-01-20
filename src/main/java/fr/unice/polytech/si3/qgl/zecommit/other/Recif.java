package fr.unice.polytech.si3.qgl.zecommit.other;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import fr.unice.polytech.si3.qgl.zecommit.Position;
import fr.unice.polytech.si3.qgl.zecommit.shape.Shape;

/**
 * Classe correspondant aux récifs
 * @author Nathan
 */
public class Recif extends VisibleEntitie {
    @JsonProperty("position")
    private Position position;
    @JsonProperty("shape")
    private Shape shape;

    @JsonCreator
    public Recif(@JsonProperty("position") Position position,@JsonProperty("shape") Shape shape) {
        super(position, shape);
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

