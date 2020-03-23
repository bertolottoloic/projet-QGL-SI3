package fr.unice.polytech.si3.qgl.zecommit.boat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import fr.unice.polytech.si3.qgl.zecommit.Logs;
import fr.unice.polytech.si3.qgl.zecommit.crew.Sailor;
import fr.unice.polytech.si3.qgl.zecommit.deserializer.ShipDeserializer;
import fr.unice.polytech.si3.qgl.zecommit.entite.Entity;
import fr.unice.polytech.si3.qgl.zecommit.entite.Oar;
import fr.unice.polytech.si3.qgl.zecommit.entite.Rudder;
import fr.unice.polytech.si3.qgl.zecommit.entite.Sail;
import fr.unice.polytech.si3.qgl.zecommit.maths.Collision;
import fr.unice.polytech.si3.qgl.zecommit.maths.Road;
import fr.unice.polytech.si3.qgl.zecommit.other.Checkpoint;
import fr.unice.polytech.si3.qgl.zecommit.other.Reef;
import fr.unice.polytech.si3.qgl.zecommit.shape.Point;
import fr.unice.polytech.si3.qgl.zecommit.shape.Polygone;
import fr.unice.polytech.si3.qgl.zecommit.shape.Shape;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/**
 * @author Loic Bertolotto
 */
@JsonDeserialize(using = ShipDeserializer.class)
public class Ship {

    private String type;
    private int life;
    private Position position;
    private String name;
    private Deck deck;
    private List<Entity> entities;
    private Shape shape;
    @JsonIgnore
    private List<Oar> oars = new ArrayList<>();


    /**
     *
     *                    v  ~.      v
     *           v           /|
     *                      / |          v
     *               v     /__|__
     *                   \--------/
     * ~~~~~~~~~~~~~~~~~~~`~~~~~~'~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *
     * @param type
     * @param life
     * @param position
     * @param name
     * @param deck
     * @param entities
     * @param shape
     */
    public Ship(String type, int life,Position position,String name, Deck deck, List<Entity> entities,Shape shape){
        this.type = type;
        this.life = life;
        this.position = position;
        this.name = name;
        this.deck = deck;
        this.deck.initDeck(entities);
        this.entities = entities;
        this.shape = shape;

    }

    /**
     * Méthode permettant de savoir si un bateau est arrivé dans le checkpoint
     * cette méthode considère un bateau dans la zone si son centre (et non le bateau entier) est à l'intérieur du checkpoint
     * @param checkpoint
     * @return boolean
     */
    @JsonIgnore
    public boolean isInCheckpoint(Checkpoint checkpoint) {
        Collision collision = new Collision(checkpoint.getShape(), checkpoint.getPosition(), position);
        return collision.collide();
    }

    @JsonIgnore
    public boolean isInFrontOfCheckpoint(Checkpoint checkpoint){
        Position cpPosition=checkpoint.getPosition();
        Road road = new Road(this.position,cpPosition);
        double angle = road.orientationToGoal();
        return Math.abs(angle)<=Math.PI/2;

    }

    /**
     * Methode qui calcule la distance d'une position par rapport au bateau
     * @param position
     * @return
     */
    public double distanceTo(Position position) {
        return this.position.distanceTo(position);
    }


    @JsonIgnore
    public double distanceToforReef(Reef reef){
        if (reef.getShape().isCircle() && !reef.getShape().isRectangle()){
            return distanceTo(reef.getPosition()) - reef.getShape().getShapeRadius();
        }

        if (!reef.getShape().isCircle() && reef.getShape().isRectangle()){
            return distanceTo(reef.getPosition()) - reef.getShape().getShapeRadius();
        }
        else {
            double min = distanceTo(reef.getPosition());
            Polygone poly = (Polygone)(reef.getShape());
            for(Point point : poly.getVertices()){
                double distance = distanceTo(new Position(point.getX() + reef.getPosition().getX(), point.getY()+ reef.getPosition().getY(), 0));
                if(distance<min)
                    min = distance;
            }
            return min;

        }
    }



    @Override
    public String toString() {
        return "type : " + this.type +
                "\nlife : " + this.life +
                "\nposition : " + this.position +
                "\nname : " + this.name +
                "\ndeck : "+this.deck+
                "\n entities : "+this.entities+
                "\n shape : "+this.shape;
    }


    //--------------------GETTER -------------------------//

    public String getType() {
        return type;
    }
    public int getLife() {
        return life;
    }
    public Position getPosition() {
        return position;
    }
    public String getName() {
        return name;
    }
    public Deck getDeck() {
        return deck;
    }
    public List<Entity> getEntities() {
        return entities;
    }
    public Shape getShape() {
        return shape;
    }

    /**
     * Retourne la position x du bateau
     * @return
     */
    @JsonIgnore
    public double getXPosition() {
        return this.getPosition().getX();
    }

    /**
     * Retourne la position y du bateau
     * @return
     */
    @JsonIgnore
    public double getYPosition() {
        return this.getPosition().getY();
    }


    /**
     *
     * @return la liste des rames à gauche du bateau.
     */
    @JsonIgnore
    public List<Oar> getLeftOars(){
        ArrayList<Oar> oarsList = new ArrayList<>();
        this.oars.forEach(oar->
        {
            if(deck.isLeft(oar))
                oarsList.add(oar);
        });
        return oarsList;
    }

    /**
     *
     * @return la liste des rames à droite du bateau.
     */
    @JsonIgnore
    public List<Oar> getRightOars(){
        ArrayList<Oar> oarsList = new ArrayList<>();
        this.oars.forEach(oar->
        {
            if(!deck.isLeft(oar))
                oarsList.add(oar);
        });
        return oarsList;
    }

    /**
     * retourne les marins du deck
     * @return
     */
    @JsonIgnore
    public List<Sailor> getDeckSailors() {
        return this.deck.getSailors();
    }


    /**
     * retourne les rames du deck
     * @return
     */
    @JsonIgnore
    public List<Oar> getDeckOars() {
        return this.deck.getOars();
    }

    /**
     * retourne les voiles du deck
     * @return
     */
    @JsonIgnore
    public List<Sail> getDeckSails() {
        return this.deck.getSails();
    }

    /**
     * retourne le gouvernail du deck
     * @return
     */
    @JsonIgnore
    public Optional<Rudder> getDeckRudder() {
        return this.deck.getRudder();
    }


    //------------------------------SETTER-------------------------//

    public void setType(String type) {
        this.type = type;
    }
    public void setLife(int life) {
        this.life = life;
    }
    public void setPosition(Position position) {
        this.position = position;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public void setEntities(List<Entity> entities) {
        this.entities = entities;
    }
    public void setShape(Shape shape) {
        this.shape = shape;
    }

}
