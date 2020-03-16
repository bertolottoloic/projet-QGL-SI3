package fr.unice.polytech.si3.qgl.zecommit.crew;


import fr.unice.polytech.si3.qgl.zecommit.Game;
import fr.unice.polytech.si3.qgl.zecommit.boat.Ship;
import fr.unice.polytech.si3.qgl.zecommit.entite.Entity;
import fr.unice.polytech.si3.qgl.zecommit.entite.Oar;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;


public class CaptainMateTest {

    Sailor sailor1;
    CaptainMate captainMate;
    Ship ship;
    List<Entity> entities;
    List<Sailor> sailors;
    Captain captain;

    @BeforeEach
    public void setUp() {
        System.out.println("test");
        Game game = mock(Game.class);
        sailor1 = new Sailor(1, 0, 0, "sailor1");
        captain = new Captain(game);
        captainMate = new CaptainMate(captain);
        Oar o1 = new Oar(2,0);
        Oar o2 = new Oar(2,3);

        Sailor s1 = new Sailor(0, 2, 0, "Pouce");
        Sailor s2 = new Sailor(1, 1, 0, "Teach");

        entities = Arrays.asList(new Entity[]{o1,o2});
        sailors = Arrays.asList(new Sailor[]{s1,s2});
    }


     @Test
     @Disabled
     public void toOarTest() {
         Oar oar1 = new Oar(sailor1.getX(), sailor1.getY());
         sailor1.setOnEntity(oar1);
         oar1.putSailorOn(sailor1);
         ArrayList<Sailor> sailors = new ArrayList<>();
         sailors.add(sailor1);
         assertFalse(captainMate.actions().isEmpty());
     }
//
//     @Test
//     void toOarNotCorrectTest() {
//         Oar oar2 = new Oar(sailor1.getX() + 1, sailor1.getY());
//         captainMate.toOar(sailor1, oar2);
//         assertTrue(captainMate.getActionList().isEmpty());
//     }
//
//     @Test
//     void initAttributeOarToSailorsTest(){
//         captainMate.initAttibuteOarToSailors(sailors, ship);
//         assertTrue(oars.get(0).hasSailorOn());
//         assertEquals(oars.get(0),sailors.get(1).getEntity());
//         assertTrue(oars.get(1).hasSailorOn());
//         assertEquals(oars.get(1),sailors.get(0).getEntity());
//     }
//
//     @Test
//     void initMoveSailorFirstStepTest(){
//         captainMate.initAttibuteOarToSailors(sailors, ship);
//         captainMate.initMoveSailor(sailors);
//         assertTrue(sailors.get(1).isOnEntity());
//         assertTrue(sailors.get(0).isOnEntity());
//     }
//
//     @Test
//     void initMoveSailorSecondStepTest(){
//         Oar o4 = new Oar(9,3);
//         Sailor s3 = new Sailor(2,4,2,"barbe");
//         List<Entity> oars = Arrays.asList(new Entity[]{o4});
//         List<Sailor> sailors = Arrays.asList(new Sailor[]{s3});
//         ship = new Ship(100,new Position(0, 0, 0),"boat",new Deck(4, 10),oars,new Rectangle(4, 10, 0));
//         captainMate.initAttibuteOarToSailors(sailors, ship);
//         captainMate.initMoveSailor(sailors);
//         assertTrue(s3.hasEntity());
//         assertFalse(s3.isOnEntity());
//         assertTrue(o4.hasSailorOn());
//         captainMate.initMoveSailor(sailors);
//         assertTrue(s3.isOnEntity());
//         assertTrue(o4.hasSailorOn());
//         assertEquals(o4, s3.getEntity());
//     }
//


}
