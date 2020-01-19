package fr.unice.polytech.si3.qgl.teamid.goal;

import fr.unice.polytech.si3.qgl.teamid.Checkpoint;

import java.util.List;

public class Regatta extends Goal {

    private List<Checkpoint> checkpoints;

    public Regatta(String mode, List<Checkpoint> checkpoints) {
        super(mode);
        this.checkpoints = checkpoints;
    }
}
