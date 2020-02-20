package fr.unice.polytech.si3.qgl.zecommit.visible;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import fr.unice.polytech.si3.qgl.zecommit.boat.Position;
import fr.unice.polytech.si3.qgl.zecommit.shape.Shape;

/**
 * Classe correspondant aux récifs
 * @author Nathan
 */
public class Reef extends VisibleEntity {

    @JsonCreator
    public Reef(@JsonProperty("position") Position position, @JsonProperty("shape") Shape shape) {
        super(VisibleEntityType.REEF,position, shape);
    }


}

