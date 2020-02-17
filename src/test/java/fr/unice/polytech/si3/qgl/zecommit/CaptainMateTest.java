package fr.unice.polytech.si3.qgl.zecommit;

import fr.unice.polytech.si3.qgl.zecommit.boat.Deck;
import fr.unice.polytech.si3.qgl.zecommit.boat.Position;
import fr.unice.polytech.si3.qgl.zecommit.boat.Ship;
import fr.unice.polytech.si3.qgl.zecommit.crew.CaptainMate;
import fr.unice.polytech.si3.qgl.zecommit.crew.Sailor;
import fr.unice.polytech.si3.qgl.zecommit.entite.Entity;
import fr.unice.polytech.si3.qgl.zecommit.entite.Oar;
import fr.unice.polytech.si3.qgl.zecommit.entite.Rudder;
import fr.unice.polytech.si3.qgl.zecommit.entite.Sail;
import fr.unice.polytech.si3.qgl.zecommit.shape.Rectangle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class CaptainMateTest {

    Sailor sailor1;
    CaptainMate captainMate;
    Ship ship;
    List<Entity> entities;
    List<Sailor> sailors;
    Game game;

    @BeforeEach
    void setUp() {
        Logs logs = new Logs();
        Game game = mock(Game.class);
        sailor1 = new Sailor(1, 0, 0, "sailor1");
        captainMate = new CaptainMate(game);
        Oar o1 = new Oar(2,0);
        Oar o2 = new Oar(2,3);

        Sailor s1 = new Sailor(0, 0, 3, "Pouce");
        Sailor s2 = new Sailor(1, 1, 0, "Teach");

        entities = Arrays.asList(new Entity[]{o1,o2});
        sailors = Arrays.asList(new Sailor[]{s1,s2});
        ship = new Ship(100,new Position(0, 0, 0),"boat",new Deck(4, 10),entities,new Rectangle(4, 10, 0));
    }

    @Test
    void moveSailorCorrecteTest() {
        captainMate.moveSailor(sailor1, 2, 1);
        assertEquals(2, sailor1.getX());
        assertEquals(1, sailor1.getY());
    }

    @Test
    void moveSailorNotCorrecteTest() {
        captainMate.moveSailor(sailor1, 3, 3);
        assertEquals(3, sailor1.getX());
        assertEquals(2, sailor1.getY());
    }

    @Test
    void toOarCorrecteTest() {
        Oar oar1 = new Oar(sailor1.getX(), sailor1.getY());
        sailor1.setOnEntity(oar1);
        captainMate.toOar(sailor1, oar1);
        assertFalse(captainMate.getActionList().isEmpty());
    }

    @Test
    void toOarNotCorrectTest() {
        Oar oar2 = new Oar(sailor1.getX() + 1, sailor1.getY());
        captainMate.toOar(sailor1, oar2);
        assertTrue(captainMate.getActionList().isEmpty());
    }

    @Test
    void initAttributeOarToSailorsTest(){
        captainMate.initAttibuteEntityToSailors(sailors, ship);
        assertTrue(entities.get(0).hasSailorOn());
        assertEquals(entities.get(0), sailors.get(1).getEntity());
        assertTrue(entities.get(1).hasSailorOn());
        assertEquals(entities.get(1), sailors.get(0).getEntity());
    }

    @Test
    void initAttributeOarToSailorsWithRudderTest(){
        List<Entity> entities = new ArrayList<>(this.entities);
        List<Sailor> sailors = new ArrayList<>(this.sailors);
        sailors.add(new Sailor(3,4,0,"JP"));
        sailors.add(new Sailor(4, 6, 3, "Paul"));
        sailors.add(new Sailor(5, 9, 0, "Didier"));
        sailors.add(new Sailor(6, 9, 3, "Pascal"));
        entities.add(new Oar(6, 0));
        entities.add(new Oar(8, 3));
        entities.add(new Rudder(8, 1));
        entities.add(new Sail(4, 2,false));
        this.entities = new ArrayList<>(entities);
        this.sailors = new ArrayList<>(sailors);
        ship = new Ship(100, new Position(0, 0, 0), "boat", new Deck(4, 10), this.entities, new Rectangle(4, 10, 0));
        captainMate.initAttibuteEntityToSailors(this.sailors, ship);
        assertTrue(entities.get(0).hasSailorOn());
        assertEquals(entities.get(0), sailors.get(1).getEntity());
        assertTrue(entities.get(1).hasSailorOn());
        assertEquals(entities.get(1), sailors.get(0).getEntity());
        assertTrue(entities.get(2).hasSailorOn());
        assertEquals(entities.get(2), sailors.get(2).getEntity());
        assertTrue(entities.get(3).hasSailorOn());
        assertEquals(entities.get(3), sailors.get(5).getEntity());
        assertTrue(entities.get(4).hasSailorOn());
        assertEquals(entities.get(4), sailors.get(4).getEntity());
        assertTrue(entities.get(5).hasSailorOn());
        assertEquals(entities.get(5), sailors.get(3).getEntity());
    }

    @Test
    void initMoveSailorFirstStepTest() {
        captainMate.initAttibuteEntityToSailors(sailors, ship);
        captainMate.initMoveSailor(sailors);
        assertTrue(sailors.get(1).isOnEntity());
        assertTrue(sailors.get(0).isOnEntity());
    }

    @Test
    void initMoveSailorSecondStepTest() {
        Oar o4 = new Oar(9, 3);
        Sailor s3 = new Sailor(2, 4, 2, "barbe");
        List<Entity> oars = Arrays.asList(new Entity[] { o4 });
        List<Sailor> sailors = Arrays.asList(new Sailor[] { s3 });
        ship = new Ship(100, new Position(0, 0, 0), "boat", new Deck(4, 10), oars, new Rectangle(4, 10, 0));
        captainMate.initAttibuteEntityToSailors(sailors, ship);
        captainMate.initMoveSailor(sailors);
        assertTrue(s3.hasEntity());
        assertFalse(s3.isOnEntity());
        assertTrue(o4.hasSailorOn());
        captainMate.initMoveSailor(sailors);
        assertTrue(s3.isOnEntity());
        assertTrue(o4.hasSailorOn());
        assertEquals(o4, s3.getEntity());
    }

    @Test
    void toLiftSailTest() {
        Sail sail = new Sail(1,1, false);

    }

}
