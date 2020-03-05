package fr.unice.polytech.si3.qgl.zecommit.maths;

import fr.unice.polytech.si3.qgl.zecommit.boat.Position;
import fr.unice.polytech.si3.qgl.zecommit.boat.Ship;
import fr.unice.polytech.si3.qgl.zecommit.crew.Sailor;
import fr.unice.polytech.si3.qgl.zecommit.entite.Oar;
import fr.unice.polytech.si3.qgl.zecommit.entite.Sail;
import fr.unice.polytech.si3.qgl.zecommit.other.Stream;
import fr.unice.polytech.si3.qgl.zecommit.other.VisibleEntitie;
import fr.unice.polytech.si3.qgl.zecommit.other.VisibleEntityType;
import fr.unice.polytech.si3.qgl.zecommit.other.Wind;

import java.util.ArrayList;

/**
 * Classe permettant de prévoir la prochaine position du bateau
 * @author Nathan
 */
public class Predictions {
    private ArrayList<Sailor> leftSailors;
    private ArrayList<Sailor> rightSailors;
    private ArrayList<Oar> oarArrayList;
    private Ship ship;
    private ArrayList<VisibleEntitie> visibleEntities;
    private double rotation;
    private ArrayList<Sail> sailArrayList;
    private Wind wind;
    private int nbSailUsed;


    public Predictions(ArrayList<Sailor> leftSailors, ArrayList<Sailor> rightSailors, ArrayList<Oar> oarArrayList, Ship ship, ArrayList<VisibleEntitie> visibleEntities, double rotation, ArrayList<Sail> sailArrayList, Wind wind) {
        this.leftSailors = leftSailors;
        this.rightSailors = rightSailors;
        this.oarArrayList = oarArrayList;
        this.ship = ship;
        this.visibleEntities = visibleEntities;
        this.rotation = rotation;
        this.sailArrayList = sailArrayList;
        this.wind = wind;
    }

    public Position predictPosition() {

        double vitesse = 165 * (leftSailors.size() + rightSailors.size()) / oarArrayList.size();
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
        double gap = Math.PI / (oarArrayList.size());
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
