package fr.unice.polytech.si3.qgl.zecommit;


import fr.unice.polytech.si3.qgl.zecommit.boat.Position;
import fr.unice.polytech.si3.qgl.zecommit.maths.Collision;
import fr.unice.polytech.si3.qgl.zecommit.shape.Circle;
import fr.unice.polytech.si3.qgl.zecommit.shape.Point;
import fr.unice.polytech.si3.qgl.zecommit.shape.Rectangle;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class CollisionTest {


    @Test
    public void isInTriangleTest(){
        Point A = new Point(2,3);
        Point B = new Point(7,4);
        Point C = new Point(2,6);
        Point M = new Point(3,4);

        Collision collision = new Collision(new Circle(0), new Position(0,0, 0), new Position(0,0,0));

        assertTrue(collision.isInTriangle(A,B,C,M));
    }


    @Test
    public void isNotInTriangleTest(){
        Point A = new Point(2,3);
        Point B = new Point(7,4);
        Point C = new Point(2,6);
        Point M = new Point(3,2.5);

        Collision collision = new Collision(new Circle(0), new Position(0,0, 0), new Position(0,0,0));

        assertFalse(collision.isInTriangle(A,B,C,M));
    }


    @Test
    /**
     * Cas limite : sur l'arrete du triangle
     */
    public void isInTriangleTest2(){
        Point A = new Point(2,3);
        Point B = new Point(7,4);
        Point C = new Point(2,6);
        Point M = new Point(4,5);

        Collision collision = new Collision(new Circle(0), new Position(0,0, 0), new Position(0,0,0));

        assertTrue(collision.isInTriangle(A,B,C,M));
    }

    @Test
    /**
     * Cas limite : sur un sommet du triangle
     */
    public void isInTriangleTest3(){
        Point A = new Point(2,3);
        Point B = new Point(7,4);
        Point C = new Point(2,6);
        Point M = new Point(2,6);

        Collision collision = new Collision(new Circle(0), new Position(0,0, 0), new Position(0,0,0));

        assertTrue(collision.isInTriangle(A,B,C,M));
    }

    @Test
    /**
     * Cas avec des négatifs
     */
    public void isInTriangleTest4(){
        Point A = new Point(-1,-1);
        Point B = new Point(-2,-2);
        Point C = new Point(-1,-6);
        Point M = new Point(-1.4,-2);

        Collision collision = new Collision(new Circle(0), new Position(0,0, 0), new Position(0,0,0));

        assertTrue(collision.isInTriangle(A,B,C,M));
    }





    @Test
    public void determineRectanglePointsTest(){
        Point RES1 = new Point(1,6);
        Point RES2 = new Point(4,6);
        Point RES3 = new Point(4,2);
        Point RES4 = new Point(1,2);


        Rectangle rectangle = new Rectangle(3, 4, Math.PI/2);
        Position position = new Position(2.5, 4, 0);

        Collision collision = new Collision(new Circle(0), new Position(0,0, 0), new Position(0,0,0));


        assertEquals(RES1.getX(), Math.round(collision.determineRectanglePoints(rectangle, position).get(0).getX()));
        assertEquals(RES1.getY(), Math.round(collision.determineRectanglePoints(rectangle, position).get(0).getY()));

        assertEquals(RES2.getX(), Math.round(collision.determineRectanglePoints(rectangle, position).get(1).getX()));
        assertEquals(RES2.getY(), Math.round(collision.determineRectanglePoints(rectangle, position).get(1).getY()));

        assertEquals(RES3.getX(), Math.round(collision.determineRectanglePoints(rectangle, position).get(2).getX()));
        assertEquals(RES3.getY(), Math.round(collision.determineRectanglePoints(rectangle, position).get(2).getY()));

        assertEquals(RES4.getX(), Math.round(collision.determineRectanglePoints(rectangle, position).get(3).getX()));
        assertEquals(RES4.getY(), Math.round(collision.determineRectanglePoints(rectangle, position).get(3).getY()));

    }

    @Test
    /**
     * cas négatifs
     */
    public void determineRectanglePointsTest2(){
        Point RES1 = new Point(-11.25,-7.5);
        Point RES2 = new Point(-11.25,-5.5);
        Point RES3 = new Point(-3.501,-5.5);
        Point RES4 = new Point(-3.501,-7.51);


        Rectangle rectangle = new Rectangle(2, 7.75, Math.PI);
        Position position = new Position(-7.38, -6.5, 0);

        Collision collision = new Collision(new Circle(0), new Position(0,0, 0), new Position(0,0,0));


        assertEquals(Math.round(RES1.getX()), Math.round(collision.determineRectanglePoints(rectangle, position).get(0).getX()));
        assertEquals(Math.round(RES1.getY()), Math.round(collision.determineRectanglePoints(rectangle, position).get(0).getY()));

        assertEquals(Math.round(RES2.getX()), Math.round(collision.determineRectanglePoints(rectangle, position).get(1).getX()));
        assertEquals(Math.round(RES2.getY()), Math.round(collision.determineRectanglePoints(rectangle, position).get(1).getY()));

        assertEquals(Math.round(RES3.getX()), Math.round(collision.determineRectanglePoints(rectangle, position).get(2).getX()));
        assertEquals(Math.round(RES3.getY()), Math.round(collision.determineRectanglePoints(rectangle, position).get(2).getY()));

        assertEquals(Math.round(RES4.getX()), Math.round(collision.determineRectanglePoints(rectangle, position).get(3).getX()));
        assertEquals(Math.round(RES4.getY()), Math.round(collision.determineRectanglePoints(rectangle, position).get(3).getY()));

    }


    /**
     * Sans orientation du rectangle
     */

    @Test
    public void isInRectangleTest(){
        Rectangle rectangle = new Rectangle(2,4, 0);
        Position rectanglePosition = new Position(6,3,0);
        Position M = new Position(5,3, 0);

        Collision collision = new Collision(new Circle(0), rectanglePosition, M);

        assertTrue(collision.isInRectangle(rectangle));
    }


    @Test
    public void isNotInRectangleTest(){
        Rectangle rectangle = new Rectangle(2,4, 0);
        Position rectanglePosition = new Position(6,3,0);
        Position M = new Position(3,3, 0);

        Collision collision = new Collision(new Circle(0), rectanglePosition, M);

        assertFalse(collision.isInRectangle(rectangle));
    }


    @Test
    /**
     * Cas limite : sur l'arrete du rectangle
     */
    public void isInRectangleTest2(){
        Rectangle rectangle = new Rectangle(2,4, 0);
        Position rectanglePosition = new Position(6,3,0);
        Position M = new Position(4,3, 0);

        Collision collision = new Collision(new Circle(0), rectanglePosition, M);

        assertTrue(collision.isInRectangle(rectangle));
    }

    @Test
    /**
     * Cas limite : sur un sommet du rectangle
     */
    public void isInRectangleTest3(){
        Rectangle rectangle = new Rectangle(2,4, 0);
        Position rectanglePosition = new Position(6,3,0);
        Position M = new Position(4,4, 0);

        Collision collision = new Collision(new Circle(0), rectanglePosition, M);

        assertTrue(collision.isInRectangle(rectangle));
    }


    /**
     * Avec orientation du rectangle
     */

    @Test
    public void isInRectangleTest4(){
        Rectangle rectangle = new Rectangle(2.84,5.66, 0.79);
        Position rectanglePosition = new Position(4,5,0);
        Position M = new Position(2,4, 0);
        Position N = new Position(4,4, 0);
        Position O = new Position(5,6, 0);
        Position P = new Position(4,6, 0);

        Collision collision = new Collision(new Circle(0), rectanglePosition, M);
        assertTrue(collision.isInRectangle(rectangle));

        collision = new Collision(new Circle(0), rectanglePosition, N);
        assertTrue(collision.isInRectangle(rectangle));

        collision = new Collision(new Circle(0), rectanglePosition, O);
        assertTrue(collision.isInRectangle(rectangle));

        collision = new Collision(new Circle(0), rectanglePosition, P);
        assertTrue(collision.isInRectangle(rectangle));
    }


    @Test
    public void isNotInRectangleTest5(){
        Rectangle rectangle = new Rectangle(2.84,5.66, 0.79);
        Position rectanglePosition = new Position(4,5,0);
        Position M = new Position(2,6, 0);

        Collision collision = new Collision(new Circle(0), rectanglePosition, M);

        assertFalse(collision.isInRectangle(rectangle));
    }


    @Test
    /**
     * Cas limite : sur l'arrete du rectangle
     */
    public void isInRectangleTest6(){
        Rectangle rectangle = new Rectangle(2.84,5.66, 0.79);
        Position rectanglePosition = new Position(4,5,0);
        Position M = new Position(6,7, 0);

        Collision collision = new Collision(new Circle(0), rectanglePosition, M);

        assertTrue(collision.isInRectangle(rectangle));
    }

    @Test
    /**
     * Cas limite : sur un sommet du rectangle
     */
    public void isInRectangleTest7(){
        Rectangle rectangle = new Rectangle(2.84,5.66, 0.79);
        Position rectanglePosition = new Position(4,5,0);
        Position M = new Position(3.02,2.3, 0);

        Collision collision = new Collision(new Circle(0), rectanglePosition, M);

        assertTrue(collision.isInRectangle(rectangle));
    }

    @Test
    /**
     * Cas négatif
     */
    public void isInRectangleTest8(){
        Rectangle rectangle = new Rectangle(2.84,5.66, -0.79);
        Position rectanglePosition = new Position(-4,-5,0);
        Position M = new Position(-6,-4, 0);

        Collision collision = new Collision(new Circle(0), rectanglePosition, M);

        assertTrue(collision.isInRectangle(rectangle));
    }





    @Test
    public void collideTest(){
        Position circlePosition = new Position(-4,-5,0);
        Position M = new Position(-6,-4, 0);

        Collision collision = new Collision(new Circle(10), circlePosition, M);

        assertTrue(collision.collide());
    }

    @Test
    public void collideTest2(){
        Position circlePosition = new Position(-4,-5,0);
        Position M = new Position(5.9,-4, 0);

        Collision collision = new Collision(new Circle(10), circlePosition, M);

        assertTrue(collision.collide());
    }


    @Test
    public void collideTest3(){
        Position rectanglePosition = new Position(-4,-5,0);
        Position M = new Position(5.9,-4, 0);

        Collision collision = new Collision(new Rectangle(4, 22, 0), rectanglePosition, M);

        assertTrue(collision.collide());
    }






}
