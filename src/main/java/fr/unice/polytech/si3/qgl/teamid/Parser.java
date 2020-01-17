package fr.unice.polytech.si3.qgl.teamid;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.swing.text.Position;

public class Parseur {

    public static InitGame parserInitGame(String jsonString) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        InitGame initGame = objectMapper.readValue(jsonString, InitGame.class);

        return initGame;
    }



}
