package fr.unice.polytech.si3.qgl.zecommit;

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

    public void ramer(){

    }

    @Override
    public String toString(){
        String chaine ="\n[id : "+this.id+
                "\nx : "+this.x+
                "\ny : "+this.y+
                "\nname : "+this.name+"]";
        return chaine;
    }


    /**
     * Déplace le sailor de la distance demandé.
     * Si la distance dépasse 5 l'action est annulée, ceci est prit en charge dans le constructeur de Moving
     * @param xdistance
     * @param ydistance
     */
    public void mouvSailor(int xdistance, int ydistance) {
        Moving action = new Moving(this.getId(), xdistance, ydistance);
        this.setX(this.x + action.getXDistance());
        this.setY(this.y + action.getYDistance());
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
