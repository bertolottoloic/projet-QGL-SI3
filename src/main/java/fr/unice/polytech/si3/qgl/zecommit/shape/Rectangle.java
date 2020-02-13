package fr.unice.polytech.si3.qgl.zecommit.shape;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Forme definissant un rectangle
 * @author  Clement P
 */

public class Rectangle extends Polygone {
    @JsonProperty("width")
    protected double width;
    @JsonAlias({"length", "height"})
    protected double height;


    @JsonCreator
    public Rectangle(@JsonProperty("width")double width, @JsonProperty("height")double height, @JsonProperty("orientation")double orientation){
        super(ShapeType.RECTANGLE, orientation,width,height);
        this.width=width;
        this.height=height;
        this.orientation=orientation;
    }



    @Override
    public String toString() {
        String chaine = "type : "+super.getType()+
                " [ width : "+this.width+" , height : "+this.height+" , orientation : "+this.orientation+" ] ";
        return chaine;
    }


    //-------------------------GETTER-------------------------//

    @JsonProperty("height")
    public double getHeight() {
        return height;
    }

    @JsonProperty("length")
    @JsonIgnore
    public double getlength() {
        return height;
    }

    @JsonProperty("width")
    public double getWidth() {
        return width;
    }

    @JsonProperty("orientation")
    public double getOrientation() {
        return orientation;
    }

    //-------------------------SETTER-------------------------//


    @JsonProperty("width")
    public void setWidth(double width) {
        this.width = width;
    }

    @JsonProperty("height")
    public void setHeight(double height) {
        this.height = height;
    }

    @JsonProperty("length")
    public void setlength(double height) {
        this.height = height;
    }

    @JsonProperty("orientation")
    public void setOrientation(double orientation) {
        this.orientation = orientation;
    }
}
