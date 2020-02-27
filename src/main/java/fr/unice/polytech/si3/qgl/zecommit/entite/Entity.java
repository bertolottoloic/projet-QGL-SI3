package fr.unice.polytech.si3.qgl.zecommit.entite;

/**
 * Classe mère décrivant les objets présents sur le bateau
 */



import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import fr.unice.polytech.si3.qgl.zecommit.boat.Position;
import fr.unice.polytech.si3.qgl.zecommit.crew.Sailor;
import fr.unice.polytech.si3.qgl.zecommit.deserializer.EntityDeserializer;
import fr.unice.polytech.si3.qgl.zecommit.shape.Shape;

import java.util.Objects;

@JsonDeserialize(using = EntityDeserializer.class)
public abstract class Entity {
    private EntityType type;
    private int x;
    private int y;
    @JsonIgnore
    private Sailor sailorOn;

    public Entity(EntityType type,int x, int y) {
        this.type = type;
        this.x = x;
        this.y = y;
        this.sailorOn = null;
    }

    @JsonIgnore
    public boolean hasSailorOn(){
        return this.sailorOn!=null;
    }

    @JsonIgnore
    public void putSailorOn(Sailor sailor){
        this.sailorOn = sailor;
    }

    @Override
    public String toString() {
        return "{ type : " + this.type +
                ", x : " + this.x +
                ", y : " + this.y + " }";
    }

    @Override
    public boolean equals(Object obj) {
        if(obj==this) return true;
        if(obj instanceof Entity){
            Entity e = (Entity)obj;
            return (this.type.equals(e.type) && this.x==e.x && this.y==e.y);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, x, y, sailorOn);
    }

    //------------------------------GETTER-------------------------//

    public EntityType getType() {
        return type;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    /**
     * @return the sailorOn
     */
    @JsonIgnore
    public Sailor getSailorOn() {
        return sailorOn;
    }

    //------------------------------SETTER-------------------------//

    public void setType(EntityType type) {
        this.type = type;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

}


