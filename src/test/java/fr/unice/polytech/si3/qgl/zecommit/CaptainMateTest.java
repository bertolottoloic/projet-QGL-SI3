package fr.unice.polytech.si3.qgl.zecommit;

import fr.unice.polytech.si3.qgl.zecommit.crew.CaptainMate;
import fr.unice.polytech.si3.qgl.zecommit.crew.Sailor;
import fr.unice.polytech.si3.qgl.zecommit.entite.Oar;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class CaptainMateTest {

    Sailor sailor1;
    CaptainMate captainMate;

    @BeforeEach
    void setUp() {
        Logs logs = new Logs();
        sailor1 = new Sailor(1, 0, 0, "sailor1");
        captainMate = new CaptainMate(logs);
    }

    @Test
    void moveSailorCorrecteTest() {
        captainMate.moveSailor(sailor1,2,1);
        assertEquals(2, sailor1.getX());
        assertEquals(1, sailor1.getY());
    }
    @Test
    void moveSailorNotCorrecteTest() {
        captainMate.moveSailor(sailor1,3,3);
        assertEquals(3, sailor1.getX());
        assertEquals(2, sailor1.getY());
    }

    @Test
    void toOarCorrecteTest(){
        Oar oar1= new Oar(sailor1.getX(),sailor1.getY());
        captainMate.toOar(sailor1,oar1);
        assertFalse(captainMate.getActionList().isEmpty());
        assertTrue(oar1.isUsed());
    }

    @Test
    void toOarNotCorrectTest(){
        Oar oar2= new Oar(sailor1.getX()+1,sailor1.getY());
        captainMate.toOar(sailor1,oar2);
        assertTrue(captainMate.getActionList().isEmpty());
        assertFalse(oar2.isUsed());
    }

}
