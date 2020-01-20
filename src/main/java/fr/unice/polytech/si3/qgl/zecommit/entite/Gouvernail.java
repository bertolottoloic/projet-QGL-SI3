package fr.unice.polytech.si3.qgl.ZeCommiT.entite;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Classe correspondant au gouvernail du bateau
 * @author Nathan
 */
public class Gouvernail extends Entite {

    @JsonCreator
    public Gouvernail(@JsonProperty("x")int x, @JsonProperty("y") int y){
        super("rudder",x,y);
    }


}

