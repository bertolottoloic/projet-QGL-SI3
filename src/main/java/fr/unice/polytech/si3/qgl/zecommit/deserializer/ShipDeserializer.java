package fr.unice.polytech.si3.qgl.zecommit.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import fr.unice.polytech.si3.qgl.zecommit.boat.Deck;
import fr.unice.polytech.si3.qgl.zecommit.boat.Position;
import fr.unice.polytech.si3.qgl.zecommit.boat.Ship;
import fr.unice.polytech.si3.qgl.zecommit.entite.*;
import fr.unice.polytech.si3.qgl.zecommit.shape.Shape;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class ShipDeserializer extends JsonDeserializer {

    @Override
    public Ship deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        ObjectCodec oc = jsonParser.getCodec();
        JsonNode node = oc.readTree(jsonParser);

        PositionDeserializer positionDeserializer = new PositionDeserializer();
        Position position = positionDeserializer.deserialize(jsonParser, deserializationContext);

        DeckDeserializer deckDeserializer = new DeckDeserializer();
        Deck deck = deckDeserializer.deserialize(jsonParser, deserializationContext);


        Iterator<JsonNode> iteratorShip = node.path("entities").iterator();
        List<Entity> listEntitie = new ArrayList<>();
        while (iteratorShip.hasNext()) {
            JsonNode current = iteratorShip.next();
            JsonNode type = current.path("type");
            String textType = type.asText();
            EntityDeserializer entityDeserializer = new EntityDeserializer();
            Entity entity = entityDeserializer.deserialize(jsonParser, deserializationContext);
            listEntitie.add(entity);
        }

        ShapeDeserializer shapeDeserializer = new ShapeDeserializer();
        Shape shape = shapeDeserializer.deserialize(jsonParser, deserializationContext);


        return new Ship(node.get("life").asInt(), position, node.get("name").asText(), deck, listEntitie, shape);
    }
}


