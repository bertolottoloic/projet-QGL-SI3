package fr.unice.polytech.si3.qgl.zecommit.visible;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import fr.unice.polytech.si3.qgl.zecommit.boat.Position;
import fr.unice.polytech.si3.qgl.zecommit.entite.Oar;
import fr.unice.polytech.si3.qgl.zecommit.entite.Rudder;
import fr.unice.polytech.si3.qgl.zecommit.entite.Watch;
import fr.unice.polytech.si3.qgl.zecommit.shape.Shape;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Oar.class, name = "stream"),
        @JsonSubTypes.Type(value = Rudder.class, name = "reef"),
        @JsonSubTypes.Type(value = Watch.class, name = "ship"),
})

/**
 * @author Nathan
 */
public abstract class VisibleEntity {
    @JsonProperty("position")
    private Position position;
    @JsonProperty("shape")
    private Shape shape;
    @JsonProperty("type")
    private VisibleEntityType type;

    public VisibleEntity(@JsonProperty("type") VisibleEntityType type, @JsonProperty("position") Position position, @JsonProperty("shape") Shape shape){
        this.position=position;
        this.shape=shape;
        this.type=type;
    }
    //------------------------------GETTER-------------------------//


    public Position getPosition() {
        return position;
    }

    public Shape getShape() {
        return shape;
    }

    public VisibleEntityType getType() {
        return type;
    }

    //------------------------------SETTER-------------------------//


    public void setPosition(Position position) {
        this.position = position;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    public void setType(VisibleEntityType type) {
        this.type = type;
    }
}
