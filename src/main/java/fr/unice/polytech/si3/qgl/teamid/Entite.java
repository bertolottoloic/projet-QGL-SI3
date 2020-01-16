package fr.unice.polytech.si3.qgl.teamid;

public abstract class Entite {
    private String type;
    private int x;
    private int y;

    public Entite(String type, int x,int y){
        this.type=type;
        this.x=x;
        this.y=y;
    }

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
