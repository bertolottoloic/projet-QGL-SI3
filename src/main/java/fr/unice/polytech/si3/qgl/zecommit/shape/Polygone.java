package fr.unice.polytech.si3.qgl.zecommit.shape;


public class Polygone extends Shape {

    protected double orientation;
    protected Point[] vertices;


    public Polygone(double orientation,Point[] vertices) {
        super(ShapeType.polygon.toString());
        setCircle(false);
        this.orientation=orientation;
        this.vertices=vertices;
    }

    /////////////////////////////// GETTER //////////////////////////////


    public double getOrientation() {
        return orientation;
    }


    public Point[] getVertices() {
        return vertices;
    }

    public Point getVertice(int index){
        return vertices[index];
    }
}
