package fr.unice.polytech.si3.qgl.ZeCommiT.shape;

import fr.unice.polytech.si3.qgl.ZeCommiT.Position;

/**
 * Forme definissant un rectangle
 * @author  Clement P
 */
public class Rectangle extends Shape {
    private double width;
    private double length;
    private double orientation;

    public Rectangle(Position centre, double width,double length,double orientation){
        super("rectangle", centre);
        this.width=width;
        this.length=length;
        this.orientation=orientation;
    }

    //-------------------------GETTER-------------------------//


    public double getLength() {
        return length;
    }

    public double getOrientation() {
        return orientation;
    }

    public double getWidth() {
        return width;
    }

    //-------------------------SETTER-------------------------//


    public void setWidth(double width) {
        this.width = width;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public void setOrientation(double orientation) {
        this.orientation = orientation;
    }
}
