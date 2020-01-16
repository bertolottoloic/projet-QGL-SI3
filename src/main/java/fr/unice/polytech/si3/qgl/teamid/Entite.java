package fr.unice.polytech.si3.qgl.teamid;

public abstract class Entite {
    private String type;
    private int x;
    private int y;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getType() {
        return type;
    }
}
