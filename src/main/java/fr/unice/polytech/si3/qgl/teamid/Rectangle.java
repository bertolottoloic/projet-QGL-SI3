package fr.unice.polytech.si3.qgl.teamid;

public class Rectangle extends Forme {
    private double width;
    private double length;
    private double orientation;

    public Rectangle(String type, double width,double length,double orientation){
        super(type);
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
}
