package fr.unice.polytech.si3.qgl.zecommit.other;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import fr.unice.polytech.si3.qgl.zecommit.boat.Position;
import fr.unice.polytech.si3.qgl.zecommit.shape.Shape;

public class OtherShip extends VisibleEntitie {

    private VisibleEntityType type;
    private int life;
    private Position position;
    private Shape shape;

    public OtherShip(int life, Position position, Shape shape){
        super(VisibleEntityType.OTHERSHIP ,position, shape);
        this.life = life;

    }


    //--------------------GETTER -------------------------//

    public VisibleEntityType getType() {
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


    public void setType(VisibleEntityType type) {
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
