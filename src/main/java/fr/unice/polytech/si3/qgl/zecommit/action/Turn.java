package fr.unice.polytech.si3.qgl.zecommit.action;

public class Turn extends Action{

    private double rotation;

    public Turn(int id, double rotation){
        super(id,"TURN");
        this.rotation = rotation;
    }

    public double getRotation(){
        return this.rotation;
    }
}