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
        captainMate = new CaptainMate();
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
        Oar o1 = new Oar(2,0);
        Oar o2 = new Oar(2,3);
        Oar o3 = new Oar(7,0);
        Oar o4 = new Oar(9,3);
        Sailor s1 = new Sailor(0, 0, 3, "Pouce");
        Sailor s2 = new Sailor(1, 1, 0, "Teach");
        Sailor s3 = new Sailor(2,4,2,"barbe");
        Sailor s4 = new Sailor(3,6,0,"barbe");
        List<Entity> oars = Arrays.asList(new Entity[]{o1,o2,o3,o4});
        List<Sailor> sailors = Arrays.asList(new Sailor[]{s1,s2,s3,s4});
        ship = new Ship(100,new Position(0, 0, 0),"boat",new Deck(4, 10),oars,new Rectangle(4, 10, 0));
        captainMate.initAttibuteOarToSailors(sailors, ship);
        captainMate.initMoveSailor(sailors);
        assertTrue(s2.isOnEntity());
        assertTrue(o1.hasSailorOn());
        assertEquals(o1,s2.getEntity());
        System.out.println(s2.getEntity());
        assertTrue(s1.isOnEntity());
        assertTrue(o2.hasSailorOn());
        assertEquals(o2,s1.getEntity());
        assertTrue(s4.isOnEntity());
        assertTrue(o3.hasSailorOn());
        assertEquals(o3,s4.getEntity());
        assertTrue(s3.hasEntity());
        assertFalse(s3.isOnEntity());
        assertTrue(o4.hasSailorOn());
        captainMate.initMoveSailor(sailors);
        assertTrue(s3.isOnEntity());
        assertTrue(o4.hasSailorOn());
        assertEquals(o4, s3.getEntity());
    }

}
