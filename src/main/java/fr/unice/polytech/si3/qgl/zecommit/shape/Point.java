package fr.unice.polytech.si3.qgl.zecommit.shape;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Point {
    @JsonProperty("x")
    double x;
    @JsonProperty("y")
    double y;

    Point(@JsonProperty("x")double x,@JsonProperty("y")double y){
        this.x=x;
        this.y=y;
    }

    ///////////////////// GETTER //////////////////////////


    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
