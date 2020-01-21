package fr.unice.polytech.si3.qgl.ZeCommiT.entite;

/**
 * Classe mère décrivant les objets présents sur le bateau
 */

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Rame.class, name = "oar"),
        @JsonSubTypes.Type(value = Gouvernail.class, name = "rudder"),
        @JsonSubTypes.Type(value = Vigie.class, name = "watch"),
        @JsonSubTypes.Type(value = Voile.class, name = "sail")

})

public abstract class Entite {
    private String type;
    private int x;
    private int y;

    public Entite(String type, int x, int y) {
        this.type = type;
        this.x = x;
        this.y = y;
    }
    //------------------------------GETTER-------------------------//

    public String getType() {
        return type;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}


