package fr.unice.polytech.si3.qgl.teamid;

import com.fasterxml.jackson.core.JsonProcessingException;
<<<<<<< HEAD
=======
import fr.unice.polytech.si3.qgl.teamid.InitGame;
import fr.unice.polytech.si3.qgl.teamid.Parser;
>>>>>>> 9f5a6bc042e8b0a7378e4ae5cdafb319c5e6c74b
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

<<<<<<< HEAD
    String jsonFile;

    @BeforeEach
    public void setup(){
        jsonFile = "{\n" +
                "  \"goal\": {\n" +
                "    \"mode\": \"FRIGATE\",\n" +
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
                "      },\n" +
                "      {\n" +
                "        \"position\": {\n" +
                "          \"x\": 0,\n" +
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
                "      \"width\": 3,\n" +
                "      \"length\": 6\n" +
                "    },\n" +
                "    \"entities\": [\n" +
                "      {\n" +
                "        \"x\": 1,\n" +
                "        \"y\": 0,\n" +
                "        \"type\": \"oar\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"x\": 1,\n" +
                "        \"y\": 2,\n" +
                "        \"type\": \"oar\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"x\": 3,\n" +
                "        \"y\": 0,\n" +
                "        \"type\": \"oar\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"x\": 3,\n" +
                "        \"y\": 2,\n" +
                "        \"type\": \"oar\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"x\": 4,\n" +
                "        \"y\": 0,\n" +
                "        \"type\": \"oar\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"x\": 4,\n" +
                "        \"y\": 2,\n" +
                "        \"type\": \"oar\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"x\": 2,\n" +
                "        \"y\": 1,\n" +
                "        \"type\": \"sail\",\n" +
                "        \"openned\": false\n" +
                "      },\n" +
                "      {\n" +
                "        \"x\": 5,\n" +
                "        \"y\": 0,\n" +
                "        \"type\": \"rudder\"\n" +
                "      }\n" +
                "    ],\n" +
                "    \"shape\": {\n" +
                "      \"type\": \"rectangle\",\n" +
                "      \"width\": 3,\n" +
                "      \"height\": 6,\n" +
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
                "      \"x\": 0,\n" +
                "      \"y\": 1,\n" +
                "      \"id\": 1,\n" +
                "      \"name\": \"Edward Pouce\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"x\": 0,\n" +
                "      \"y\": 2,\n" +
                "      \"id\": 2,\n" +
                "      \"name\": \"Tom Pouce\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"x\": 1,\n" +
                "      \"y\": 0,\n" +
                "      \"id\": 3,\n" +
                "      \"name\": \"Jack Teach\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"x\": 1,\n" +
                "      \"y\": 1,\n" +
                "      \"id\": 4,\n" +
                "      \"name\": \"Jack Teach\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"x\": 1,\n" +
                "      \"y\": 2,\n" +
                "      \"id\": 5,\n" +
                "      \"name\": \"Tom Pouce\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";
    }

    @Test
    void attributTest() throws JsonProcessingException {
        InitGame initGame = Parser.parserInitGame(jsonFile);
        String mode = initGame.getGoal().getMode();

        assertEquals("FRIGATE", mode);
    }


=======
    @Test
    void initGameTest() {
    }

>>>>>>> 9f5a6bc042e8b0a7378e4ae5cdafb319c5e6c74b
}