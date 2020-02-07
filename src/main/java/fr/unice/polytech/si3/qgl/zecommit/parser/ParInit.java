package fr.unice.polytech.si3.qgl.zecommit.parser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import fr.unice.polytech.si3.qgl.zecommit.boat.Position;
import fr.unice.polytech.si3.qgl.zecommit.crew.Sailor;
import fr.unice.polytech.si3.qgl.zecommit.deserializer.EntityDeserializer;
import fr.unice.polytech.si3.qgl.zecommit.deserializer.PositionDeserializer;
import fr.unice.polytech.si3.qgl.zecommit.deserializer.SailorDeserializer;
import fr.unice.polytech.si3.qgl.zecommit.deserializer.ShapeDeserializer;
import fr.unice.polytech.si3.qgl.zecommit.entite.Entity;
import fr.unice.polytech.si3.qgl.zecommit.shape.Shape;

public class ParInit {

    public Position parse(String jsonString) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();

        module.addDeserializer(Position.class, new PositionDeserializer());
        module.addDeserializer(Shape.class, new ShapeDeserializer());
        module.addDeserializer(Entity.class, new EntityDeserializer());
        module.addDeserializer(Sailor.class, new SailorDeserializer());

        mapper.registerModule(module);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        return mapper.readValue(jsonString, Position.class);
    }
}


