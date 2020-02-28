package fr.unice.polytech.si3.qgl.zecommit.engine;

import fr.unice.polytech.si3.qgl.zecommit.boat.Deck;
import fr.unice.polytech.si3.qgl.zecommit.boat.Position;
import fr.unice.polytech.si3.qgl.zecommit.boat.Ship;
import fr.unice.polytech.si3.qgl.zecommit.crew.Sailor;
import fr.unice.polytech.si3.qgl.zecommit.entite.Oar;
import fr.unice.polytech.si3.qgl.zecommit.other.Checkpoint;
import fr.unice.polytech.si3.qgl.zecommit.shape.Circle;
import fr.unice.polytech.si3.qgl.zecommit.shape.Rectangle;
import fr.unice.polytech.si3.qgl.zecommit.shape.Shape;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class EngineSettingsTest{
    String json1;

    @BeforeEach
    void setUp() {
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
    void thisToJsonTest(){
        EngineSettings engineSettings = new EngineSettings();
        engineSettings.createList();
        Checkpoint checkpoint = new Checkpoint(new Position(1000,0,0), new Circle(50));
        engineSettings.addCheckpoint(checkpoint);
        engineSettings.setGoal();

        engineSettings.addEntities(new Oar(0,0));
        engineSettings.addEntities(new Oar(0,1));

        engineSettings.addSailors(new Sailor(0,0,0,"Edward Teach"));
        engineSettings.addSailors(new Sailor(1,0,1,"Tom Pouce"));

        Deck deck = new Deck(2,1);
        engineSettings.createDeck(deck);
        Shape shape = new Rectangle(2,3,0);
        engineSettings.createShip(new Ship("ship", 100, new Position(0,0, 0),"Les copaings d'abord!", deck, engineSettings.getEntities(), shape  ));

        assertEquals(engineSettings.thisToJson(), json1);
    }
}
