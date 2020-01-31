package fr.unice.polytech.si3.qgl.zecommit.action;

public enum ActionType{
    LIFT_SAIL("LIFT_SAIL"),LOWER_SAIL("LOWER_SAIL"),MOVING("MOVING"),OAR("OAR"),TURN("TURN"),USE_WATCH("USE_WATCH");

    private String type;

    ActionType(String type){
        this.type = type;
    }

    public String toString(){
        return type;
    }

}