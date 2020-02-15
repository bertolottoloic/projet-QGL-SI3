package fr.unice.polytech.si3.qgl.zecommit.entite;

/**
 * Classe mère décrivant les objets présents sur le bateau
 */



import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import fr.unice.polytech.si3.qgl.zecommit.crew.Sailor;
import fr.unice.polytech.si3.qgl.zecommit.deserializer.EntityDeserializer;

import java.util.Objects;

@JsonDeserialize(using = EntityDeserializer.class)
public abstract class Entity {
    private EntityType type;
    private int x;
    private int y;
    private Sailor sailorOn;

    public Entity(EntityType type,int x, int y) {
        this.type = type;
        this.x = x;
        this.y = y;
        this.sailorOn = null;
    }

    public boolean hasSailorOn(){
        return this.sailorOn!=null;
    }

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
            if(this.type==e.type && this.x==e.x && this.y==e.y){
                return true;
            }
            return false;
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


