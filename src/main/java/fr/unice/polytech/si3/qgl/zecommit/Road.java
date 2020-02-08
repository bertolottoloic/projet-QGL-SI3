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

    public double xDistanceToGoal(){
        return Math.abs(startPosition.getX()-finishPosition.getX());
    }
    public double yDistanceToGoal(){
        return Math.abs(startPosition.getY()-finishPosition.getY());
    }

    public double distanceToGoal(){
        return Math.sqrt(Math.pow(xDistanceToGoal(),2)+Math.pow(yDistanceToGoal(),2));
    }


    /**
     * Retourne l'angle entre l'oriantation du bateau et l'objectif.
     * Si le bateau est parfaitement orienté avec l'objectif, on regarde s'il point vers l'objectif ou s'en éloigne
     * @return
     */
    public double orientationToGoal() {
        double angle = shipOrientation;
        double x = (finishPosition.getX() - startPosition.getX());
        double y = (finishPosition.getY() - startPosition.getY());
        if (x == 0 && y == 0) {
            return 0;

        }
        if (x == 0) {
            if (y > 0)
                angle = Math.PI / 2;
            if (y < 0)
                angle = -Math.PI / 2;
        } else {
            angle = shortestAngle(Math.atan(y / x));
        }
        System.out.println(adjustAngle(angle));
        return adjustAngle(angle);
    }


    public double adjustAngle(double angle) {
        if (finishPosition.getX() < startPosition.getX() && finishPosition.getY() <= startPosition.getY()) {
            angle -= Math.PI;
            angle -= shipOrientation;
        }
        if (finishPosition.getX() < startPosition.getX() && finishPosition.getY() > startPosition.getY()) {
            angle += Math.PI - shipOrientation;
        }
        if (finishPosition.getX() >= startPosition.getX() && finishPosition.getY() < startPosition.getY()) {
            angle -= shipOrientation;
        }
        if (finishPosition.getX() >= startPosition.getX() && finishPosition.getY() >= startPosition.getY()) {
            angle -= shipOrientation;
        }
        return angle;
    }

    /**
     * true si bateau orienté vers object
     * false si bateau orienté dans le sens opposé
     * @return
     */
    public boolean isGoodAngle(){
        shipOrientation+=Math.PI/36;
        double angle =  shortestAngle(Math.atan((finishPosition.getY()-startPosition.getY())/(finishPosition.getX()-startPosition.getX()))-shipOrientation);
        if(angle>0)
            return true;
        return false;

    }

    /**
     * Permet de virer d'un angle le plus petit possible, évite de faire un virage trop grand
     * @param angle
     * @return
     */
    public double shortestAngle(double angle){
        if(angle>Math.PI)
            return angle - (2*Math.PI);
        if(angle< -Math.PI)
            return angle + (2*Math.PI);
        return angle;


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
