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
    private String type;


    @JsonCreator
    public Shape(@JsonProperty("type")String type){
        this.type=type;
    }


    //------------------------GETTER----------------------//
    @JsonProperty("type")
    public String getType() {
        return type;
    }


    //------------------------SETTER----------------------//

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }
}
