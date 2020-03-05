package fr.unice.polytech.si3.qgl.zecommit.other;

import fr.unice.polytech.si3.qgl.zecommit.boat.Position;
import fr.unice.polytech.si3.qgl.zecommit.shape.Shape;

public class OtherShip extends VisibleEntitie {

    private int life;

    public OtherShip(int life, Position position, Shape shape){
        super(VisibleEntityType.ship ,position, shape);
        this.life = life;

    }


    //--------------------GETTER -------------------------//

    public int getLife() {
        return life;
    }

    //--------------------SETTER -------------------------//

    public void setLife(int life) {
        this.life = life;
    }

}
