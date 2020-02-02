/**
 * Definie la position du bateau sur une carte
 * @auteur Clement P
 */

package fr.unice.polytech.si3.qgl.zecommit.boat;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import fr.unice.polytech.si3.qgl.zecommit.deserializer.PositionDeserializer;


@JsonDeserialize(using = PositionDeserializer.class)
public class Position {
    //@JsonProperty("x")
    double x;
    //@JsonProperty("y")
    double y;
    //@JsonProperty("orientation")
    double orientation;

    //@JsonCreator
    public Position(/*@JsonProperty("x")*/double x, /*@JsonProperty("y")*/double y, /*@JsonProperty("orientation")*/double orientation){
        this.x=x;
        this.y=y;
        this.orientation=orientation;
    }

    @Override
    public String toString() {
        return "{ " + this.x + " , " + this.y + " }";
    }
    //------------------------------GETTER-------------------------//

    //@JsonProperty("x")
    public double getX() {
        return x;
    }
    //@JsonProperty("y")
    public double getY() {
        return y;
    }
    //@JsonProperty("orientation")
    public double getOrientation() {
        return orientation;
    }


    //------------------------------SETTER-------------------------//

    //@JsonProperty("x")
    public void setX(double x) {
        this.x = x;
    }

    //@JsonProperty("y")
    public void setY(double y) {
        this.y = y;
    }

    //@JsonProperty("orientation")
    public void setOrientation(double orientation) {
        this.orientation = orientation;
    }

    //-------------------------------------------------------------//

    public double positiveOrientation() {
        if (orientation < 0) return 2*Math.PI+orientation;
        return orientation;
    }

    public double orientationGap(Position cp) {
        if (orientation > 0) {
            //if(x>cp.getPosition().x) return this.orientation+Math.acos(a)
        }
        return 0.0;
    }
}
