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
            this.xdistance = 0;
            this.ydistance = 0;
        }
        //TODO ajouter condition de déplacement max : pas plus de 5 cases
        //DONE
    }


    public int getXDistance(){
        return this.xdistance;
    }

    public int getYDistance(){
        return this.ydistance;
    }
}