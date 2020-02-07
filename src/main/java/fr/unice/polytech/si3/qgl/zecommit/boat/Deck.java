package fr.unice.polytech.si3.qgl.zecommit.boat;

import fr.unice.polytech.si3.qgl.zecommit.entite.Oar;

/**
 * @author Loic Bertolotto
 */
public class Deck{
    private int width;
    private int length;

    public Deck(int width,int length){
        this.width = width;
        this.length = length;
    }


    @Override
    public String toString() {
        return  "width : "+this.width+
                " | height : "+this.length;
    }

    public boolean isLeft(Oar oar){
        if (oar.getY()<width/2)
            return true;
        return false;
    }
    //------------------------------GETTER-------------------------//

    public int getWidth() {
        return width;
    }

    public int getLength() {
        return length;
    }


    //------------------------------SETTER-------------------------//


    public void setWidth(int width) {
        this.width = width;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
