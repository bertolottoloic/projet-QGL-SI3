package fr.unice.polytech.si3.qgl.zecommit.crew;

import fr.unice.polytech.si3.qgl.zecommit.Game;
import fr.unice.polytech.si3.qgl.zecommit.Logs;
import fr.unice.polytech.si3.qgl.zecommit.boat.Position;
import fr.unice.polytech.si3.qgl.zecommit.boat.Ship;
import fr.unice.polytech.si3.qgl.zecommit.crew.Captain;
import fr.unice.polytech.si3.qgl.zecommit.crew.CaptainMate;
import fr.unice.polytech.si3.qgl.zecommit.crew.Sailor;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.List;

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


}


