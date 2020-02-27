package fr.unice.polytech.si3.qgl.zecommit.visible;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import fr.unice.polytech.si3.qgl.zecommit.boat.Position;
import fr.unice.polytech.si3.qgl.zecommit.maths.Road;
import fr.unice.polytech.si3.qgl.zecommit.shape.Shape;

import java.util.Objects;

/**
 * Classe modélisant les courants marins
 * @author Nathan
 */
public class Stream extends VisibleEntitie {
    @JsonProperty("strength")
    private double strength;

    @JsonCreator
    public Stream(@JsonProperty("position") Position position, @JsonProperty("shape") Shape shape, @JsonProperty("strength") double strength) {
        super(position, shape);
        this.strength = strength;
    }

    @Override
    public int hashCode() {
        return Objects.hash(strength,getPosition());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Stream)) {
            return false;
        }
        Stream stream = (Stream) obj;
        return( stream.strength== this.strength && stream.getPosition().equals(this.getPosition()));
    }

    //------------------------------GETTER-------------------------//


    public double getStrength() {
        return strength;
    }


    //------------------------------SETTER-------------------------//


    public void setStrength(double strength) {
        this.strength = strength;
    }


}
