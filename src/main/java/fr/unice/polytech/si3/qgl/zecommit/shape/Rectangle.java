package fr.unice.polytech.si3.qgl.zecommit.shape;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.lang.reflect.Array;

/**
 * Forme definissant un rectangle
 * @author  Clement P
 */

public class Rectangle extends Polygone {
    @JsonProperty("width")
    private double width;
    @JsonAlias({"length", "height"})
    private double height;


    @JsonCreator
    public Rectangle(@JsonProperty("width")double width, @JsonProperty("height")double height, @JsonProperty("orientation")double orientation){
        super(orientation,null);
        this.width=width;
        this.height=height;
        this.orientation=orientation;
        super.vertexes=vertexes();
    }

    private Point[] vertexes(){
        Point[] points= new Point[4];
        points[0]= new Point(width/2,height/2);
        points[1]= new Point(-width/2,height/2);
        points[2]= new Point(-width/2,-height/2);
        points[3]= new Point(width/2,-height/2);
        return points;
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
