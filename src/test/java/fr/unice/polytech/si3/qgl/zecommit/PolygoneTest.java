package fr.unice.polytech.si3.qgl.zecommit;

import fr.unice.polytech.si3.qgl.zecommit.shape.Point;
import fr.unice.polytech.si3.qgl.zecommit.shape.Polygone;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PolygoneTest {

    @Test
    public void getRadiusTest() {

        Point[] points = new Point[6];
        points[0] = new Point(8, 5);
        points[1] = new Point(9, 6);
        points[2] = new Point(10, 6);
        points[3] = new Point(11, 5);
        points[4] = new Point(10, 4);
        points[5] = new Point(9, 4);

        Polygone polygone = new Polygone(0, points);

        assertEquals(1.12, (double)Math.round(polygone.getRadius()*100)/100.0);
    }
}
