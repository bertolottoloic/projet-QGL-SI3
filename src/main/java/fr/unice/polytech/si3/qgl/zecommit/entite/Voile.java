package fr.unice.polytech.si3.qgl.ZeCommiT.entite;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Classe correspondant à la voile du bateau
 * @author Nathan
 */
public class Voile extends Entite {

    private boolean openned;

    @JsonCreator
    public Voile(@JsonProperty("x")int x, @JsonProperty("y")int y){
        super("sail",x,y);
        this.openned = false;//par défaut la voile est fermée
    }

    //------------------------------GETTER-------------------------//

    public boolean isOpenned() {
        return openned;
    }

    //------------------------------SETTER-------------------------//

    public void setOpenned(boolean openned) {
        this.openned = openned;
    }
}

