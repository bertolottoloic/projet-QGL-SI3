package fr.unice.polytech.si3.qgl.zecommit.boat;

import fr.unice.polytech.si3.qgl.zecommit.Road;
import fr.unice.polytech.si3.qgl.zecommit.entite.Entity;
import fr.unice.polytech.si3.qgl.zecommit.entite.EntityType;
import fr.unice.polytech.si3.qgl.zecommit.entite.Oar;
import fr.unice.polytech.si3.qgl.zecommit.other.Checkpoint;
import fr.unice.polytech.si3.qgl.zecommit.shape.Shape;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Loic Bertolotto
 */
public class Ship {
    private String type;
    private int life;
    private Position position;
    private String name;
    private Deck deck;
    private List<Entity> entities;
    private Shape shape;
    private List<Oar> oars = new ArrayList<>();

    public Ship(String type, int life,Position position,String name, Deck deck, List<Entity> entities,Shape shape){
        this.type = type;
        this.life = life;
        this.position = position;
        this.name = name;
        this.deck = deck;
        this.entities = entities;
        this.shape = shape;
        createOarlist();
        sortOars();
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
    public double getXPosition() {
        return this.getPosition().getX();
    }

    /**
     * Retourne la position y du bateau
     * @return
     */
    public double getYPosition() {
        return this.getPosition().getY();
    }

    public List<Oar> getOars(){
        return this.oars;
    }

    public int getOarsNb() {
        return oars.size();
    }

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
