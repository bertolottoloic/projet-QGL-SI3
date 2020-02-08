package fr.unice.polytech.si3.qgl.zecommit.action;

import fr.unice.polytech.si3.qgl.zecommit.crew.Sailor;

public class Moving extends Action{

    private int xdistance;
    private int ydistance;

    public Moving(int sailorId, int xdistance, int ydistance){
        super(sailorId, ActionType.MOVING);
        if ((Math.abs(xdistance) + Math.abs(ydistance)) <= 5) {
            this.xdistance = xdistance;
            this.ydistance = ydistance;
        }
        else {
            if (Math.abs(xdistance) <= 5) {
                this.xdistance = xdistance;
                if(ydistance<0)
                    this.ydistance = - Math.min(5-Math.abs(this.xdistance), Math.abs(ydistance));
                else
                    this.ydistance = Math.min(5-Math.abs(this.xdistance), Math.abs(ydistance));
            }
            else {
                if(xdistance<0)
                    this.xdistance = -5;
                else
                    this.xdistance = 5;
                this.ydistance = 0;

            }

        }
       
    }

    //------------------------------GETTER-------------------------//

    public int getXDistance(){
        return this.xdistance;
    }

    public int getYDistance(){
        return this.ydistance;
    }
}
