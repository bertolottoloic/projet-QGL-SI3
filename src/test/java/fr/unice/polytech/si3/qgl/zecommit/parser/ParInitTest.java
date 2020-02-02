package fr.unice.polytech.si3.qgl.zecommit.parser;

import com.fasterxml.jackson.core.JsonProcessingException;
import fr.unice.polytech.si3.qgl.zecommit.boat.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParInitTest {
    String json;
    String jsonTwo;
    String jsonNR;

    @BeforeEach
    void setUp() {
        json =  "{\n" +
                "\"position\": {\n"+
                "\"x\": 1000,\n" +
                "\"y\": 0,\n" +
                "\"orientation\": 0\n" +
                "}\n" +
                "}";


          jsonTwo = "{" +
                  "\"position\": {\n" +
                "      \"x\": 1000,\n" +
                "      \"y\": 0,\n" +
                "      \"orientation\": 0\n" +
                "    },\n" +
                "}";

        jsonNR = "{\n "+
                "    \"position\": {\n "+
                "      \"x\": 0,\n "+
                "      \"y\": 0,\n "+
                "      \"orientation\": 0\n "+
                "    },\n" +
                "}";

    }

    /*
    void parseinitPositionTest() throws JsonProcessingException {
        ParInit parse = new ParInit();
        Position positionParse = parse.parse(json);

        assertEquals(positionParse.getY(), 1000);
    }

     */
}
