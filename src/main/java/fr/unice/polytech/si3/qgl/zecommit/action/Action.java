package fr.unice.polytech.si3.qgl.zecommit.action;

/**
 * @author Loic Bertolotto
 */
public abstract class Action{
    int sailorId;
    ActionType type;

    Action(int sailorId, ActionType type){
        this.sailorId = sailorId;
        this.type = type;
    }

    //------------------------------GETTER-------------------------//

    public int getSailorId(){
        return this.sailorId;
    }

    public ActionType getType(){
        return this.type;
    }



}
