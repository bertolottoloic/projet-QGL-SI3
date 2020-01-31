package fr.unice.polytech.si3.qgl.zecommit.crew;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import fr.unice.polytech.si3.qgl.zecommit.action.Action;
import fr.unice.polytech.si3.qgl.zecommit.action.Moving;

/**
 * Classe correspondant au marin
 * @author joris Liebgott
 */

public class Sailor {
    @JsonProperty("id")private int id;
    @JsonProperty("x")private int x;
    @JsonProperty("y")private int y;
    @JsonProperty("name")private String name;

    @JsonCreator
    public Sailor(@JsonProperty("id")int id, @JsonProperty("x")int x, @JsonProperty("y")int y, @JsonProperty("name")String name) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.name = name;
    }
    

    @Override
    public String toString(){
        return "\n[id : "+this.id+
                "\nx : "+this.x+
                "\ny : "+this.y+
                "\nname : "+this.name+"]";
    }


    public void move(int xdistance, int ydistance){
        this.x+=xdistance;
        this.y+=ydistance;
    }

    //--------------------GETTER -------------------------//


    public int getId() {
        return id;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getName() {
        return name;
    }


    //------------------------------SETTER-------------------------//


    public void setId(int id) {
        this.id = id;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setName(String name) {
        this.name = name;
    }
}
