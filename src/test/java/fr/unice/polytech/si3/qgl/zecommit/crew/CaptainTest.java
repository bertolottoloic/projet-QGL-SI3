package fr.unice.polytech.si3.qgl.zecommit.crew;

import fr.unice.polytech.si3.qgl.zecommit.Game;
import fr.unice.polytech.si3.qgl.zecommit.Logs;
import fr.unice.polytech.si3.qgl.zecommit.boat.Position;
import fr.unice.polytech.si3.qgl.zecommit.boat.Ship;
import fr.unice.polytech.si3.qgl.zecommit.crew.Captain;
import fr.unice.polytech.si3.qgl.zecommit.crew.CaptainMate;
import fr.unice.polytech.si3.qgl.zecommit.crew.Sailor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CaptainTest {
    Captain jackSparrow;
    Game game;
    Ship blackPearl;
    Logs logs;
    CaptainMate gibbs;
    List<Sailor> sailorList;



    @BeforeEach
    void setUp() {
        sailorList = new ArrayList<>();
        for (int i = 0; i<10; i++)
            sailorList.add(new Sailor(i,0,0,""+i));

        game = mock(Game.class);
        when(game.getSailors()).thenReturn(sailorList);
        blackPearl = mock(Ship.class);
        when(game.getShip()).thenReturn(blackPearl);
        when(blackPearl.getPosition()).thenReturn(new Position(0,0,0));
        gibbs = mock(CaptainMate.class);
        logs = mock(Logs.class);

    }

    @Test
    public void findClosestPossibleAngleTest(){
        when(game.getShip().getOarsNb()).thenReturn(4);
        jackSparrow = new Captain(game,gibbs);

        assertEquals(1, jackSparrow.findClosestPossibleAngle(Math.PI/8-Math.PI/2));
        assertEquals(1, jackSparrow.findClosestPossibleAngle(Math.PI/4-Math.PI/2));
        assertEquals(1, jackSparrow.findClosestPossibleAngle(Math.PI/8+Math.PI/36-Math.PI/2));
        assertEquals(2, jackSparrow.findClosestPossibleAngle(Math.PI/2+Math.PI/36-Math.PI/2));
        assertEquals(4, jackSparrow.findClosestPossibleAngle(7*Math.PI/8-Math.PI/2));
        assertEquals(4, jackSparrow.findClosestPossibleAngle(Math.PI-Math.PI/36-Math.PI/2));

    }

    @Test
    public void findClosestPossibleAngleTest3(){
        when(game.getShip().getOarsNb()).thenReturn(6);
        jackSparrow = new Captain(game,gibbs);

        when(blackPearl.getPosition()).thenReturn(new Position(-20,-20,1.047));

        assertEquals(4, jackSparrow.findClosestPossibleAngle(0.77));

    }

    @Test
    public void findClosestPossibleAngleTest2(){
        when(game.getShip().getOarsNb()).thenReturn(3);
        jackSparrow = new Captain(game,gibbs);

        assertEquals(0, jackSparrow.findClosestPossibleAngle(0-Math.PI/2));
        assertEquals(1, jackSparrow.findClosestPossibleAngle(Math.PI/3-Math.PI/2));
        assertEquals(1, jackSparrow.findClosestPossibleAngle(Math.PI/3+Math.PI/36-Math.PI/2));
        assertEquals(2, jackSparrow.findClosestPossibleAngle(Math.PI/2+Math.PI/36-Math.PI/2));
        assertEquals(2, jackSparrow.findClosestPossibleAngle(2*Math.PI/3-Math.PI/2));
        assertEquals(3, jackSparrow.findClosestPossibleAngle(5*Math.PI/6-Math.PI/2));
        assertEquals(3, jackSparrow.findClosestPossibleAngle(Math.PI-Math.PI/2));

    }

}
