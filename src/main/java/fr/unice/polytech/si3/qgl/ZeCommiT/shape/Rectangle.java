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
    private double height;
    @JsonProperty("orientation")private double orientation;

    @JsonCreator
    public Rectangle(@JsonProperty("width")double width, @JsonProperty("height")double height, @JsonProperty("orientation")double orientation){
        super("rectangle");
        this.width=width;
        this.height=height;
        this.orientation=orientation;
    }

    //-------------------------GETTER-------------------------//

    @JsonProperty("height")
    public double getHeight() {
        return height;
    }

    @JsonProperty("length")
    public double getlength() {
        return height;
    }

    @JsonProperty("width")
    public double getWidth() {
        return width;
    }

    @JsonProperty("orientation")
    public double getOrientation() {
        return orientation;
    }

    //-------------------------SETTER-------------------------//


    @JsonProperty("width")
    public void setWidth(double width) {
        this.width = width;
    }

    @JsonProperty("height")
    public void setHeight(double height) {
        this.height = height;
    }

    @JsonProperty("length")
    public void setlength(double height) {
        this.height = height;
    }

    @JsonProperty("orientation")
    public void setOrientation(double orientation) {
        this.orientation = orientation;
    }
}
