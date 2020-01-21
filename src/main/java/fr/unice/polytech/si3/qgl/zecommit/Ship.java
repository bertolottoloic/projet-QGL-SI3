package fr.unice.polytech.si3.qgl.zecommit;

import com.fasterxml.jackson.annotation.*;
import fr.unice.polytech.si3.qgl.zecommit.entite.Entite;
import fr.unice.polytech.si3.qgl.zecommit.shape.Shape;

import java.util.List;

/**
 * @author Loic Bertolotto
 */
public class Ship {
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
        this.life = life;
        this.position = position;
        this.name = name;
        this.deck = deck;
        this.entities = entities;
        this.shape = shape;
    }

    @Override
    public String toString() {
        String chaine = "type : " + this.type +
                "\nlife : " + this.life +
                "\nposition : " + this.position +
                "\nname : " + this.name +
                "\ndeck : "+this.deck+
                "\n entities : "+this.entities+
                "\n shape : "+this.shape;
        return chaine;
    }

    // Calcule la distance entre deux positions données
    public int calculDistance(Position position, Position position1) {
        int distance = 0;
        distance = (int)((Math.abs(position.getX()-position1.getX())) + Math.abs(position.getY()-position1.getY()));
        return distance;
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
    public List<Entite> getEntities() {
        return entities;
    }
    @JsonGetter("shape")
    public Shape getShape() {
        return shape;
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
    public void setEntities(List<Entite> entities) {
        this.entities = entities;
    }
    @JsonSetter("shape")
    public void setShape(Shape shape) {
        this.shape = shape;
    }
}