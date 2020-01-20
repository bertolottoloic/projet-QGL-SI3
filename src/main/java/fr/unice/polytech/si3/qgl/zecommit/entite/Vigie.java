package fr.unice.polytech.si3.qgl.zecommit.entite;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Classe correspondant à la vigie du bateau
 * @author Nathan
 */
public class Vigie extends Entite {

    @JsonCreator
    public Vigie(@JsonProperty("x")int x, @JsonProperty("y")int y){
        super("watch",x,y);
    }
}

