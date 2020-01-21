package fr.unice.polytech.si3.qgl.ZeCommiT.goal;

import fr.unice.polytech.si3.qgl.ZeCommiT.other.Checkpoint;

import java.util.List;

public class Regatta extends Goal {

    private List<Checkpoint> checkpoints;

    public Regatta(List<Checkpoint> checkpoints) {
        super("REGATTA");
        this.checkpoints = checkpoints;
    }
}
