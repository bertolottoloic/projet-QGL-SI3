package fr.unice.polytech.si3.qgl.zecommit.boat;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import fr.unice.polytech.si3.qgl.zecommit.Collision;
import fr.unice.polytech.si3.qgl.zecommit.crew.Sailor;
import fr.unice.polytech.si3.qgl.zecommit.deserializer.ShipDeserializer;
import fr.unice.polytech.si3.qgl.zecommit.entite.*;
import fr.unice.polytech.si3.qgl.zecommit.other.Checkpoint;
import fr.unice.polytech.si3.qgl.zecommit.shape.Shape;
import fr.unice.polytech.si3.qgl.zecommit.strategy.Road;

import java.util.ArrayList;
import java.util.List;


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


    @JsonIgnore
    private void createOarlist(){
        this.oars = new ArrayList<>();
        entities.forEach(entity->
        {
            if(entity.getType().equals(EntityType.oar))
                this.oars.add((Oar)entity);
        });
    }

    /**
     * Tri la liste de rames de façon à alterner les rames de gauches et de droites.
     */
    @JsonIgnore
    private void sortOars(){
        List<Oar> oarsLeft = getLeftOars();
        List<Oar> oarsRight = getRightOars();
        List<Oar> oarsSort = new ArrayList<>();
        for(int i=0;i<Math.max(oarsLeft.size(),oarsRight.size());i++){
            if(i<oarsLeft.size())
                oarsSort.add(oarsLeft.get(i));
            if(i<oarsRight.size())
                oarsSort.add(oarsRight.get(i));
        }
        this.oars=oarsSort;
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
        if(Math.abs(angle)>Math.PI/2){
            return false;
        }
        else{
            return true;
        }
    }

    /**
     * Methode qui calcule la distance d'une position par rapport au bateau
     * @param position
     * @return
     */
    public double distanceTo(Position position) {
        return Math.sqrt(Math.pow(this.getXPosition() - position.getX(),2) + Math.pow(this.getYPosition() - position.getY(),2));
    }

    public void putSailorOnDeck(List<Sailor> sailors){
        deck.setSailors(sailors);
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


    @JsonIgnore
    public List<Oar> getOars(){
        return this.oars;
    }

    @JsonIgnore
    public int getOarsNb() {
        return oars.size();
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
    public Rudder getDeckRudder() {
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
