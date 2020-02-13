package fr.unice.polytech.si3.qgl.zecommit.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.unice.polytech.si3.qgl.zecommit.boat.Deck;
import fr.unice.polytech.si3.qgl.zecommit.boat.Position;
import fr.unice.polytech.si3.qgl.zecommit.entite.Entity;
import fr.unice.polytech.si3.qgl.zecommit.other.Checkpoint;
import fr.unice.polytech.si3.qgl.zecommit.other.OtherShip;
import fr.unice.polytech.si3.qgl.zecommit.shape.Shape;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class OtherShipDeserializer extends JsonDeserializer {
    @Override
    public OtherShip deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        ObjectCodec oc = jsonParser.getCodec();
        JsonNode node = oc.readTree(jsonParser);


        Position position = objectMapper.readValue(node.get("position").toPrettyString(), Position.class);

        Deck deck = objectMapper.readValue(node.get("deck").toPrettyString(), Deck.class);


        List<Entity> listEntitie = objectMapper.readValue(node.get("entities").toPrettyString(), new TypeReference<List<Entity>>() {});


        Shape shape = objectMapper.readValue(node.get("shape").toPrettyString(), Shape.class);
        
        return new OtherShip(node.get("life").asInt(), position, node.get("name").asText(), deck, listEntitie, shape);
    }
}