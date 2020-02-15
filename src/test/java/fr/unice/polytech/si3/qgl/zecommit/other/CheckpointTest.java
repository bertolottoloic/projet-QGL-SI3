package fr.unice.polytech.si3.qgl.zecommit.other;

import fr.unice.polytech.si3.qgl.zecommit.boat.Position;
import fr.unice.polytech.si3.qgl.zecommit.shape.Circle;
import fr.unice.polytech.si3.qgl.zecommit.shape.Rectangle;
import fr.unice.polytech.si3.qgl.zecommit.shape.Shape;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;


class CheckpointTest {
    Checkpoint cpCircle;
    Checkpoint cpRect;
    Position posMock;
    Shape circle;
    Shape rect;


    @BeforeEach
    void setUp() {
        circle = new Circle(1);
        rect = new Rectangle(1,1,0);
        posMock = mock(Position.class);
        cpCircle = new Checkpoint(posMock, circle);
        cpRect = new Checkpoint(posMock, rect);
    }

    /**
     * Renvoie true car le cp est un cercle
     */
    @Test
    void isCircleTrueTest() {
        assertTrue(cpCircle.isCircle());
    }


    /**
     * Renvoie false car le cp est un rectangle
     */
    @Test
    void isCircleFalseTest() {
        assertFalse(cpRect.isCircle());
    }

    @Test
    void getCircleRadiusTest() {
        assertEquals(1, cpCircle.getCircleRadius());
    }



}
