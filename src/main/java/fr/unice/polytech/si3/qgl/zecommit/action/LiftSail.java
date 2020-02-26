package fr.unice.polytech.si3.qgl.zecommit.action;

import fr.unice.polytech.si3.qgl.zecommit.Logs;

public class LiftSail extends Action{
    public LiftSail(int id){
        super(id,ActionType.LIFT_SAIL);
        Logs.add("S" + sailorId + ": liftening sail");
    }
}
