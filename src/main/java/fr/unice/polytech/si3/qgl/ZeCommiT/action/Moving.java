package fr.unice.polytech.si3.qgl.ZeCommiT.action;

public class Moving extends Action{

    private int xdistance;
    private int ydistance;

    public Moving(int id, int xdistance, int ydistance){
        super(id, "MOVING");
        this.xdistance = xdistance;
        this.ydistance = ydistance;
        //TODO ajouter condition de déplacement max : pas plus de 5 cases
    }

    public int getXDistance(){
        return this.xdistance;
    }

    public int getYDistance(){
        return this.ydistance;
    }
}