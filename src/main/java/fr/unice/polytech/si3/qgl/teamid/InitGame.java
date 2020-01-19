package fr.unice.polytech.si3.qgl.teamid;

import fr.unice.polytech.si3.qgl.teamid.goal.Goal;

import java.util.List;

public class InitGame {
    private Goal goal;
    private List<Position> checkpoints;
    private Ship bateau;
    private List<Sailor> sailors;

    public Goal getGoal() {
        return this.goal;
    }

}
