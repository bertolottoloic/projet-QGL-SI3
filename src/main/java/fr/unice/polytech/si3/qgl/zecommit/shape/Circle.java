package fr.unice.polytech.si3.qgl.zecommit.shape;


/**
 * Forme definissant un cercle
 * @author  Clement P
 */
public class Circle extends Shape {
    private double radius;

    public Circle(double radius){
        super("circle");
        this.radius=radius;
        setCircle(true);
    }

    //--------------------GETTER -------------------------//

    /**
     * Getter du rayon du cercle
     * @return radius
     */
    public double getRadius() {
        return radius;
    }

    //--------------------SETTER -------------------------//

    public void setRadius(double radius) {
        this.radius = radius;
    }
}
