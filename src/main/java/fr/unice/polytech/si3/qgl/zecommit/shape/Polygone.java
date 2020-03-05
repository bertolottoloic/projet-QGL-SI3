package fr.unice.polytech.si3.qgl.zecommit.shape;


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

    public double getRadius() {
        double max = 0;
        int nbVertices = vertices.length;
        for (int i = 0; i < nbVertices; i++) {
            int k = vertices.length/2;
            double distance = vertices[i].distanceTo(vertices[(i+1+k)%nbVertices]);
            if(max < distance)
                max = distance;
        }
        return max;
    }
}
