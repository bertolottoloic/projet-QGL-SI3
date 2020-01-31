package fr.unice.polytech.si3.qgl.zecommit;

import fr.unice.polytech.si3.qgl.zecommit.boat.Deck;
import fr.unice.polytech.si3.qgl.zecommit.boat.Position;
import fr.unice.polytech.si3.qgl.zecommit.boat.Ship;
import fr.unice.polytech.si3.qgl.zecommit.entite.Entity;
import fr.unice.polytech.si3.qgl.zecommit.shape.Rectangle;
import fr.unice.polytech.si3.qgl.zecommit.shape.Shape;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
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
        List<Entity> list = new ArrayList<>();
        this.ship = new Ship(0, shipPosition,"zeBoat",deck, list, shape);
    }

    /**
     * calcul dans les negatifs bateau en (0,0)
     */
    @Test
    void distanceToNegatifTest() {
        Position position = new Position(-2,-5,0);

        assertEquals(ship.distanceTo(position), 7);
    }

    /**
     * calcul dans les positifs bateau en (0,0)
     */
    @Test
    void distanceToPositifTest() {
        Position position = new Position(2,5,0);

        assertEquals(ship.distanceTo(position), 7);
    }
}