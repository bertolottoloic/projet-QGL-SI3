package fr.unice.polytech.si3.qgl.zecommit;

import fr.unice.polytech.si3.qgl.zecommit.boat.Position;
import fr.unice.polytech.si3.qgl.zecommit.boat.Ship;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
        assertEquals(6,roadTest.xDistanceToGoal());
        assertEquals(6,roadTest2.xDistanceToGoal());

    }

    @Test
    void distanceYToGoalTest(){
        assertEquals(3,roadTest.yDistanceToGoal());
        assertEquals(3,roadTest2.yDistanceToGoal());
    }

    @Test
    void distanceToGoalTest(){
        assertEquals(Math.sqrt(3*3+6*6),roadTest.distanceToGoal());
        assertEquals(Math.sqrt(3*3+6*6),roadTest2.distanceToGoal());
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
        assertEquals(-3*Math.PI/4,road.orientationToGoal());

        road= new Road(start, new Position(-100,0,0));
        assertEquals(-Math.PI,road.orientationToGoal());

    }
    @Test
    void orientationToGoalPositivePositionTest() {
        //Position positive, position checkpoint positive
        Road road = new Road(new Position(2,3,Math.PI/4), new Position(2, 8, 0));
        assertEquals(Math.PI/4,road.orientationToGoal());

        //Position positive, position checkpoint négative
        road = new Road(new Position(3,2,Math.PI/4), new Position(-2, 7, 0));
        assertEquals(Math.PI/2, road.orientationToGoal());

    }

    @Test
    void orientationToGoalNegativePositionTest() {
        //Position négative, position checkpoint positive
        Road road = new Road(new Position(-2, -3, Math.PI / 4), new Position(2, 7, 0));
        assertTrue(road.orientationToGoal()>0.4 && road.orientationToGoal()<0.41);

        //Position positive, position checkpoint négative
        road = new Road(new Position(-2, -3, Math.PI / 4), new Position(-2, -5, 0));
        assertEquals(-3*Math.PI /4,road.orientationToGoal());

        road = new Road(new Position(-2, -3, Math.PI / 4), new Position(2, 7, 0));
        assertTrue(road.orientationToGoal()>0.4 && road.orientationToGoal()<0.41 );

        //Position positive, position checkpoint négative
        road = new Road(new Position(-2, -3, Math.PI / 4), new Position(-2, -5, 0));
        assertEquals(-3*Math.PI /4,road.orientationToGoal());

        road = new Road(new Position(0, 0, Math.PI / 4), new Position(-5, -5, 0));
        assertEquals(-Math.PI,road.orientationToGoal());

    }

    /**
     * Cas limites
     */
    @Test
    void orientationToGoalTest2() {
        // Bateau et Checkpoint sur le meme axe vertical
        Road road = new Road(new Position(3, 2, Math.PI / 3), new Position(3, 5, 0));
        assertTrue(0.52<road.orientationToGoal() && 0.524>road.orientationToGoal() );

        // Bateau et Checkpoint sur le meme axe horizontal
        road = new Road(new Position(1, -3, Math.PI / 4), new Position(7, -3, 0));
        assertEquals( -Math.PI /4, road.orientationToGoal());

        // Bateau et Checkpoint confondu
        road = new Road(new Position(-7, 3, Math.PI / 4), new Position(-7, 3, 0));
        assertEquals( 0, road.orientationToGoal());


        road = new Road(new Position(-7, 3, 3*Math.PI / 4), new Position(-5, 1, 0));
        assertEquals( -Math.PI, road.orientationToGoal());
        
    }

    /**
     * Renvoie PI/4 à la place de 7*PI/4
     */
    @Test
    void shortestAngle1Test() {
        Position start = new Position(0,0,Math.PI/4);
        Position end = new Position(0,4,0);

        Road road = new Road(start, end);

        assertEquals(road.orientationToGoal(), Math.PI/4);
    }


    /**
     * Renvoie -(3*PI)/4 à la place de (5*PI)/4
     */
    @Test
    void shortestAngle2Test() {
        Position start = new Position(0,0,Math.PI/4);
        Position end = new Position(0,-4,0);

        Road road = new Road(start, end);

        assertEquals(road.orientationToGoal(), -3*Math.PI/4);
    }

    /**
     *
     */
    @Test
    void shortestAngle3Test() {
        Position start = new Position(0,0,Math.PI/4);
        Position end = new Position(-4,-4,0);

        Road road = new Road(start, end);

        assertEquals(road.orientationToGoal(), -Math.PI);
    }
}
