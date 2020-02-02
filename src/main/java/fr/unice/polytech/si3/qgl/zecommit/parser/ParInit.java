package fr.unice.polytech.si3.qgl.zecommit.parser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import fr.unice.polytech.si3.qgl.zecommit.Game;
import fr.unice.polytech.si3.qgl.zecommit.boat.Position;
/*
public class ParInit {

    public Position parse(String jsonString) throws JsonProcessingException {

        SimpleModule module = new SimpleModule();
        module.addDeserializer(Position.class, new PositionDeserializer());

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(module);

        Position readValue = mapper.readValue(jsonString, Position.class);

        return readValue;
    }
}

 */
