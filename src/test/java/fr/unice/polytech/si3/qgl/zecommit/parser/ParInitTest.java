package fr.unice.polytech.si3.qgl.zecommit.parser;

import org.junit.jupiter.api.BeforeEach;


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


}
