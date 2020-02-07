package fr.unice.polytech.si3.qgl.zecommit.parser;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class ParInitTest {
    String json;
    String jsonTwo;
    String jsonNR;

    @BeforeEach
    void setUp() {
        json =  "{\n"+
                "\t\t\"x\" : 1000,\n" +
                "\t\t\"y\" : 0,\n" +
                "\t\t\"orientation\" : 0\n" +
                "\t}";

    }

    @Test
    void positionTest() throws JsonProcessingException {
        ParInit parseInit = new ParInit();
        double x =  parseInit.parse(json).getX();
        assertEquals(1000, x);
    }


}
