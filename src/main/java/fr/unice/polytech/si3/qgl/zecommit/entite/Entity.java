package fr.unice.polytech.si3.qgl.zecommit.entite;

/**
 * Classe mère décrivant les objets présents sur le bateau
 */



import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import fr.unice.polytech.si3.qgl.zecommit.crew.Sailor;
import fr.unice.polytech.si3.qgl.zecommit.deserializer.EntityDeserializer;

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


