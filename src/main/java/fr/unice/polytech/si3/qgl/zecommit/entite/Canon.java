package fr.unice.polytech.si3.qgl.zecommit.entite;



/**
 * Entite definissant l'objet Canon sur le bateau
 * @author Nathan
 *
 */

public class Canon extends Entity {
    private boolean loaded;
    private double angle;

    public Canon(int x, int y, boolean loaded, double angle){
        super(EntityType.canon,x,y);
        this.loaded=loaded;
        this.angle=angle;
    }

    public void setLoaded(boolean loaded) {
        this.loaded = loaded;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }
}
