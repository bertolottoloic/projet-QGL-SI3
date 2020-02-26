package fr.unice.polytech.si3.qgl.zecommit.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import fr.unice.polytech.si3.qgl.zecommit.boat.Deck;
import fr.unice.polytech.si3.qgl.zecommit.boat.Position;
import fr.unice.polytech.si3.qgl.zecommit.entite.Entity;
import fr.unice.polytech.si3.qgl.zecommit.other.OtherShip;
import fr.unice.polytech.si3.qgl.zecommit.other.Reef;
import fr.unice.polytech.si3.qgl.zecommit.other.Stream;
import fr.unice.polytech.si3.qgl.zecommit.other.VisibleEntitie;
import fr.unice.polytech.si3.qgl.zecommit.shape.Shape;

import java.io.IOException;
import java.util.List;

public class VisibleEntitiesDeserializer extends StdDeserializer {
    protected VisibleEntitiesDeserializer(Class vc) {
        super(vc);
    }
    public VisibleEntitiesDeserializer() {
        this(null);
    }

    @Override
    public Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        ObjectCodec codec = jsonParser.getCodec();
        JsonNode node = codec.readTree(jsonParser);

        String type = node.get("type").asText();

        if (type.equals("stream")){
            Position position = objectMapper.readValue(node.get("position").toPrettyString(), Position.class);
            Shape shape = objectMapper.readValue(node.get("shape").toPrettyString(), Shape.class);
            return new Stream(position, shape, node.get("strength").asDouble());
        }
        else if (type.equals("ship")) {
            Position position = objectMapper.readValue(node.get("position").toPrettyString(), Position.class);
            Deck deck = objectMapper.readValue(node.get("deck").toPrettyString(), Deck.class);
            Shape shape = objectMapper.readValue(node.get("shape").toPrettyString(), Shape.class);
            List<Entity> entities = objectMapper.readValue(node.get("entities").toPrettyString(), new TypeReference<>() {});
            return new OtherShip(node.get("life").asInt(), position, shape);
        }
        else if (type.equals("reef")) {
            Position position = objectMapper.readValue(node.get("position").toPrettyString(), Position.class);
            Shape shape = objectMapper.readValue(node.get("shape").toPrettyString(), Shape.class);
            return new Reef(position, shape);
        }
        return null;
    }
}
