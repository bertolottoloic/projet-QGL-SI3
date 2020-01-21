package fr.unice.polytech.si3.qgl.ZeCommiT;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class Parser {
    public static InitGame parserInitGame(String jsonString) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        SimpleModule module = new SimpleModule("InitGameDeserializer", new Version(1,0,0,null,null,null));
        module.addDeserializer(InitGame.class, new InitGameDeserializer());
        objectMapper.registerModule(module);

        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        InitGame initGame = objectMapper.readValue(jsonString, InitGame.class);


        return initGame;
    }
}
