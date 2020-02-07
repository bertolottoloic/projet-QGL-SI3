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
    private String type;
    @JsonIgnore
    private boolean isCircle;

    @JsonCreator
    public Shape(@JsonProperty("type")String type){
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
    public String getType() {
        return type;
    }

    //------------------------SETTER----------------------//

    @JsonProperty("type")
    public void setType(String type) {
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
