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
                "\t\"position\" : {\n"+
                "\t\t\"x\" : 1000,\n" +
                "\t\t\"y\" : 0,\n" +
                "\t\t\"orientation\" : 0\n" +
                "\t}\n" +
                "}";

    }

    /*
    void parseinitPositionTest() throws JsonProcessingException {
        ParInit parse = new ParInit();
        Position positionParse = parse.parse(json);

<<<<<<< HEAD
=======
        assertEquals(positionParse.getY(), 1000);
>>>>>>> bf3c7d499185b132a8be6d7150c6e977960b551e
    }

     */
}
