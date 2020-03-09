package fr.unice.polytech.si3.qgl.zecommit.shape;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.unice.polytech.si3.qgl.zecommit.boat.Position;

public class Polygone extends Shape {

    protected double orientation;
    protected Point[] vertices;


    public Polygone(double orientation,Point[] vertices) {
        super(ShapeType.polygon.toString());
        setCircle(false);
        setRectangle(false);
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

    public Point getRelativeVertice(int index, Position position){
        return new Point(vertices[index].getX()+position.getX(), vertices[index].getY()+position.getY());
    }

    public int[] getVerticesIntX(){
        int[] x= new int[this.vertices.length];
        for (int i=0; i<this.vertices.length;i++) {
            x[i]=(int)vertices[i].getX();

        }
        return x;
    }
    public int[] getVerticesIntY(){
        int[] y= new int[this.vertices.length];
        for (int i=0; i<this.vertices.length;i++) {
            y[i]=(int)vertices[i].getY();
        }
        return y;
    }

    @JsonIgnore
    public double getRadius() {
        double max = 0;
        int nbVertices = vertices.length;
        for (int i = 0; i < nbVertices; i++) {
            int k = vertices.length/2;
            double distance = vertices[i].distanceTo(vertices[(i+1+k)%nbVertices]);
            if(max < distance)
                max = distance;
        }
        return max/2 +20; //TODO à revoir
    }
}
