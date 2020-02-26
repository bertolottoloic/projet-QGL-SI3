package fr.unice.polytech.si3.qgl.zecommit.shape;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import fr.unice.polytech.si3.qgl.zecommit.deserializer.ShapeDeserializer;


/**
 * Model de Forme
 * @author Clement P
 */
@JsonDeserialize(using = ShapeDeserializer.class)
public abstract class Shape {
    private String type;
    @JsonIgnore private boolean isCircle;

    public Shape(String type){
        this.type=type;
        this.isCircle=false;
    }
    @JsonIgnore
    public boolean isCircle(){
        return this.isCircle;
    }

    //------------------------GETTER----------------------//

    public String getType() {
        return type;
    }

    @JsonIgnore
    public double getShapeRadius() {
        if(isCircle)
            return ((Circle)this).getRadius();
        return ((Rectangle)this).getHeight()/2;
    }

    //------------------------SETTER----------------------//


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
