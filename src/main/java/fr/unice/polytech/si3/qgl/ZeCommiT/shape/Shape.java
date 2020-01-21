package fr.unice.polytech.si3.qgl.ZeCommiT.shape;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.unice.polytech.si3.qgl.ZeCommiT.Position;



import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Rectangle.class, name = "rectangle"),
        @JsonSubTypes.Type(value = Circle.class, name = "circle")
})

/**
 * Model de Forme
 * @author Clement P
 */
public abstract class Shape {
    private String type;

    @JsonIgnore
    private Position centre;

    public Shape(String type, Position centre){
        this.type=type;
        this.centre=centre;
    }


    //------------------------GETTER----------------------//
    public String getType() {
        return type;
    }

    public Position getCentre() {
        return centre;
    }

    //------------------------SETTER----------------------//


    public void setType(String type) {
        this.type = type;
    }

    public void setCentre(Position centre) {
        this.centre = centre;
    }
}
