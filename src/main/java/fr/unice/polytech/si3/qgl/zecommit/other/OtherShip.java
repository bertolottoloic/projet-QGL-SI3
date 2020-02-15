package fr.unice.polytech.si3.qgl.zecommit.other;

import fr.unice.polytech.si3.qgl.zecommit.boat.Deck;
import fr.unice.polytech.si3.qgl.zecommit.boat.Position;
import fr.unice.polytech.si3.qgl.zecommit.entite.Entity;
import fr.unice.polytech.si3.qgl.zecommit.shape.Shape;

import java.util.List;

public class OtherShip extends VisibleEntitie {

    private String type;
    private int life;
    private Position position;
    private String name;
    private Deck deck;
    private List<Entity> entities;
    private Shape shape;

    public OtherShip(int life, Position position, String name,Deck deck, List<Entity> entities,Shape shape){
        super(position, shape);
        this.type = "ship";
        this.life = life;
        this.name = name;
        this.deck = deck;
        this.entities = entities;
    }


    //--------------------GETTER -------------------------//

    public String getType() {
        return type;
    }

    public int getLife() {
        return life;
    }

    @Override
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

    @Override
    public Shape getShape() {
        return shape;
    }


    //--------------------SETTER -------------------------//


    public void setType(String type) {
        this.type = type;
    }

    public void setLife(int life) {
        this.life = life;
    }

    @Override
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

    @Override
    public void setShape(Shape shape) {
        this.shape = shape;
    }
}
