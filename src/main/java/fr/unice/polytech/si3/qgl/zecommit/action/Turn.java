package fr.unice.polytech.si3.qgl.zecommit.action;

import fr.unice.polytech.si3.qgl.zecommit.Logs;

public class Turn extends Action{

    private double rotation;

    public Turn(int id, double rotation){
        super(id,ActionType.TURN);
        if(rotation<=Math.PI/4&&rotation>=-Math.PI/4) {
            this.rotation = rotation;
            Logs.add("S" + sailorId + ": turn : " + rotation + "\n");
        }
        else{
            this.rotation=0;
        }
    }

    public double getRotation(){
        return this.rotation;
    }
}
