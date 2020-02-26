package fr.unice.polytech.si3.qgl.zecommit.engine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class EngineSettingsTest{
    EngineSettings es;
    String json;

    @BeforeEach
    void setUp(){
        es = new EngineSettings();
        json = "{\"goal\":{\"mode\":\"REGATTA\",\"checkpoints\":[{\"position\":{\"x\":1600.0,\"y\":350.0,\"orientation\":0.0},\"shape\":{\"type\":\"circle\",\"radius\":50.0}},{\"position\":{\"x\":345.0,\"y\":1550.0,\"orientation\":0.0},\"shape\":{\"type\":\"circle\",\"radius\":50.0}},{\"position\":{\"x\":0.0,\"y\":0.0,\"orientation\":0.0},\"shape\":{\"type\":\"circle\",\"radius\":70.0}}]},\"ship\":{\"type\":\"ship\",\"position\":{\"x\":0.0,\"y\":0.0,\"orientation\":0.0},\"name\":\"Drakkar\",\"deck\":{\"width\":5,\"length\":11},\"entities\":[{\"x\":1,\"y\":0,\"type\":\"oar\"},{\"x\":2,\"y\":0,\"type\":\"oar\"},{\"x\":3,\"y\":0,\"type\":\"oar\"},{\"x\":4,\"y\":0,\"type\":\"oar\"},{\"x\":5,\"y\":0,\"type\":\"oar\"},{\"x\":6,\"y\":0,\"type\":\"oar\"},{\"x\":7,\"y\":0,\"type\":\"oar\"},{\"x\":8,\"y\":0,\"type\":\"oar\"},{\"x\":9,\"y\":0,\"type\":\"oar\"},{\"x\":1,\"y\":4,\"type\":\"oar\"},{\"x\":2,\"y\":4,\"type\":\"oar\"},{\"x\":3,\"y\":4,\"type\":\"oar\"},{\"x\":4,\"y\":4,\"type\":\"oar\"},{\"x\":5,\"y\":4,\"type\":\"oar\"},{\"x\":6,\"y\":4,\"type\":\"oar\"},{\"x\":7,\"y\":4,\"type\":\"oar\"},{\"x\":8,\"y\":4,\"type\":\"oar\"},{\"x\":9,\"y\":4,\"type\":\"oar\"},{\"x\":10,\"y\":4,\"type\":\"rudder\"},{\"x\":5,\"y\":2,\"type\":\"sail\",\"openned\":false}],\"life\":2200,\"shape\":{\"type\":\"rectangle\",\"width\":5.0,\"height\":11.0,\"orientation\":0.0}},\"sailors\":[{\"x\":0,\"y\":0,\"id\":0,\"name\":\"Luffy Teach\"},{\"x\":0,\"y\":1,\"id\":1,\"name\":\"Luffy Teach\"},{\"x\":0,\"y\":2,\"id\":2,\"name\":\"Jack Teach\"},{\"x\":0,\"y\":3,\"id\":3,\"name\":\"Luffy Teach\"},{\"x\":0,\"y\":4,\"id\":4,\"name\":\"Edward Teach\"},{\"x\":1,\"y\":0,\"id\":5,\"name\":\"Jack Teach\"},{\"x\":1,\"y\":1,\"id\":6,\"name\":\"Jack Pouce\"},{\"x\":1,\"y\":2,\"id\":7,\"name\":\"Jack Pouce\"},{\"x\":1,\"y\":3,\"id\":8,\"name\":\"Luffy Teach\"},{\"x\":1,\"y\":4,\"id\":9,\"name\":\"Edward Teach\"},{\"x\":2,\"y\":0,\"id\":10,\"name\":\"Tom Pouce\"},{\"x\":2,\"y\":1,\"id\":11,\"name\":\"Edward Teach\"},{\"x\":2,\"y\":2,\"id\":12,\"name\":\"Tom Pouce\"},{\"x\":2,\"y\":3,\"id\":13,\"name\":\"Luffy Pouce\"},{\"x\":2,\"y\":4,\"id\":14,\"name\":\"Edward Pouce\"},{\"x\":3,\"y\":0,\"id\":15,\"name\":\"Luffy Teach\"},{\"x\":3,\"y\":1,\"id\":16,\"name\":\"Jack Teach\"},{\"x\":3,\"y\":2,\"id\":17,\"name\":\"Jack Pouce\"},{\"x\":3,\"y\":3,\"id\":18,\"name\":\"Luffy Pouce\"},{\"x\":3,\"y\":4,\"id\":19,\"name\":\"Tom Pouce\"}],\"shipCount\":1}";
    }


    @Test
    @Disabled
    void thisToJsonTest(){
        EngineSettings engineSettings = new EngineSettings();
        assertEquals(engineSettings.thisToJson2(), json);
    }
}
