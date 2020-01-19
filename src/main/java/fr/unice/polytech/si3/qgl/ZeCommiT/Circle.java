package fr.unice.polytech.si3.qgl.ZeCommiT;

/**
 * Forme definissant un cercle
 * @author  Clement P
 */
public class Circle extends Shape {
    private double radius;

    public Circle(String type,double radius){
        super(type);
        this.radius=radius;
    }

    //--------------------GETTER -------------------------//


    public double getRadius() {
        return radius;
    }
}
