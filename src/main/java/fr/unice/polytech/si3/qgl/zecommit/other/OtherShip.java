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
    private Shape shape;

    public OtherShip(int life, Position position, Shape shape){
        super(position, shape);
        this.type = "ship";
        this.life = life;


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

    @Override
    public void setShape(Shape shape) {
        this.shape = shape;
    }
}
