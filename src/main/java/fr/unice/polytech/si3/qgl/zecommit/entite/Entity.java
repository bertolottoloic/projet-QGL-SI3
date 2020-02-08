package fr.unice.polytech.si3.qgl.zecommit.entite;

/**
 * Classe mère décrivant les objets présents sur le bateau
 */

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import fr.unice.polytech.si3.qgl.zecommit.crew.Sailor;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Oar.class, name = "oar"),
        @JsonSubTypes.Type(value = Rudder.class, name = "rudder"),
        @JsonSubTypes.Type(value = Watch.class, name = "watch"),
        @JsonSubTypes.Type(value = Sail.class, name = "sail")

})

public abstract class Entity {
    @JsonProperty("type")
    @JsonIgnore
    private EntityType type;
    @JsonProperty("x")
    private int x;
    @JsonProperty("y")
    private int y;
    @JsonIgnore private Sailor sailorOn;

    @JsonCreator
    public Entity(@JsonProperty("type") EntityType type, @JsonProperty("x") int x, @JsonProperty("y")int y) {
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
    //------------------------------GETTER-------------------------//

    @JsonIgnore
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


