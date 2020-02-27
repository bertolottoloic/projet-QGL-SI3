package fr.unice.polytech.si3.qgl.zecommit.other;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import fr.unice.polytech.si3.qgl.zecommit.boat.Deck;
import fr.unice.polytech.si3.qgl.zecommit.boat.Position;
import fr.unice.polytech.si3.qgl.zecommit.deserializer.OtherShipDeserializer;
import fr.unice.polytech.si3.qgl.zecommit.deserializer.ShipDeserializer;
import fr.unice.polytech.si3.qgl.zecommit.entite.Entity;
import fr.unice.polytech.si3.qgl.zecommit.shape.Shape;
import fr.unice.polytech.si3.qgl.zecommit.visible.VisibleEntitie;


import java.util.List;

@JsonDeserialize(using = OtherShipDeserializer.class)
public class OtherShip extends VisibleEntitie {

    private String type;
    private int life;
    private Position position;
    private Shape shape;

    public OtherShip(String type, int life, Position position, Shape shape){
        super(position, shape);
        this.type = type;
        this.life = life;

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


    public void setPosition(Position position) {
        this.position = position;
    }


    public void setShape(Shape shape) {
        this.shape = shape;
    }
}