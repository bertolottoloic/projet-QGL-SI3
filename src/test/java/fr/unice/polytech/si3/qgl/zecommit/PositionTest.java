package fr.unice.polytech.si3.qgl.zecommit;

import fr.unice.polytech.si3.qgl.zecommit.boat.Position;
import org.junit.jupiter.api.Test;

class PositionTest{
    Position p1;
    Position p2;

    @Test
    void orientationGapTest(){
        p1 = new Position(0, 0, Math.PI/2);
        p2 = new Position(4, 3, 5*Math.PI/4);
        System.out.println(p1.orientationGap(p2));
    }
}