package fr.unice.polytech.si3.qgl.zecommit;

import fr.unice.polytech.si3.qgl.zecommit.boat.Deck;
import fr.unice.polytech.si3.qgl.zecommit.boat.Position;
import fr.unice.polytech.si3.qgl.zecommit.boat.Ship;
import fr.unice.polytech.si3.qgl.zecommit.crew.Captain;
import fr.unice.polytech.si3.qgl.zecommit.crew.CaptainMate;
import fr.unice.polytech.si3.qgl.zecommit.crew.Sailor;
import fr.unice.polytech.si3.qgl.zecommit.entite.Entity;
import fr.unice.polytech.si3.qgl.zecommit.entite.Oar;
import fr.unice.polytech.si3.qgl.zecommit.shape.Rectangle;
import fr.unice.polytech.si3.qgl.zecommit.shape.Shape;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CaptainTest {
    Captain jackSparrow;
    Game game;
    Ship blackPearl;
    Logs logs;
    CaptainMate gibbs;


    @BeforeEach
    void setUp() {
        game = mock(Game.class);
        blackPearl = mock(Ship.class);
        when(game.getShip()).thenReturn(blackPearl);
        when(blackPearl.getPosition()).thenReturn(new Position(0,0,0));
        gibbs = mock(CaptainMate.class);
        logs = mock(Logs.class);

    }

    @Test
    public void findClosestPossibleAngleTest(){
        when(game.getShip().getOarsNb()).thenReturn(4);
        jackSparrow = new Captain(game,gibbs,logs);

        assertEquals(jackSparrow.findClosestPossibleAngle(Math.PI/8-Math.PI/2), 1);
        assertEquals(jackSparrow.findClosestPossibleAngle(Math.PI/4-Math.PI/2), 1);
        assertEquals(jackSparrow.findClosestPossibleAngle(Math.PI/8+Math.PI/36-Math.PI/2), 1);
        assertEquals(jackSparrow.findClosestPossibleAngle(Math.PI/2+Math.PI/36-Math.PI/2), 2);
        assertEquals(jackSparrow.findClosestPossibleAngle(7*Math.PI/8-Math.PI/2), 4);
        assertEquals(jackSparrow.findClosestPossibleAngle(Math.PI-Math.PI/36-Math.PI/2), 4);

    }

    @Test
    public void findClosestPossibleAngleTest2(){
        when(game.getShip().getOarsNb()).thenReturn(3);
        jackSparrow = new Captain(game,gibbs,logs);

        assertEquals(jackSparrow.findClosestPossibleAngle(0-Math.PI/2), 0);
        assertEquals(jackSparrow.findClosestPossibleAngle(Math.PI/3-Math.PI/2), 1);
        assertEquals(jackSparrow.findClosestPossibleAngle(Math.PI/3+Math.PI/36-Math.PI/2), 1);
        assertEquals(jackSparrow.findClosestPossibleAngle(Math.PI/2+Math.PI/36-Math.PI/2), 2);
        assertEquals(jackSparrow.findClosestPossibleAngle(2*Math.PI/3-Math.PI/2), 2);
        assertEquals(jackSparrow.findClosestPossibleAngle(5*Math.PI/6-Math.PI/2), 3);
        assertEquals(jackSparrow.findClosestPossibleAngle(Math.PI-Math.PI/2), 3);

    }

}
