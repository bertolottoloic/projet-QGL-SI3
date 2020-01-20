package fr.unice.polytech.si3.qgl.ZeCommiT.other;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.unice.polytech.si3.qgl.ZeCommiT.Deck;
import fr.unice.polytech.si3.qgl.ZeCommiT.Position;
import fr.unice.polytech.si3.qgl.ZeCommiT.entite.Entite;
import fr.unice.polytech.si3.qgl.ZeCommiT.shape.Shape;

import java.util.List;

public class OtherShip extends VisibleEntitie {

    @JsonProperty("type")private String type;
    @JsonProperty("life")private int life;
    @JsonProperty("position")private Position position;
    @JsonProperty("name")private String name;
    @JsonProperty("deck")private Deck deck;
    @JsonProperty("entities")private List<Entite> entities;
    @JsonProperty("shape")private Shape shape;

    public OtherShip(@JsonProperty("life")int life, @JsonProperty("position")Position position, @JsonProperty("name")String name, @JsonProperty("deck")Deck deck, @JsonProperty("entities")List<Entite> entities, @JsonProperty("shape")Shape shape){
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

    public List<Entite> getEntities() {
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

    public void setEntities(List<Entite> entities) {
        this.entities = entities;
    }

    @Override
    public void setShape(Shape shape) {
        this.shape = shape;
    }
}
