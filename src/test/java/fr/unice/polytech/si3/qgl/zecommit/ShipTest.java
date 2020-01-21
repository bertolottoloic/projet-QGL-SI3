package fr.unice.polytech.si3.qgl.zecommit;

import fr.unice.polytech.si3.qgl.zecommit.entite.Entite;
import fr.unice.polytech.si3.qgl.zecommit.entite.Rame;
import fr.unice.polytech.si3.qgl.zecommit.shape.Circle;
import fr.unice.polytech.si3.qgl.zecommit.shape.Rectangle;
import fr.unice.polytech.si3.qgl.zecommit.shape.Shape;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author joris Liebgott
 */
class ShipTest {

    Ship ship;

    @BeforeEach
    void setUp() {
        Position shipPosition = new Position(0,0,0);
        Deck deck = new Deck(5,5);
        Shape shape = new Rectangle(3,3,0);
        List<Entite> list = null;
        this.ship = new Ship(0, shipPosition,"zeBoat",deck, list, shape);
    }

    @Test
    void calculDistance() {
        Position position = new Position(3,5,0);
        Position position1 = new Position(1,4,0);

        assertEquals(ship.calculDistance(position,position1), 3);
    }

    @Test
    void calculDistance2() {
        Position position = new Position(0,0,0);
        Position position1 = new Position(8,4,0);

        assertEquals(ship.calculDistance(position,position1), 12);
    }
}