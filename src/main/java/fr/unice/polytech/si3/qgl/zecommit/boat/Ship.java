package fr.unice.polytech.si3.qgl.zecommit.boat;

import com.fasterxml.jackson.annotation.*;
import fr.unice.polytech.si3.qgl.zecommit.entite.*;
import fr.unice.polytech.si3.qgl.zecommit.strategy.Road;
import fr.unice.polytech.si3.qgl.zecommit.other.Checkpoint;
import fr.unice.polytech.si3.qgl.zecommit.shape.Shape;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Loic Bertolotto
 */
public class Ship {
    @JsonProperty("type")private String type;
    @JsonProperty("life")private int life;
    @JsonProperty("position")private Position position;
    @JsonProperty("name")private String name;
    @JsonProperty("deck")private Deck deck;
    @JsonProperty("entities")private List<Entity> entities;
    @JsonProperty("shape")private Shape shape;
    @JsonIgnore private List<Oar> oars = new ArrayList<>();
    @JsonIgnore private Rudder rudder;
    @JsonIgnore private Sail sail;



    @JsonCreator
    public Ship(@JsonProperty("life")int life, @JsonProperty("position")Position position, @JsonProperty("name")String name, @JsonProperty("deck")Deck deck, @JsonProperty("entities")List<Entity> entities, @JsonProperty("shape")Shape shape){
        this.type = "ship";
        this.life = life;
        this.position = position;
        this.name = name;
        this.deck = deck;
        this.entities = entities;
        this.shape = shape;
        createOarlist();
        sortOars();
        createRudder();
        createSail();
    }

    private void createRudder(){
        entities.forEach(entity ->
        {
            if(entity.getType().equals(EntityType.RUDDER)){
                rudder=(Rudder)entity;
            }
        });
    }
    private void createSail(){
        entities.forEach(entity ->
        {
            if(entity.getType().equals(EntityType.SAIL)){
                sail=(Sail)entity;
            }
        });
    }

    private void createOarlist(){
        entities.forEach(entity->
        {
            if(entity.getType().equals(EntityType.OAR))
                this.oars.add((Oar)entity);
        });
    }

    /**
     * Tri la liste de rames de façon à alterner les rames de gauches et de droites.
     */
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
    public boolean isInCheckpoint(Checkpoint checkpoint) { //TODO à corriger
        //cas avec un cercle
        if (checkpoint.isCircle() && this.distanceTo(checkpoint.getPosition()) < checkpoint.getCircleRadius()) {
            //Si le centre du bateau est dans le CP
            return true;
        }
        return false;
    }

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

    @JsonGetter("type")
    public String getType() {
        return type;
    }
    @JsonGetter("life")
    public int getLife() {
        return life;
    }
    @JsonGetter("position")
    public Position getPosition() {
        return position;
    }
    @JsonGetter("name")
    public String getName() {
        return name;
    }
    @JsonGetter("deck")
    public Deck getDeck() {
        return deck;
    }
    @JsonGetter("entities")
    public List<Entity> getEntities() {
        return entities;
    }
    @JsonGetter("shape")
    public Shape getShape() {
        return shape;
    }

    @JsonIgnore
    /**
     * Retourne la position x du bateau
     * @return
     */
    public double getXPosition() {
        return this.getPosition().getX();
    }

    @JsonIgnore
    /**
     * Retourne la position y du bateau
     * @return
     */
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

    @JsonIgnore
    public Rudder getRudder() {
        return rudder;
    }

    @JsonIgnore
    /**
     * 
     * @return la liste des rames à gauche du bateau.
     */
    public List<Oar> getLeftOars(){
        ArrayList<Oar> oarsList = new ArrayList<>();
        this.oars.forEach(oar->
        {
            if(deck.isLeft(oar))
                oarsList.add(oar);
        });
        return oarsList;
    }

    @JsonIgnore
    /**
     * 
     * @return la liste des rames à droite du bateau.
     */
    public List<Oar> getRightOars(){
        ArrayList<Oar> oarsList = new ArrayList<>();
        this.oars.forEach(oar->
        {
            if(!deck.isLeft(oar))
                oarsList.add(oar);
        });
        return oarsList;
    }

    public Sail getSail(){
        return sail;
    }


    //------------------------------SETTER-------------------------//

    @JsonSetter("type")
    public void setType(String type) {
        this.type = type;
    }
    @JsonSetter("life")
    public void setLife(int life) {
        this.life = life;
    }
    @JsonSetter("position")
    public void setPosition(Position position) {
        this.position = position;
    }
    @JsonSetter("name")
    public void setName(String name) {
        this.name = name;
    }
    @JsonSetter("deck")
    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    @JsonSetter("entities")
    public void setEntities(List<Entity> entities) {
        this.entities = entities;
    }
    @JsonSetter("shape")
    public void setShape(Shape shape) {
        this.shape = shape;
    }
}
