package fr.unice.polytech.si3.qgl.zecommit.engine;

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

import java.util.ArrayList;

public class EngineSettings {
    Goal mode;
    ArrayList<Checkpoint> checkpoints;
    Ship ship;
    Deck deck;
    ArrayList<Entity> entities;
    Shape shape;
    ArrayList<Sailor> sailors;
    ArrayList<Entity> visibleEntities;

    EngineSettings(){
        //Liste de checkpoints
        this.checkpoints= new ArrayList<>();
        this.checkpoints.add(new Checkpoint(new Position(100,100,0), new Circle(50)));
        //GameMode
        this.mode= new Regatta(checkpoints);
        //Entitees
        this.entities= new ArrayList<>();
        this.entities.add(new Oar(2,0));
        this.entities.add(new Oar(2,3));
        this.entities.add(new Oar(7,0));
        this.entities.add(new Oar(7,3));
        //Marins
        this.sailors= new ArrayList<>();
        this.sailors.add(new Sailor(0,0,0,"jean"));
        this.sailors.add(new Sailor(1,0,0,"paul"));
        this.sailors.add(new Sailor(2,0,0,"jacques"));
        //Deck
        this.deck=new Deck(4,10);
        //Forme
        this.shape=new Rectangle(4,10,0);
        //Bateau
        this.ship= new Ship(100,new Position(0,0,0),"ZECOMMIT",deck,entities,shape);

        //Entitees visibles
        this.visibleEntities=new ArrayList<>();
    }

}
