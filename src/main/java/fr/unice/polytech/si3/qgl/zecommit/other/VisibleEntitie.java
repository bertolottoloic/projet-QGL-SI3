package fr.unice.polytech.si3.qgl.zecommit.other;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import fr.unice.polytech.si3.qgl.zecommit.boat.Position;
import fr.unice.polytech.si3.qgl.zecommit.deserializer.VisibleEntitiesDeserializer;
import fr.unice.polytech.si3.qgl.zecommit.other.VisibleEntityType;
import fr.unice.polytech.si3.qgl.zecommit.shape.Shape;



/**
 * @author Nathan
 */
@JsonDeserialize(using = VisibleEntitiesDeserializer.class)
public abstract class VisibleEntitie {
    private Position position;
    private Shape shape;
    private VisibleEntityType type;

    public VisibleEntitie (VisibleEntityType type, Position position,Shape shape){
        this.type=type;
        this.position=position;
        this.shape=shape;
    }
    //------------------------------GETTER-------------------------//


    public Position getPosition() {
        return position;
    }

    public Shape getShape() {
        return shape;
    }

    @JsonIgnore
    public VisibleEntityType getType(){return this.type;}
    //------------------------------SETTER-------------------------//


    public void setPosition(Position position) {
        this.position = position;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }


}
