package fr.unice.polytech.si3.qgl.zecommit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrientationTableTest {

    OrientationTable orientationTable;


    @BeforeEach
    void setUp() {
        orientationTable = new OrientationTable(4, 4);




    }

    /**
     * test du tableau des orientation avec un nombre pair de rames
     */
    @Test
    void generateAngleTablePairTest() {

        assertEquals(5, orientationTable.getAngleTable().size());
        assertEquals(-Math.PI/2, orientationTable.getAngleTable().get(0));
        assertEquals(Math.PI/2, orientationTable.getAngleTable().get(4));
        assertEquals(0, orientationTable.getAngleTable().get(2));

    }


    /**
     * test du tableau des orientation avec un nombre impair de rames
     */
    @Test
    void generateAngleTableImpairTest() {
        OrientationTable orientationTable = new OrientationTable(5, 7);

        assertEquals(5, orientationTable.getAngleTable().size());
        assertEquals(-Math.PI/2, orientationTable.getAngleTable().get(0));
        assertEquals(Math.PI/2, orientationTable.getAngleTable().get(4));
        assertEquals(0, orientationTable.getAngleTable().get(2));

    }

    @Test
    void generateFormationTest() {
        assertEquals(5, orientationTable.getFormationTable().size());
    }


    @Test
    void generateFormationNegativeAngle() {

        //-PI/2
        assertEquals(2, orientationTable.getCompo(0, 0).getSailorsLeft()); // 2 marins à gauche
        assertEquals(0, orientationTable.getCompo(0, 0).getSailorsRight()); // 0 à droite

        //-PI/4 : compo 1
        assertEquals(1, orientationTable.getCompo(1, 0).getSailorsLeft()); // 1 marin à gauche
        assertEquals(0, orientationTable.getCompo(1, 0).getSailorsRight()); // 0 à droite

        //-PI/4 : compo 2
        assertEquals(2, orientationTable.getCompo(1, 1).getSailorsLeft()); // 0 marin à gauche
        assertEquals(1, orientationTable.getCompo(1, 1).getSailorsRight()); // 1 à droite

    }

    @Test
    void generateFormation0Angle(){
        //0 : compo 1
        assertEquals(1, orientationTable.getCompo(2, 0).getSailorsLeft()); // 1 marin à gauche
        assertEquals(1, orientationTable.getCompo(2, 0).getSailorsRight()); // 1 à droite

        //0 : compo 2
        assertEquals(2, orientationTable.getCompo(2, 1).getSailorsLeft()); // 2 marins à gauche
        assertEquals(2, orientationTable.getCompo(2, 1).getSailorsRight()); // 2 à droite

        assertEquals(2, orientationTable.getLastCompo(2).getSailorsRight());

    }

    @Test
    void generateFormationPositiveAngle(){

        //PI/4 : compo 1
        assertEquals(0, orientationTable.getCompo(3, 0).getSailorsLeft()); // 0 marin à gauche
        assertEquals(1, orientationTable.getCompo(3, 0).getSailorsRight()); // 1 à droite

        //PI/4 : compo 2
        assertEquals(1, orientationTable.getCompo(3, 1).getSailorsLeft()); // 1 marin à gauche
        assertEquals(2, orientationTable.getCompo(3, 1).getSailorsRight()); // 2 à droite

        //PI/2
        assertEquals(0, orientationTable.getCompo(4, 0).getSailorsLeft()); // 0 marin à gauche
        assertEquals(2, orientationTable.getCompo(4, 0).getSailorsRight()); // 2 à droite

    }
}
