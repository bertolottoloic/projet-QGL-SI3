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
    private ShapeType type;
    private boolean isCircle;

    public Shape(ShapeType type){

        this.type=type;
        this.isCircle=false;
    }

    public boolean isCircle(){
        return this.isCircle;
    }

    //------------------------GETTER----------------------//

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


    public void setType(ShapeType type) {
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
