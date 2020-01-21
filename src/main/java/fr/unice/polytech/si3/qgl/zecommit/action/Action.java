package fr.unice.polytech.si3.qgl.zecommit.action;

/**
 * @author Loic Bertolotto
 */
public abstract class Action{
    int sailorId;
    String type;

    Action(int sailorId, String type){
        this.sailorId = sailorId;
        this.type = type;
    }

    //------------------------------GETTER-------------------------//

    public int getSailorId(){
        return this.sailorId;
    }

    public String getType(){
        return this.type;
    }
}