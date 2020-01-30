package fr.unice.polytech.si3.qgl.zecommit;

import fr.unice.polytech.si3.qgl.zecommit.boat.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RoadTest {

    Road roadTest;
    Road roadTest2;

    @BeforeEach
    void setUp(){
        Position startpos1 = new Position(1,3,0);
        Position endPos1 = new Position(7,6,Math.PI/2);
         roadTest= new Road(startpos1,endPos1);
         roadTest2 = new Road(endPos1,startpos1);
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
}
