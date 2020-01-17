package fr.unice.polytech.si3.qgl.teamid.entite;

/**
 * Classe mère décrivant les objets présents sur le bateau
 */

public abstract class Entite {
    private String type;
    private int x;
    private int y;

    public Entite(String type, int x, int y) {
        this.type = type;
        this.x = x;
        this.y = y;
    }
    //------------------------------GETTER-------------------------//

    public String getType() {
        return type;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}


