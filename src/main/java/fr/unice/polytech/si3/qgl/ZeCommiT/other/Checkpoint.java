package fr.unice.polytech.si3.qgl.ZeCommiT.other;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import fr.unice.polytech.si3.qgl.ZeCommiT.Position;
import fr.unice.polytech.si3.qgl.ZeCommiT.shape.Shape;
import javafx.geometry.Pos;

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
