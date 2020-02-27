package fr.unice.polytech.si3.qgl.zecommit.visible;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import fr.unice.polytech.si3.qgl.zecommit.boat.Position;
import fr.unice.polytech.si3.qgl.zecommit.shape.Shape;

/**
 * Classe correspondant aux récifs
 * @author Nathan
 */
public class Reef extends VisibleEntitie {

    @JsonCreator
    public Reef(Position position, Shape shape) {
        super(position, shape);
    }


}

