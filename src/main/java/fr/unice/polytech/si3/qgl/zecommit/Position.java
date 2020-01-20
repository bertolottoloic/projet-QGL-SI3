/**
 * Definie la position du bateau sur une carte
 * @auteur Clement P
 */

package fr.unice.polytech.si3.qgl.ZeCommiT;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Position {
    @JsonProperty("x")
    double x;
    @JsonProperty("y")
    double y;
    @JsonProperty("orientation")
    double orientation;

    @JsonCreator
    public Position(@JsonProperty("x")double x, @JsonProperty("y")double y, @JsonProperty("orientation")double orientation){
        this.x=x;
        this.y=y;
        this.orientation=orientation;
    }

    @Override
    public String toString() {
        String chaine = "{ " + this.x + " , " + this.y + " }";
        return chaine;
    }
    //------------------------------GETTER-------------------------//

    @JsonProperty("x")
    public double getX() {
        return x;
    }
    @JsonProperty("y")
    public double getY() {
        return y;
    }
    @JsonProperty("orientation")
    public double getOrientation() {
        return orientation;
    }


    //------------------------------SETTER-------------------------//

    @JsonProperty("x")
    public void setX(double x) {
        this.x = x;
    }

    @JsonProperty("y")
    public void setY(double y) {
        this.y = y;
    }

    @JsonProperty("orientation")
    public void setOrientation(double orientation) {
        this.orientation = orientation;
    }
}
