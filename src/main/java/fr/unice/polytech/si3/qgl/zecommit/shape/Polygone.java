package fr.unice.polytech.si3.qgl.zecommit.shape;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Polygone extends Shape {

    @JsonProperty("orientation")
    protected double orientation;

    @JsonProperty("vertexes")
    protected Point[] vertexes;


    public Polygone(@JsonProperty("orientation")double orientation,@JsonProperty("vertexes")Point[] vertexes) {
        super("polygon");
        setCircle(false);
        this.orientation=orientation;
        this.vertexes=vertexes;
    }

    /////////////////////////////// GETTER //////////////////////////////


    public double getOrientation() {
        return orientation;
    }

    @Override
    public String getType() {
        return super.getType();
    }

    public Point[] getVertexes() {
        return vertexes;
    }
}
