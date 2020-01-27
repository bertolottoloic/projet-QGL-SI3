package fr.unice.polytech.si3.qgl.zecommit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CockpitTest {

    Cockpit cockpit;
    String jsonInit;
    String jsonNR;

    @BeforeEach
    void setUp() {
        jsonNR = "{\n "+ 
                "  \"ship\": {\n "+ 
                "    \"type\": \"ship\",\n "+ 
                "    \"life\": 100,\n "+ 
                "    \"position\": {\n "+ 
                "      \"x\": 10.654,\n "+ 
                "      \"y\": 3,\n "+ 
                "      \"orientation\": 2.05\n "+ 
                "    },\n "+ 
                "    \"name\": \"Les copaings d'abord!\",\n "+ 
                "    \"deck\": {\n "+ 
                "      \"width\": 2,\n "+ 
                "      \"length\": 1\n "+ 
                "    },\n "+ 
                "    \"entities\": [\n "+ 
                "      {\n "+ 
                "        \"x\": 0,\n "+ 
                "        \"y\": 0,\n "+ 
                "        \"type\": \"oar\"\n "+ 
                "      },\n "+ 
                "      {\n "+ 
                "        \"x\": 1,\n "+ 
                "        \"y\": 0,\n "+ 
                "        \"type\": \"oar\"\n "+ 
                "      }\n "+ 
                "    ]\n "+ 
                "  },\n "+ 
                "  \"visibleEntities\": []\n "+ 
                "}";
    jsonInit = "{\n" +
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
        this.cockpit = new Cockpit();
        this.cockpit.initGame(jsonInit);
    }

    @Test
    void nextRoundTest() {
        assertEquals("[{\"sailorId\":0,\"type\":\"OAR\"},{\"sailorId\":1,\"type\":\"OAR\"}]", this.cockpit.nextRound(jsonNR));
    }

}
