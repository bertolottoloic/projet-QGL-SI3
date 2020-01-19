package fr.unice.polytech.si3.qgl.teamid.goal;

import fr.unice.polytech.si3.qgl.teamid.goal.Goal;
import fr.unice.polytech.si3.qgl.teamid.other.Checkpoint;


import java.util.List;

public class Regatta extends Goal {

    private List<Checkpoint> checkpoints;

    Regatta(List<Checkpoint> checkpoint) {
        super("REGATTA");
        this.checkpoints = checkpoint;

    }
}
