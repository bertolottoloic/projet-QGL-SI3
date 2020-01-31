package fr.unice.polytech.si3.qgl.zecommit.shape;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import fr.unice.polytech.si3.qgl.zecommit.deserializer.ShapeDeserializer;

import java.awt.font.ShapeGraphicAttribute;

/*@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Rectangle.class, name = "rectangle"),
        @JsonSubTypes.Type(value = Circle.class, name = "circle")
})

 */

/**
 * Model de Forme
 * @author Clement P
 */
@JsonDeserialize(using = ShapeDeserializer.class)
public abstract class Shape {
    //@JsonProperty("type")
    private String Type;
    protected boolean whichShape;

    //@JsonCreator
    public Shape(/*@JsonProperty("type")*/String type){
        Type=type;
    }

    public boolean isCircle(){
        return this.whichShape;
    }

    public boolean isRectangle(){
        return !this.whichShape;
    }


    //------------------------GETTER----------------------//
    //@JsonProperty("type")
    public String getType() {
        return Type;
    }


    //------------------------SETTER----------------------//

    //@JsonProperty("type")
    public void setType(String type) {
        Type = type;
    }

    @Override
    public String toString() {
        return "Shape{" + "Type=" + Type + "\'" + "}";
    }

}
