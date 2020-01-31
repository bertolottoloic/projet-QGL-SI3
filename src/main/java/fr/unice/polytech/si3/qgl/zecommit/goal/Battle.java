package fr.unice.polytech.si3.qgl.zecommit.goal;

import com.fasterxml.jackson.annotation.JsonCreator;

public class Battle extends Goal {

    @JsonCreator
    public Battle() {
        super("BATTLE");
        this.whichMode = false;
    }
}
