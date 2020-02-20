package fr.unice.polytech.si3.qgl.zecommit.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


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


}
