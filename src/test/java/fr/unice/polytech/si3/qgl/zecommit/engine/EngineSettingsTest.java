package fr.unice.polytech.si3.qgl.zecommit.engine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EngineSettingsTest{
    EngineSettings es;

    @BeforeEach
    void setUp(){
        es = new EngineSettings();
    }

    @Test
    void thisToJsonTest(){
        System.out.println(es.thisToJson());
        System.out.println(es.thisToJson2());
    }
}