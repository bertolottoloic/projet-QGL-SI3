package fr.unice.polytech.si3.qgl.zecommit.maths;

import fr.unice.polytech.si3.qgl.zecommit.boat.Position;
import fr.unice.polytech.si3.qgl.zecommit.boat.Ship;
import fr.unice.polytech.si3.qgl.zecommit.crew.Sailor;
import fr.unice.polytech.si3.qgl.zecommit.entite.Sail;
import fr.unice.polytech.si3.qgl.zecommit.other.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Classe permettant de prévoir la prochaine position du bateau
 * @author Nathan
 */
public class Predictions {
    private List<Sailor> leftSailors;
    private List<Sailor> rightSailors;
    private int oarsNb;
    private Ship ship;
    private List<VisibleEntitie> visibleEntities;
    private double rotation;
    private List<Sail> sailArrayList;
    private Wind wind;
    private int nbSailUsed;


    public Predictions(List<Sailor> leftSailors, List<Sailor> rightSailors,Ship ship, List<VisibleEntitie> visibleEntities, double rotation, Wind wind) {
        this.leftSailors = leftSailors;
        this.rightSailors = rightSailors;
        this.oarsNb = ship.getOarsNb();
        this.ship = ship;
        this.visibleEntities = visibleEntities;
        this.rotation = rotation;
        this.sailArrayList = ship.getDeckSails();
        this.wind = wind;
    }

    /**
     * Méthode vérifiant les collisions avec tous les récifs présents
     * @return true en cas de collision
     */
    public boolean checkCollision() {
        boolean res = false;

        List<Reef> reefs = getReefs();

        for (Reef reef : reefs) {
            Collision collision = new Collision(reef.getShape(), reef.getPosition(), predictPosition());
            if (collision.collide()) {
                res = true;
            }
        }
        return res;
    }

    public List<Reef> getReefs() {
        List<Reef> reefs = new ArrayList<>();
        for (VisibleEntitie visibleEntitie : visibleEntities)
            if(visibleEntitie.getType().equals(VisibleEntityType.reef))
                reefs.add((Reef)visibleEntitie);
        return reefs;
    }

    public Position predictPosition() {

        double vitesse = 165 * (double)(leftSailors.size() + rightSailors.size()) / oarsNb;
        vitesse += calculWind();

        double x = vitesse * Math.cos(ship.getPosition().getOrientation()) + ship.getPosition().getX();
        double y = vitesse * Math.sin(ship.getPosition().getOrientation()) + ship.getPosition().getY();

        Stream stream =getCurrentOn();
        if(stream !=null){
            if(stream.getPosition().getOrientation()==ship.getPosition().getOrientation()){
                x+= stream.getStrength()*Math.cos(Math.abs(ship.getPosition().getOrientation()-stream.getPosition().getOrientation()));
                y+= stream.getStrength()*Math.sin(Math.abs(ship.getPosition().getOrientation()-stream.getPosition().getOrientation()));
            }
        }
        return new Position(x, y, angleCalcul());
    }


    public double angleCalcul() {
        double currentOrientation = ship.getPosition().getOrientation();
        double gap = Math.PI / (oarsNb);
        int balanced = rightSailors.size() - leftSailors.size();
        currentOrientation += (balanced * gap);
        currentOrientation += rotation;
        if (currentOrientation < -Math.PI) {
            currentOrientation = 2 * Math.PI + currentOrientation;
        }
        if (currentOrientation > Math.PI) {
            currentOrientation = -2 * Math.PI + currentOrientation;
        }
        return currentOrientation;
    }


    public double calculWind() {
        double value = 0;
        if (!sailArrayList.isEmpty()) {
            value = ((double) nbSailUsed / sailArrayList.size()) * wind.getStrength() *
                    Math.cos(Math.abs(wind.getOrientation()) - Math.abs(ship.getPosition().getOrientation()));
        }
        return value;
    }


    public Stream getCurrentOn(){
        for (VisibleEntitie entity: visibleEntities) {
            Collision collision = new Collision(entity.getShape(),entity.getPosition(),ship.getPosition());
            if(entity.getType()== VisibleEntityType.stream &&collision.collide()){
                return (Stream) entity;
            }
        }
        return null;
    }


}
