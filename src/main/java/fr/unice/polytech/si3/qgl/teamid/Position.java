/**
 * Definie la position du bateau sur une carte
 * @auteur Clement P
 */

package fr.unice.polytech.si3.qgl.teamid;

public class Position {
    private final double x;
    private final double y;

    public Position(double x,double y){
        this.x=x;
        this.y=y;
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
     * ordonne
     * @return y
     */
    public double getY() {
        return y;
    }
}
