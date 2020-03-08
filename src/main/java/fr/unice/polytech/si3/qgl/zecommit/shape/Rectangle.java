package fr.unice.polytech.si3.qgl.zecommit.shape;


import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Objects;

/**
 * Forme definissant un rectangle
 * @author  Clement P
 */


public class Rectangle extends Shape {
    private double width; //largeur
    private double height; // longueur
    private double orientation;

    public Rectangle(double width,double height, double orientation){
        super(ShapeType.rectangle.toString());
        setCircle(false);
        setRectangle(true);
        if(width<height) {
            this.width = width;
            this.height = height;
        }
        else { // si longueur et largeur ont été inversées dans le constructeur
            this.width = height;
            this.height = width;
        }
        this.orientation=orientation;
    }



    @Override
    public String toString() {
        return "type : "+super.getType()+
                " [ width : "+this.width+" , height : "+this.height+" , orientation : "+this.orientation+" ] ";
    }


    @Override
    public int hashCode() {
        return Objects.hash(width, height);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Rectangle)) {
            return false;
        }
        Rectangle rectangle = (Rectangle) obj;
        return(rectangle.width == this.width && rectangle.height == this.height&&rectangle.orientation==this.orientation);
    }
    //-------------------------GETTER-------------------------//

    public double getHeight() {
        return height;//longueur
    }

    @JsonIgnore
    public double getlength() {
        return height;
    }

    public double getWidth() {
        return width;
    } //largeur

    public double getOrientation() {
        return orientation;
    }

    @JsonIgnore
    public double getRadius(){
        return getHeight()/2;
    }

    //-------------------------SETTER-------------------------//


    public void setWidth(double width) {
        this.width = width;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setlength(double height) {
        this.height = height;
    }

    public void setOrientation(double orientation) {
        this.orientation = orientation;
    }
}
