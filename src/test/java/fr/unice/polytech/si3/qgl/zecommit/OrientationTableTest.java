package fr.unice.polytech.si3.qgl.zecommit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Or;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class OrientationTableTest {


    @BeforeEach
    void setUp() {

    }

    /**
     * test du tableau des orientation avec un nombre pair de rames
     */
    @Test
    void generateAngleTablePairTest() {
        OrientationTable orientationTable = new OrientationTable(4);

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
}