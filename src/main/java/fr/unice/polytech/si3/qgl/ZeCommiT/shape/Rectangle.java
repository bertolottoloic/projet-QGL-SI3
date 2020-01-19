package fr.unice.polytech.si3.qgl.ZeCommiT.shape;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import fr.unice.polytech.si3.qgl.ZeCommiT.Position;

/**
 * Forme definissant un rectangle
 * @author  Clement P
 */

public class Rectangle extends Shape {
    @JsonProperty("width")private double width;
    @JsonAlias({"length", "height"})
    private double length;
    @JsonProperty("orientation")private double orientation;

    @JsonCreator
    public Rectangle(@JsonProperty("position")Position centre, @JsonProperty("width")double width, @JsonProperty("length")double length, @JsonProperty("orientation")double orientation){
        super("rectangle", centre);
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

    //-------------------------SETTER-------------------------//


    public void setWidth(double width) {
        this.width = width;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public void setOrientation(double orientation) {
        this.orientation = orientation;
    }
}
