package fr.unice.polytech.si3.qgl.zecommit.action;

import org.junit.jupiter.api.Test;

import fr.unice.polytech.si3.qgl.zecommit.crew.Sailor;

import static org.junit.jupiter.api.Assertions.*;

class MovingTest {

    Sailor s = new Sailor(1, 0, 0, "Mousson");
    /**
     * On construit un objet Moving avec une distance respectant la spec
     */
    @Test
    void mouvAvecDistanceCorrecteTest() {
        Moving moving = new Moving(s, 2,1);
        assertEquals(2, moving.getXDistance());
        assertEquals(1, moving.getYDistance());
    }

    /**
     * On construit un objet Moving avec une distance respectant la spec
     * Conditions limites
     */
    @Test
    void mouvAvecDistanceCorrecteCdtLmtTest() {
        Moving moving = new Moving(s, 3,2);
        assertEquals(3, moving.getXDistance());
        assertEquals(2, moving.getYDistance());
    }

    /**
     * On construit un objet Moving avec une distance ne respectant pas la spec
     * On veut donc que la distance soit nulle
     */
    @Test
    void mouvAvecDistanceNonCorrecteTest() {
        Moving moving = new Moving(s, 5,3);
        assertEquals(5, moving.getXDistance());
        assertEquals(0, moving.getYDistance());
    }

}
