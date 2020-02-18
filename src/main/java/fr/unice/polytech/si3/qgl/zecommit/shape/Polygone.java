package fr.unice.polytech.si3.qgl.zecommit.shape;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Polygone extends Shape {

    @JsonProperty("orientation")
    protected double orientation;

    @JsonProperty("vertexes")
    protected Point[] vertices;


    public Polygone(@JsonProperty("orientation")double orientation,@JsonProperty("vertexes")Point[] vertices) {
        super(ShapeType.POLYGON);
        setCircle(false);
        this.orientation=orientation;
        this.vertices=vertices;
    }

    public Polygone(ShapeType type,double orientation,double width, double height) {
        super(type);
        setCircle(false);
        this.orientation=orientation;
        this.vertices=buildVertexes(type, width,height);
    }


    public Point[] buildVertexes(ShapeType type,double width, double height){
        Point[] points = new Point[0];
        if(type.equals(ShapeType.RECTANGLE)) {
            points=verticesRectangle(width,height);
        }
        return points;
    }

    private Point[] verticesRectangle(double width, double height){
        Point[] points= new Point[4];
        points[0]= new Point(width/2,height/2);
        points[1]= new Point(-width/2,height/2);
        points[2]= new Point(-width/2,-height/2);
        points[3]= new Point(width/2,-height/2);
        return points;
    }

    /////////////////////////////// GETTER //////////////////////////////


    public double getOrientation() {
        return orientation;
    }

    @Override
    public ShapeType getType() {
        return super.getType();
    }

    public Point[] getVertices() {
        return vertices;
    }
}
