package fr.unice.polytech.si3.qgl.zecommit.action;

public class Moving extends Action{

    private int xdistance;
    private int ydistance;

    public Moving(int id, int xdistance, int ydistance){
        super(id, "MOVING");
        if ((xdistance + ydistance) <= 5) {
            this.xdistance = xdistance;
            this.ydistance = ydistance;
        }
        else {
            this.xdistance = Math.max(xdistance, 5);
            this.ydistance = Math.max(ydistance, 5);
        }
    }


    public int getXDistance(){
        return this.xdistance;
    }

    public int getYDistance(){
        return this.ydistance;
    }
}
