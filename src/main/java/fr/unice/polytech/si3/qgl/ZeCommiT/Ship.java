package fr.unice.polytech.si3.qgl.ZeCommiT;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import fr.unice.polytech.si3.qgl.ZeCommiT.entite.Entite;
import fr.unice.polytech.si3.qgl.ZeCommiT.shape.Shape;

import java.util.List;

/**
 * @author Loic Bertolotto
 */
class Ship {
    @JsonProperty("type")private String type;
    @JsonProperty("life")private int life;
    @JsonProperty("position")private Position position;
    @JsonProperty("name")private String name;
    @JsonProperty("deck")private Deck deck;
    @JsonProperty("entities")private List<Entite> entities;
    @JsonProperty("shape")private Shape shape;

    @JsonCreator
    Ship(@JsonProperty("life")int life, @JsonProperty("position")Position position, @JsonProperty("name")String name, @JsonProperty("deck")Deck deck, @JsonProperty("entities")List<Entite> entities, @JsonProperty("shape")Shape shape){
        this.type = "ship";
        this.type = type;
        this.life = life;
        this.position = position;
        this.name = name;
        this.deck = deck;
        this.entities = entities;
        this.shape = shape;
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

    public List<Entite> getEntities() {
        return entities;
    }

    public Shape getShape() {
        return shape;
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

    @JsonSetter("Entities")
    public void setEntities(List<Entite> entities) {
        this.entities = entities;
    }
    public void setShape(Shape shape) {
        this.shape = shape;
    }
}