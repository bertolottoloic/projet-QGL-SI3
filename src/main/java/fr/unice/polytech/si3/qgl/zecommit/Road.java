package fr.unice.polytech.si3.qgl.zecommit;

import fr.unice.polytech.si3.qgl.zecommit.boat.Position;


public class Road {

    private Position startPosition;
    private Position finishPosition;
    private double shipOrientation;

    public Road(Position start, Position finish){
        this.shipOrientation=start.getOrientation();
        this.startPosition=start;
        this.finishPosition=finish;
    }

    public double DistanceXToGoal(){
        return Math.abs(startPosition.getX()-finishPosition.getX());
    }
    public double DistanceYToGoal(){
        return Math.abs(startPosition.getY()-finishPosition.getY());
    }

    public double DistanceToGoal(){
        return Math.sqrt(Math.pow(DistanceXToGoal(),2)+Math.pow(DistanceYToGoal(),2));
    }

    public double orientationToGoal(){
        return Math.atan((finishPosition.getY()-startPosition.getY())/(finishPosition.getX()-startPosition.getX()))-shipOrientation;
    }


    //------------------------GETTER----------------------------
    public double getOrientation() {
        return shipOrientation;
    }

    public Position getFinishPosition() {
        return finishPosition;
    }

    public Position getStartPosition() {
        return startPosition;
    }
}
