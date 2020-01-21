package fr.unice.polytech.si3.qgl.zecommit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SailorTest {

    Sailor sailor1;

    @BeforeEach
    void setUp() {
        sailor1 = new Sailor(1, 0, 0, "sailor1");
    }

    
    @Test
    void mouvSailorCorrecteTest() {
        sailor1.mouvSailor(2,1);
        assertEquals(2, sailor1.getX());
        assertEquals(1, sailor1.getY());
    }

    @Test
    void mouvSailorNoCorrecteTest() {
        sailor1.mouvSailor(3,3);
        assertEquals(0, sailor1.getX());
        assertEquals(0, sailor1.getY());
    }



}