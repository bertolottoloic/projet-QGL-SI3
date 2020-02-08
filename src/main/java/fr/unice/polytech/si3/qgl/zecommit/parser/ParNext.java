package fr.unice.polytech.si3.qgl.zecommit.parser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import fr.unice.polytech.si3.qgl.zecommit.boat.Position;
import fr.unice.polytech.si3.qgl.zecommit.crew.Sailor;
import fr.unice.polytech.si3.qgl.zecommit.deserializer.*;
import fr.unice.polytech.si3.qgl.zecommit.boat.Deck;

import fr.unice.polytech.si3.qgl.zecommit.entite.Entity;
import fr.unice.polytech.si3.qgl.zecommit.other.OtherShip;
import fr.unice.polytech.si3.qgl.zecommit.shape.Shape;

public class ParNext {

    public void parse(String jsonString) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();

        module.addDeserializer(Position.class, new PositionDeserializer());
        module.addDeserializer(Shape.class, new ShapeDeserializer());
        module.addDeserializer(Entity.class, new EntityDeserializer());
        module.addDeserializer(Sailor.class, new SailorDeserializer());
        module.addDeserializer(Deck.class, new DeckDeserializer());
        module.addDeserializer(Deck.class, new DeckDeserializer());
        module.addDeserializer(Shape.class, new ShapeDeserializer());
        module.addDeserializer(Entity.class, new EntityDeserializer());
        module.addDeserializer(Sailor.class, new SailorDeserializer());
        module.addDeserializer(OtherShip.class, new OtherShipDeserializer());

        mapper.registerModule(module);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }
}


