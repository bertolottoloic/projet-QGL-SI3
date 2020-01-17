/**
 * Definie la position du bateau sur une carte
 * @auteur Clement P
 */

package fr.unice.polytech.si3.qgl.teamid;

public class Position {
    private final double x;
    private final double y;
    private final double orientation;

    public Position(double x,double y){
        this.x=x;
        this.y=y;
        this.orientation=0;
    }

    //------------------------------GETTER-------------------------//
    /**
     * abscisse
     * @return x
     */
    public double getX() {
        return x;
    }

    /**
     * ordonnée
     * @return y
     */
    public double getY() {
        return y;
    }

    /**
     * orientation
     * @return orientation
     */
    public double getOrientation(){
        return orientation;
    }
}
