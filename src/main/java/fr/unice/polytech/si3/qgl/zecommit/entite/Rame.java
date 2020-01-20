package fr.unice.polytech.si3.qgl.zecommit.entite;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Entite definissant l'objet Rame sur le bateau
 * @author Clement P
 *
 */

public class Rame extends Entite {

    @JsonCreator
    public Rame(@JsonProperty("x")int x, @JsonProperty("y")int y){
        super("oar",x,y);
    }
}
