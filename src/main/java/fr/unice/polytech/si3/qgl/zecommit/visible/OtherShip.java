package fr.unice.polytech.si3.qgl.zecommit.visible;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.unice.polytech.si3.qgl.zecommit.boat.Position;
import fr.unice.polytech.si3.qgl.zecommit.shape.Shape;


public class OtherShip extends VisibleEntitie {

    private int life;
    private Position position;
    private Shape shape;

    public OtherShip(@JsonProperty("life")int life, @JsonProperty("position")Position position, @JsonProperty("shape")Shape shape){
        super(position, shape);
        this.life = life;

    }


    //--------------------GETTER -------------------------//


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
