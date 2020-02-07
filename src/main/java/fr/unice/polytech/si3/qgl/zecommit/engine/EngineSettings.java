package fr.unice.polytech.si3.qgl.zecommit.engine;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.unice.polytech.si3.qgl.zecommit.boat.Deck;
import fr.unice.polytech.si3.qgl.zecommit.boat.Position;
import fr.unice.polytech.si3.qgl.zecommit.boat.Ship;
import fr.unice.polytech.si3.qgl.zecommit.crew.Sailor;
import fr.unice.polytech.si3.qgl.zecommit.entite.Entity;
import fr.unice.polytech.si3.qgl.zecommit.entite.Oar;
import fr.unice.polytech.si3.qgl.zecommit.goal.Goal;
import fr.unice.polytech.si3.qgl.zecommit.goal.Regatta;
import fr.unice.polytech.si3.qgl.zecommit.other.Checkpoint;
import fr.unice.polytech.si3.qgl.zecommit.shape.Circle;
import fr.unice.polytech.si3.qgl.zecommit.shape.Rectangle;
import fr.unice.polytech.si3.qgl.zecommit.shape.Shape;

import java.io.IOException;
import java.util.ArrayList;
import java.util.jar.JarException;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class EngineSettings {
    Goal goal;
    ArrayList<Checkpoint> checkpoints;
    Ship ship;
    Deck deck;
    ArrayList<Entity> entities;
    Shape shape;
    ArrayList<Sailor> sailors;
    ArrayList<Entity> visibleEntities;
    @JsonIgnore
    private ObjectMapper oM;

    EngineSettings(){
        //Liste de checkpoints
        setCheckpoints();
        //GameMode
        setGoal();
        //Entitees
        setEntities();
        //Marins
        setSailors();
        //Deck
        setDeck();
        //Forme
        setShape();
        //Bateau
        setShip();
        //Entitees visibles
        setVisibleEntities();
        this.oM = new ObjectMapper();
    }

    public static void main( String[]args){
        EngineSettings engineSettings= new EngineSettings();
        System.out.println(engineSettings.thisToJson());
    }

    public String thisToJson(){
        try{
            oM.configure(SerializationFeature.INDENT_OUTPUT, true);
            return oM.writeValueAsString(this);
        } catch(IOException e ) { 
            System.err.println(e);
            return "{}";
        }
    }

    //--------------------SETTINGS-------------------//

    public void setShip() {
        this.ship= new Ship(100,new Position(0,0,0),"ZECOMMIT",deck,entities,shape);
    }

    public void setVisibleEntities() {
        this.visibleEntities=new ArrayList<>();
    }

    public void setSailors() {
        this.sailors= new ArrayList<>();
        this.sailors.add(new Sailor(0,0,0,"jean"));
        this.sailors.add(new Sailor(1,0,0,"paul"));
        this.sailors.add(new Sailor(2,0,0,"jacques"));
    }

    public void setGoal() {
        this.goal= new Regatta(checkpoints);
    }

    public void setCheckpoints() {
        this.checkpoints= new ArrayList<>();
        this.checkpoints.add(new Checkpoint(new Position(100,100,0), new Circle(50)));
    }

    public void setDeck() {
        this.deck=new Deck(4,10);
    }

    public void setEntities() {
        this.entities= new ArrayList<>();
        this.entities.add(new Oar(2,0));
        this.entities.add(new Oar(2,3));
        this.entities.add(new Oar(7,0));
        this.entities.add(new Oar(7,3));
    }

    public void setShape() {
        this.shape=new Rectangle(4,10,0);
    }

    //---------------------Getter----------------------//

    public Goal getGoal(){
        return this.goal;
    }

    @JsonIgnore
    /**
     * @return the checkpoints
     */
    public ArrayList<Checkpoint> getCheckpoints() {
        return checkpoints;
    }

    /**
     * @return the ship
     */
    public Ship getShip() {
        return ship;
    }

    @JsonIgnore
    /**
     * @return the deck
     */
    public Deck getDeck() {
        return deck;
    }

    @JsonIgnore
    /**
     * @return the entities
     */
    public ArrayList<Entity> getEntities() {
        return entities;
    }

    @JsonIgnore
    /**
     * @return the shape
     */
    public Shape getShape() {
        return shape;
    }

    /**
     * @return the sailors
     */
    public ArrayList<Sailor> getSailors() {
        return sailors;
    }

    @JsonIgnore
    /**
     * @return the visibleEntities
     */
    public ArrayList<Entity> getVisibleEntities() {
        return visibleEntities;
    }

}
