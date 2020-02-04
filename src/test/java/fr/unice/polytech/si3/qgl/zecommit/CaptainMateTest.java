package fr.unice.polytech.si3.qgl.zecommit;

import fr.unice.polytech.si3.qgl.zecommit.boat.Deck;
import fr.unice.polytech.si3.qgl.zecommit.boat.Position;
import fr.unice.polytech.si3.qgl.zecommit.boat.Ship;
import fr.unice.polytech.si3.qgl.zecommit.crew.CaptainMate;
import fr.unice.polytech.si3.qgl.zecommit.crew.Sailor;
import fr.unice.polytech.si3.qgl.zecommit.entite.Entity;
import fr.unice.polytech.si3.qgl.zecommit.entite.Oar;
import fr.unice.polytech.si3.qgl.zecommit.shape.Rectangle;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

public class CaptainMateTest {

    Sailor sailor1;
    CaptainMate captainMate;
    Ship ship;

    @BeforeEach
    void setUp() {
        Logs logs = new Logs();
        sailor1 = new Sailor(1, 0, 0, "sailor1");
        captainMate = new CaptainMate(logs);
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
        captainMate.toOar(sailor1, oar1);
        assertFalse(captainMate.getActionList().isEmpty());
        assertTrue(oar1.isUsed());
    }

    @Test
    void toOarNotCorrectTest() {
        Oar oar2 = new Oar(sailor1.getX() + 1, sailor1.getY());
        captainMate.toOar(sailor1, oar2);
        assertTrue(captainMate.getActionList().isEmpty());
        assertFalse(oar2.isUsed());
    }

    @Test
    void initMoveSailorTest(){
        Oar o1 = new Oar(3,0);
        Oar o2 = new Oar(9,0);
        Sailor s1 = new Sailor(0, 0, 0, "Pouce");
        Sailor s2 = new Sailor(1, 0, 0, "Teach");
        List<Entity> oars = Arrays.asList(new Entity[]{o1,o2});
        List<Sailor> sailors = Arrays.asList(new Sailor[]{s1,s2});
        ship = new Ship(100,new Position(0, 0, 0),"boat",new Deck(4, 10),oars,new Rectangle(4, 10, 0));
        captainMate.initMoveSailor(sailors, ship);
        assertTrue(s1.isOnEntity());
        assertTrue(o1.hasSailorOn());
        assertFalse(s2.isOnEntity());
        assertFalse(o2.hasSailorOn());
        captainMate.initMoveSailor(sailors, ship);
        assertTrue(s1.isOnEntity());
        assertTrue(o1.hasSailorOn());
        assertTrue(s2.isOnEntity());
        assertTrue(o2.hasSailorOn());
    }

}
