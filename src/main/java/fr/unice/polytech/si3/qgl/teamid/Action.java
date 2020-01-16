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
}