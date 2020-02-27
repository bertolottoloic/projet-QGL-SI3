package fr.unice.polytech.si3.qgl.zecommit.maths;

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

    /**
     * Méthode renvoyant la tranche dans laquelle se situe l'angle souhaité
     */
    public int findClosestPossibleAngle(int oarsNb){
        double angle;
        angle = orientationToGoal();
        if(orientationToGoal()>-Math.PI/4 && orientationToGoal()<Math.PI/4)
            angle = 0;
        double step = Math.PI/(2*oarsNb);
        int res = 0;
        for (int k = 0; k<2*oarsNb; k ++){

            if(k*step-Math.PI/2 <= angle && angle <= (k+1)*step-Math.PI/2 )

                res = k;
        }
        if(turnAroundLeft())
            return oarsNb;
        if(turnAroundRight())
            return 0;

        if(res==0)
            return 0;
        if(res==2*oarsNb-1)
            return oarsNb;
        else
            return (res+1)/2;
    }

    /**
     * demi tour gauche ?
     * @return
     */
    public boolean turnAroundLeft(){
        return (orientationToGoal() > Math.PI/2 && orientationToGoal() <= Math.PI);

    }


    /**
     * demi tour droite ?
     * @return
     */
    public boolean turnAroundRight(){
        return (orientationToGoal() < -Math.PI/2 && orientationToGoal() > -Math.PI);
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
