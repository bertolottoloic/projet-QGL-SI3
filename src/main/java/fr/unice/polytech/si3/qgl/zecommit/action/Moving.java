package fr.unice.polytech.si3.qgl.zecommit.action;

public class Moving extends Action{

    private int xdistance;
    private int ydistance;

    public Moving(int id, int xdistance, int ydistance){
        super(id, ActionType.MOVING);
        if ((xdistance + ydistance) <= 5) {
            this.xdistance = xdistance;
            this.ydistance = ydistance;
        }
        else {
            if (xdistance <= 5) {
                this.xdistance = xdistance;
                this.ydistance = Math.min(5 - this.xdistance, ydistance);
            }
            else {
                this.xdistance = 5;
                this.ydistance = 0;

            }

        }
    }


    public int getXDistance(){
        return this.xdistance;
    }

    public int getYDistance(){
        return this.ydistance;
    }
}
