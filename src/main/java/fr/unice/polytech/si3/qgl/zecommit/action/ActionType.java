package fr.unice.polytech.si3.qgl.zecommit.action;

public enum ActionType{
    LIFT_SAIL,LOWER_SAIL,MOVING,OAR,TURN,USE_WATCH;

    public String toString(){
        return this.name();
    }

}