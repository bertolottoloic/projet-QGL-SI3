package fr.unice.polytech.si3.qgl.ZeCommiT.goal;

import com.fasterxml.jackson.annotation.JsonCreator;

public class Battle extends Goal {

    @JsonCreator
    public Battle() {
        super("BATTLE");
    }
}
