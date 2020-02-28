package fr.unice.polytech.si3.qgl.zecommit.engine;

import fr.unice.polytech.si3.qgl.zecommit.action.Turn;
import fr.unice.polytech.si3.qgl.zecommit.boat.Deck;
import fr.unice.polytech.si3.qgl.zecommit.boat.Position;
import fr.unice.polytech.si3.qgl.zecommit.boat.Ship;
import fr.unice.polytech.si3.qgl.zecommit.crew.Sailor;
import fr.unice.polytech.si3.qgl.zecommit.entite.Oar;
import fr.unice.polytech.si3.qgl.zecommit.entite.Rudder;
import fr.unice.polytech.si3.qgl.zecommit.entite.Sail;
import fr.unice.polytech.si3.qgl.zecommit.other.Checkpoint;
import fr.unice.polytech.si3.qgl.zecommit.other.Wind;
import fr.unice.polytech.si3.qgl.zecommit.shape.Circle;
import fr.unice.polytech.si3.qgl.zecommit.shape.Rectangle;
import fr.unice.polytech.si3.qgl.zecommit.shape.Shape;
import org.junit.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class EngineSettingsTest{
    String json1;
    EngineSettings engineSettings;

    @BeforeEach
    void setUp() {
        engineSettings= new EngineSettings();
        json1 = "{\n" +
                "  \"goal\": {\n" +
                "    \"mode\": \"REGATTA\",\n" +
                "    \"checkpoints\": [\n" +
                "      {\n" +
                "        \"position\": {\n" +
                "          \"x\": 1000,\n" +
                "          \"y\": 0,\n" +
                "          \"orientation\": 0\n" +
                "        },\n" +
                "        \"shape\": {\n" +
                "          \"type\": \"circle\",\n" +
                "          \"radius\": 50\n" +
                "        }\n" +
                "      }\n" +
                "    ]\n" +
                "  },\n" +
                "  \"shipCount\": 1,\n" +
                "  \"ship\": {\n" +
                "    \"type\": \"ship\",\n" +
                "    \"life\": 100,\n" +
                "    \"position\": {\n" +
                "      \"x\": 0,\n" +
                "      \"y\": 0,\n" +
                "      \"orientation\": 0\n" +
                "    },\n" +
                "    \"name\": \"Les copaings d'abord!\",\n" +
                "    \"deck\": {\n" +
                "      \"width\": 2,\n" +
                "      \"length\": 1\n" +
                "    },\n" +
                "    \"entities\": [\n" +
                "      {\n" +
                "        \"x\": 0,\n" +
                "        \"y\": 0,\n" +
                "        \"type\": \"oar\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"x\": 0,\n" +
                "        \"y\": 1,\n" +
                "        \"type\": \"oar\"\n" +
                "      }\n" +
                "    ],\n" +
                "    \"shape\": {\n" +
                "      \"type\": \"rectangle\",\n" +
                "      \"width\": 2,\n" +
                "      \"height\": 3,\n" +
                "      \"orientation\": 0\n" +
                "    }\n" +
                "  },\n" +
                "  \"sailors\": [\n" +
                "    {\n" +
                "      \"x\": 0,\n" +
                "      \"y\": 0,\n" +
                "      \"id\": 0,\n" +
                "      \"name\": \"Edward Teach\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"x\": 1,\n" +
                "      \"y\": 0,\n" +
                "      \"id\": 1,\n" +
                "      \"name\": \"Tom Pouce\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";

    }


    @Test
    void engineTurnTest(){
        engineSettings.addSailors(new Sailor(1,2,3,"name"));
        engineSettings.addEntities(new Rudder(2,3));
        engineSettings.sortEntities();
        Turn turn= mock(Turn.class);
        when(turn.getSailorId()).thenReturn(1);
        when(turn.getRotation()).thenReturn(1.3);
        engineSettings.engineTurn(turn);
        assertEquals(1.3,engineSettings.getRotation());
    }

    @Test
    void engineTurnTestFalse(){
        engineSettings.addSailors(new Sailor(1,2,3,"name"));
        engineSettings.addEntities(new Rudder(3,3));
        engineSettings.sortEntities();
        Turn turn= mock(Turn.class);
        when(turn.getSailorId()).thenReturn(1);
        when(turn.getRotation()).thenReturn(1.3);
        engineSettings.engineTurn(turn);
        assertFalse(engineSettings.getRotation()==1.3);
        assertTrue(engineSettings.getRotation()==0);
    }

    @Test
    void engineOarLeftRightTest(){
        Sailor sailorTest=new Sailor(3,7,3,"name");
        engineSettings.addDeck(new Deck(5,10));
        engineSettings.addSailors(sailorTest);
        engineSettings.addEntities(new Oar(7,3));
        engineSettings.sortEntities();
        engineSettings.engineOarLeftRight(sailorTest);
        assertEquals(1,engineSettings.getRightSailors().size());
        assertEquals(0,engineSettings.getLeftSailors().size());
    }

    @Test
    void engineOarLeftRightTest2(){
        Sailor sailorTest=new Sailor(3,7,1,"name");
        engineSettings.addDeck(new Deck(5,10));
        engineSettings.addSailors(sailorTest);
        engineSettings.addEntities(new Oar(7,1));
        engineSettings.sortEntities();
        engineSettings.engineOarLeftRight(sailorTest);
        assertEquals(0,engineSettings.getRightSailors().size());
        assertEquals(1,engineSettings.getLeftSailors().size());
    }

    @Test
    void engineLiftSailActionTest(){
        Sailor sailorTest=new Sailor(3,0,1,"name");
        engineSettings.addSailors(sailorTest);
        engineSettings.addEntities(new Sail(0,1,false));
        engineSettings.sortEntities();
        engineSettings.engineLiftSailAction(sailorTest);
        assertEquals(1,engineSettings.getNbSailUsed());
    }
    @Test
    void engineLiftSailActionTest2(){
        Sailor sailorTest=new Sailor(3,0,1,"name");
        engineSettings.addSailors(sailorTest);
        engineSettings.addEntities(new Sail(1,1,false));
        engineSettings.sortEntities();
        engineSettings.engineLiftSailAction(sailorTest);
        assertEquals(0,engineSettings.getNbSailUsed());
    }

    @Test
    void engineLowerSailActionTest(){
        Sailor sailorTest=new Sailor(3,0,1,"name");
        engineSettings.addSailors(sailorTest);
        engineSettings.addEntities(new Sail(0,1,false));
        engineSettings.sortEntities();
        engineSettings.engineLiftSailAction(sailorTest);
        engineSettings.engineLowerSailAction(sailorTest);
        assertEquals(0,engineSettings.getNbSailUsed());
    }
    @Test
    void engineLowerSailActionTest2(){
        Sailor sailorTest=new Sailor(3,0,1,"name");
        engineSettings.addSailors(sailorTest);
        engineSettings.addEntities(new Sail(0,1,false));
        engineSettings.sortEntities();
        engineSettings.engineLowerSailAction(sailorTest);
        assertEquals(0,engineSettings.getNbSailUsed());
    }

    @Ignore
    void calculWindTest(){
        Sailor sailorTest=new Sailor(3,0,1,"name");
        engineSettings.addSailors(sailorTest);
        engineSettings.addEntities(new Sail(0,1,false));
        engineSettings.sortEntities();
        engineSettings.engineLiftSailAction(sailorTest);
        engineSettings.calculWind();
    }

}
