package fr.unice.polytech.si3.qgl.zecommit.shape;


/**
 * Forme definissant un rectangle
 * @author  Clement P
 */


public class Rectangle extends Shape {
    private double width;
    private double height;
    private double orientation;

    public Rectangle(double width,double height, double orientation){
        super(ShapeType.RECTANGLE.toString());

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

    public double getHeight() {
        return height;
    }


    public double getlength() {

        return height;
    }

    public double getWidth() {
        return width;
    }

    public double getOrientation() {
        return orientation;
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
