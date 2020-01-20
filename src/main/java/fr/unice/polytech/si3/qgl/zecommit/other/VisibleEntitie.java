package fr.unice.polytech.si3.qgl.zecommit.other;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import fr.unice.polytech.si3.qgl.zecommit.Position;
import fr.unice.polytech.si3.qgl.zecommit.entite.Gouvernail;
import fr.unice.polytech.si3.qgl.zecommit.entite.Rame;
import fr.unice.polytech.si3.qgl.zecommit.entite.Vigie;
import fr.unice.polytech.si3.qgl.zecommit.shape.Shape;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Rame.class, name = "stream"),
        @JsonSubTypes.Type(value = Gouvernail.class, name = "reef"),
        @JsonSubTypes.Type(value = Vigie.class, name = "ship"),
})

/**
 * @author Nathan
 */
public abstract class VisibleEntitie {
    @JsonProperty("position")
    Position position;
    @JsonProperty("shape")
    private Shape shape;

    public VisibleEntitie (@JsonProperty("position") Position position,@JsonProperty("shape") Shape shape){
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
    //------------------------------SETTER-------------------------//


    public void setPosition(Position position) {
        this.position = position;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }
}
