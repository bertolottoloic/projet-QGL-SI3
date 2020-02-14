package fr.unice.polytech.si3.qgl.zecommit.shape;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import fr.unice.polytech.si3.qgl.zecommit.deserializer.ShapeDeserializer;


/**
 * Model de Forme
 * @author Clement P
 */
@JsonDeserialize(using = ShapeDeserializer.class)
public abstract class Shape {
    private String type;
    private boolean isCircle;

    public Shape(String type){
        this.type=type;
        this.isCircle=false;
    }

    public boolean isCircle(){
        return this.isCircle;
    }

    //------------------------GETTER----------------------//
    public String getType() {
        return type;
    }

    //------------------------SETTER----------------------//

    public void setType(String type) {
        this.type = type;
    }

    public void setCircle(boolean circle) {
        isCircle = circle;
    }

    @Override
    public String toString() {
        return "Shape{" + "Type=" + type + "\'" + "}";
    }

}
