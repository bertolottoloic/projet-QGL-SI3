
import fr.unice.polytech.si3.qgl.zecommit.boat.Position;
import fr.unice.polytech.si3.qgl.zecommit.boat.Ship;
import fr.unice.polytech.si3.qgl.zecommit.maths.Calculs;
import fr.unice.polytech.si3.qgl.zecommit.maths.Road;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class CalculsTest {

    private static final double EPSILON = 1e-8;


    /**
     * cas négatif
     */
    @Test
    public void shortestAngleTest() {
        assertEquals(Math.round(1000 * 2 * Math.PI / 3), Math.round(1000 * Calculs.shortestAngle(-4 * Math.PI / 3)));
    }

    /**
     * cas négatif
     */
    @Test
    public void shortestAngleTest2() {
        assertEquals(Math.round(1000 * Math.PI / 4), Math.round(1000 * Calculs.shortestAngle(-7 * Math.PI / 4)));
    }

    /**
     * cas positif
     */
    @Test
    public void shortestAngleTest3() {
        assertEquals(Math.round(1000 * 2 * -Math.PI / 3), Math.round(1000 * Calculs.shortestAngle(4 * Math.PI / 3)));
    }

    /**
     * cas positif
     */
    @Test
    public void shortestAngleTest4() {
        assertEquals(Math.round(1000 * -Math.PI / 4), Math.round(1000 * Calculs.shortestAngle(7 * Math.PI / 4)));
    }

    /**
     * cas limite
     */
    @Test
    public void shortestAngleTest5() {
        assertEquals(Math.PI, Calculs.shortestAngle(Math.PI));
    }


    @Test
    public void subdiviseRouteSizeTest(){
        assertEquals(201, Calculs.subdiviseRoute(new Position(0,0,0), new Position(200,0,0)).size());
        assertEquals(201, Calculs.subdiviseRoute(new Position(0,0,0), new Position(0,200,0)).size());
        assertEquals(201, Calculs.subdiviseRoute(new Position(0,0,0), new Position(200,200,0)).size());

        assertEquals(201, Calculs.subdiviseRoute(new Position(0,0,0), new Position(-200,0,0)).size());
        assertEquals(201, Calculs.subdiviseRoute(new Position(0,0,0), new Position(0,-200,0)).size());
        assertEquals(201, Calculs.subdiviseRoute(new Position(0,0,0), new Position(-200,-200,0)).size());

        assertEquals(201, Calculs.subdiviseRoute(new Position(200,0,0), new Position(0,200,0)).size());
        assertEquals(201, Calculs.subdiviseRoute(new Position(0,2000,0), new Position(200,0,0)).size());
    }
    @Test
    public void subdiviseRouteTest(){
        List<Position> res = new ArrayList<>();
        for(int k = 0; k<201; k++){
            res.add(new Position(k, 0, 0));
        }
        assertEquals(res.get(5), Calculs.subdiviseRoute(new Position(0,0,0), new Position(200,0,0)).get(5));
    }


    @Test
    public void findIntersectionsCercleTest() {
        List<Position> expected = new ArrayList<>();
        expected.add(new Position(6, -1, 0));
        expected.add(new Position(3, 6, 0));

        List<Position> actual = Calculs.findIntersectionsCercle(new Position(1,1,0), new Position(8,4,0));

        assertEquals(expected.get(0).getX(),actual.get(0).getX(), EPSILON );
        assertEquals(expected.get(0).getY(),actual.get(0).getY(), EPSILON );

        assertEquals(expected.get(1).getX(),actual.get(1).getX(), EPSILON );
        assertEquals(expected.get(1).getY(),actual.get(1).getY(), EPSILON );

    }


    /**
     * cas particulier : mêmes ordonnées
     */
    @Test
    public void findIntersectionsCercleTest2() {
        List<Position> expected = new ArrayList<>();
        expected.add(new Position(4.5, -2.5, 0));
        expected.add(new Position(4.5, 4.5, 0));

        List<Position> actual = Calculs.findIntersectionsCercle(new Position(1,1,0), new Position(8,1,0));

        assertEquals(expected.get(0).getX(),actual.get(0).getX(), EPSILON );
        assertEquals(expected.get(0).getY(),actual.get(0).getY(), EPSILON );

        assertEquals(expected.get(1).getX(),actual.get(1).getX(), EPSILON );
        assertEquals(expected.get(1).getY(),actual.get(1).getY(), EPSILON );

    }

    /**
     * cas particulier : memes ordonnées
     */
    @Test
    public void findIntersectionsCercleTest3() {
        List<Position> expected = new ArrayList<>();
        expected.add(new Position(7, -6, 0));
        expected.add(new Position(7, 0, 0));

        List<Position> actual = Calculs.findIntersectionsCercle(new Position(4,-3,0), new Position(10,-3,0));

        assertEquals(expected.get(0).getX(),actual.get(0).getX(), EPSILON );
        assertEquals(expected.get(0).getY(),actual.get(0).getY(), EPSILON );

        assertEquals(expected.get(1).getX(),actual.get(1).getX(), EPSILON );
        assertEquals(expected.get(1).getY(),actual.get(1).getY(), EPSILON );

    }

    /**
     * cas particulier : memes abscisses
     */
    @Test
    public void findIntersectionsCercleTest4() {
        List<Position> expected = new ArrayList<>();
        expected.add(new Position(6, -1, 0));
        expected.add(new Position(2, -1, 0));

        List<Position> actual = Calculs.findIntersectionsCercle(new Position(4,-3,0), new Position(4,1,0));

        assertEquals(expected.get(0).getX(),actual.get(0).getX(), EPSILON );
        assertEquals(expected.get(0).getY(),actual.get(0).getY(), EPSILON );

        assertEquals(expected.get(1).getX(),actual.get(1).getX(), EPSILON );
        assertEquals(expected.get(1).getY(),actual.get(1).getY(), EPSILON );

    }

}
