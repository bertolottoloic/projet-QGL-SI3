package fr.unice.polytech.si3.qgl.zecommit.shape;

import com.fasterxml.jackson.annotation.*;

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
    @JsonProperty("type")
    @JsonIgnore
    private ShapeType type;
    @JsonIgnore
    private boolean isCircle;

    @JsonCreator
    public Shape(@JsonProperty("type")ShapeType type){
        this.type=type;
        this.isCircle=false;
    }

    @JsonIgnore
    public boolean isCircle(){
        return this.isCircle;
    }

    //------------------------GETTER----------------------//
    @JsonProperty("type")
    @JsonIgnore
    public ShapeType getType() {
        return type;
    }

    @JsonIgnore
    public double getRadius() {
        if(isCircle)
            return this.getRadius();
        return ((Rectangle)this).getHeight()/2;
    }

    //------------------------SETTER----------------------//

    @JsonProperty("type")
    public void setType(ShapeType type) {
        this.type = type;
    }

    @JsonIgnore
    public void setCircle(boolean circle) {
        isCircle = circle;
    }

    @Override
    public String toString() {
        return "Shape{" + "Type=" + type + "\'" + "}";
    }

}
