package fr.unice.polytech.si3.qgl.zecommit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Or;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class OrientationTableTest {

    OrientationTable orientationTable;


    @BeforeEach
    void setUp() {
        orientationTable = new OrientationTable(4);




    }

    /**
     * test du tableau des orientation avec un nombre pair de rames
     */
    @Test
    void generateAngleTablePairTest() {

        assertEquals(orientationTable.getAngleTable().size(), 5);
        assertEquals(orientationTable.getAngleTable().get(0), -Math.PI/2);
        assertEquals(orientationTable.getAngleTable().get(4), Math.PI/2);
        assertEquals(orientationTable.getAngleTable().get(2), 0);

    }


    /**
     * test du tableau des orientation avec un nombre impair de rames
     */
    @Test
    void generateAngleTableImpairTest() {
        OrientationTable orientationTable = new OrientationTable(5);

        assertEquals(orientationTable.getAngleTable().size(), 5);
        assertEquals(orientationTable.getAngleTable().get(0), -Math.PI/2);
        assertEquals(orientationTable.getAngleTable().get(4), Math.PI/2);
        assertEquals(orientationTable.getAngleTable().get(2), 0);

    }

    @Test
    void generateFormationTest() {
        assertEquals(orientationTable.getFormationTable().size(), 5);
    }


    @Test
    void generateFormationNegativeAngle() {

        //-PI/2
        assertEquals(orientationTable.getCompo(0, 0).getSailorsLeft(), 2); // 2 marins à gauche
        assertEquals(orientationTable.getCompo(0, 0).getSailorsRight(), 0); // 0 à droite

        //-PI/4 : compo 1
        assertEquals(orientationTable.getCompo(1, 0).getSailorsLeft(), 1); // 1 marin à gauche
        assertEquals(orientationTable.getCompo(1, 0).getSailorsRight(), 0); // 0 à droite

        //-PI/4 : compo 2
        assertEquals(orientationTable.getCompo(1, 1).getSailorsLeft(), 2); // 0 marin à gauche
        assertEquals(orientationTable.getCompo(1, 1).getSailorsRight(), 1); // 1 à droite

    }

    @Test
    void generateFormation0Angle(){
        //0 : compo 1
        assertEquals(orientationTable.getCompo(2, 0).getSailorsLeft(), 0); // 0 marin à gauche
        assertEquals(orientationTable.getCompo(2, 0).getSailorsRight(), 0); // 0 à droite

        //0 : compo 2
        assertEquals(orientationTable.getCompo(2, 1).getSailorsLeft(), 1); // 1 marin à gauche
        assertEquals(orientationTable.getCompo(2, 1).getSailorsRight(), 1); // 1 à droite

        //0 : compo 3
        assertEquals(orientationTable.getCompo(2, 1).getSailorsLeft(), 1); // 2 marins à gauche
        assertEquals(orientationTable.getCompo(2, 1).getSailorsRight(), 1); // 2 à droite

    }

    @Test
    void generateFormationPositiveAngle(){

        //PI/4 : compo 1
        assertEquals(orientationTable.getCompo(3, 0).getSailorsLeft(), 0); // 0 marin à gauche
        assertEquals(orientationTable.getCompo(3, 0).getSailorsRight(), 1); // 1 à droite

        //PI/4 : compo 2
        assertEquals(orientationTable.getCompo(3, 1).getSailorsLeft(), 1); // 1 marin à gauche
        assertEquals(orientationTable.getCompo(3, 1).getSailorsRight(), 2); // 2 à droite

        //PI/2
        assertEquals(orientationTable.getCompo(4, 0).getSailorsLeft(), 0); // 0 marin à gauche
        assertEquals(orientationTable.getCompo(4, 0).getSailorsRight(), 2); // 2 à droite

    }
}
