/**
 * Definie la position du bateau sur une carte
 * @auteur Clement P
 */

package fr.unice.polytech.si3.qgl.zecommit.boat;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import fr.unice.polytech.si3.qgl.zecommit.deserializer.PositionDeserializer;

@JsonDeserialize(using = PositionDeserializer.class)
public class Position {

    double x;
    double y;
    double orientation;

    public Position(double x, double y, double orientation){
        this.x=x;
        this.y=y;
        this.orientation=orientation;
    }

    @Override
    public String toString() {
        return "{ " + this.x + " , " + this.y + " }";
    }
    //------------------------------GETTER-------------------------//

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getOrientation() {
        return orientation;
    }


    //------------------------------SETTER-------------------------//

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setOrientation(double orientation) {
        this.orientation = orientation;
    }

    //-------------------------------------------------------------//

}
