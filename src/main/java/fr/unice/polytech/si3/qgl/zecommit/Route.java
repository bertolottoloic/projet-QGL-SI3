package fr.unice.polytech.si3.qgl.zecommit;

/**
 * Une classe permettant de renseigner les différentes informations pour planifier l'itinéraire
 * @author Nathan
 */
public class Route {
    private double angle;
    private double distanceAuCheckpoint;
    private double vitesse;

    public Route(double angle, double distanceAuCheckpoint, double vitesse) {
        this.angle = angle;
        this.distanceAuCheckpoint = distanceAuCheckpoint;
        this.vitesse = vitesse;
    }


    public double getAngle() {
        return angle;
    }

    public double getDistanceAuCheckpoint() {
        return distanceAuCheckpoint;
    }

    public double getVitesse() {
        return vitesse;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public void setDistanceAuCheckpoint(double distanceAuCheckpoint) {
        this.distanceAuCheckpoint = distanceAuCheckpoint;
    }

    public void setVitesse(double vitesse) {
        this.vitesse = vitesse;
    }
}
