package fr.unice.polytech.si3.qgl.ZeCommiT.shape;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import fr.unice.polytech.si3.qgl.ZeCommiT.Position;

/**
 * Forme definissant un cercle
 * @author  Clement P
 */
public class Circle extends Shape {
    @JsonProperty("radius")private double radius;

    @JsonCreator
    public Circle(@JsonProperty("position")Position centre, @JsonProperty("radius")double radius){
        super("circle", centre);
        this.radius=radius;
    }

    //--------------------GETTER -------------------------//

    /**
     * Getter du rayon du cercle
     * @return radius
     */
    public double getRadius() {
        return radius;
    }

    //--------------------SETTER -------------------------//

    public void setRadius(double radius) {
        this.radius = radius;
    }
}
