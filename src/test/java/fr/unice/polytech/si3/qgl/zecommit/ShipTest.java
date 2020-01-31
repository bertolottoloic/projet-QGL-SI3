package fr.unice.polytech.si3.qgl.zecommit;

import fr.unice.polytech.si3.qgl.zecommit.boat.Deck;
import fr.unice.polytech.si3.qgl.zecommit.boat.Position;
import fr.unice.polytech.si3.qgl.zecommit.boat.Ship;
import fr.unice.polytech.si3.qgl.zecommit.entite.Entity;
import fr.unice.polytech.si3.qgl.zecommit.other.Checkpoint;
import fr.unice.polytech.si3.qgl.zecommit.shape.Circle;
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
    Shape circle10;

    @BeforeEach
    void setUp() {
        // Création de ship
        Position shipPosition = new Position(0,0,0);
        Deck deck = new Deck(5,5);
        Shape shape = new Rectangle(3,3,0);
        List<Entity> list = new ArrayList<>();

        ship = new Ship(0, shipPosition,"zeBoat",deck, list, shape);

        circle10 = new Circle(10);

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


    /**
     * bateau (0,0) dans le checkpointCibleIn (1,1,cercle:10 radius)
     */
    @Test
    void isInCheckpoint_IN_Test() {
        // Création du checkpointCibleIn
        Position cpPosition = new Position(1,1,0);

        Checkpoint checkpointCibleIn = new Checkpoint(cpPosition, circle10);

        assertTrue(ship.isInCheckpoint(checkpointCibleIn));

    }

    /**
     * bateau (0,0) hors du checkpointcibleOut (15,15,cercle:10 radius)
     */
    @Test
    void isInCheckpoint_OUT_Test() {
        Position cpPosition = new Position(15,15,0);

        Checkpoint checkpointCibleOut = new Checkpoint(cpPosition, circle10);

        assertFalse(ship.isInCheckpoint(checkpointCibleOut));

    }
}