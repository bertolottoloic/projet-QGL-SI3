package fr.unice.polytech.si3.qgl.zecommit.entite;


/**
 * Classe correspondant à la voile du bateau
 * @author Nathan
 */
public class Sail extends Entity {

    private boolean openned;

    public Sail(int x,int y, Boolean openned){
        super(EntityType.SAIL,x,y);
        this.openned = openned;
    }

    //------------------------------GETTER-------------------------//

    public boolean isOpenned() {
        return openned;
    }

    //------------------------------SETTER-------------------------//

    public void setOpenned(boolean openned) {
        this.openned = openned;
    }
}

