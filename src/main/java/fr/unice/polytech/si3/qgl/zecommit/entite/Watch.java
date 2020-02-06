package fr.unice.polytech.si3.qgl.zecommit.entite;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Classe correspondant à la vigie du bateau
 * @author Nathan
 */
public class Watch extends Entity {

    @JsonCreator
    public Watch(@JsonProperty("x")int x, @JsonProperty("y")int y){
    super(EntityType.WATCH,x,y);
    }
}

