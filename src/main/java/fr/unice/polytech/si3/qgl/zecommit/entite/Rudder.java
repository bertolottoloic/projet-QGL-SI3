package fr.unice.polytech.si3.qgl.zecommit.entite;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Classe correspondant au gouvernail du bateau
 * @author Nathan
 */
public class Rudder extends Entities {

    @JsonCreator
    public Rudder(@JsonProperty("x")int x, @JsonProperty("y") int y){
        super("rudder",x,y);
    }


}

