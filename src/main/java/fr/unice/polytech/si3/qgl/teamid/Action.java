package fr.unice.polytech.si3.qgl.teamid;

/**
 * @author Loic Bertolotto
 */
abstract class Action{
    int sailorId;
    String type;

    Action(int sailorId, String type){
        this.sailorId = sailorId;
        this.type = type;
    }

    public int getSailorId(){
        return this.sailorId;
    }

    public String getType(){
        return this.type;
    }
}