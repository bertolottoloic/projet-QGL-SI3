package fr.unice.polytech.si3.qgl.zecommit;

import fr.unice.polytech.si3.qgl.zecommit.boat.Position;
import fr.unice.polytech.si3.qgl.zecommit.boat.Ship;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class RoadTest {

    Road roadTest;
    Road roadTest2;

    Ship shipMock;


    @BeforeEach
    void setUp(){
        Position startpos1 = new Position(1,3,0);
        Position endPos1 = new Position(7,6,Math.PI/2);
        roadTest= new Road(startpos1,endPos1);
        roadTest2 = new Road(endPos1,startpos1);

        shipMock = mock(Ship.class);

    }

    @Test
    void distanceXToGoalTest(){
        assertEquals(6,roadTest.DistanceXToGoal());
        assertEquals(6,roadTest2.DistanceXToGoal());

    }

    @Test
    void distanceYToGoalTest(){
        assertEquals(3,roadTest.DistanceYToGoal());
        assertEquals(3,roadTest2.DistanceYToGoal());
    }

    @Test
    void distanceToGoalTest(){
        assertEquals(Math.sqrt(3*3+6*6),roadTest.DistanceToGoal());
        assertEquals(Math.sqrt(3*3+6*6),roadTest2.DistanceToGoal());
    }

    @Test
    void orientationToGoalTest(){
        Position start= new Position(0,0,0);
        Position end = new Position(0,1,0);
        Road road=new Road(start,end);
        assertEquals(Math.PI/2,road.orientationToGoal());

        road= new Road(start, new Position(1,1,0));
        assertEquals(Math.PI/4,road.orientationToGoal());

        road= new Road(start, new Position(-1,-1,0));
        assertEquals(Math.PI/4,road.orientationToGoal());
    }

    /**
     * Orientation selon axe des abscisses, Postion de fin dans l'intervalle du cap
     */
    @Test
    void inCapIntervalle_IN_Test() {
        Position start = new Position(0,0,0);
        Position end = new Position(10,0,0);
        Road road = new Road(start, end);

        assertTrue(road.inCapIntervalle(0.2));
    }

    /**
     * Orientation selon axe des abscisses, Postion de fin hors l'intervalle du cap
     */
    @Test
    void inCapIntervalle_OUT_Test() {

        Position start = new Position(0,0,0);
        Position end = new Position(0,10,0);
        Road road = new Road(start, end);

        assertFalse(road.inCapIntervalle(0.2));
    }

    /**
     * Renvoie PI/4 à la place de 7*PI/4
     */
    @Test
    void shortestangle1Test() {
        Position start = new Position(0,0,Math.PI/4);
        Position end = new Position(0,4,0);

        Road road = new Road(start, end);

        assertEquals(road.orientationToGoal(), Math.PI/4);
    }


    /**
     * Renvoie -(3*PI)/4 à la place de (5*PI)/4
     */
    @Test
    void shortestangle2Test() {
        Position start = new Position(0,0,Math.PI/4);
        Position end = new Position(0,-4,0);

        Road road = new Road(start, end);

        assertEquals(road.orientationToGoal(), -3*Math.PI/4);
    }

    /**
     *
     */
    @Test
    void shortestangle3Test() {
        Position start = new Position(0,0,Math.PI/4);
        Position end = new Position(-4,-4,0);

        Road road = new Road(start, end);

        assertEquals(road.orientationToGoal(), Math.PI);
    }
}
