package fr.unice.polytech.si3.qgl.zecommit.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import fr.unice.polytech.si3.qgl.zecommit.boat.Position;
import fr.unice.polytech.si3.qgl.zecommit.other.OtherShip;
import fr.unice.polytech.si3.qgl.zecommit.other.Reef;
import fr.unice.polytech.si3.qgl.zecommit.other.Stream;
import fr.unice.polytech.si3.qgl.zecommit.shape.Shape;

import java.io.IOException;

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

        switch (type) {
            case "stream":
                Position position = objectMapper.readValue(node.get("position").toPrettyString(), Position.class);
                Shape shape = objectMapper.readValue(node.get("shape").toPrettyString(), Shape.class);
                return new Stream(position, shape, node.get("strength").asDouble());
            case "ship":
                Position shipPosition = objectMapper.readValue(node.get("position").toPrettyString(), Position.class);
                Shape shipShape = objectMapper.readValue(node.get("shape").toPrettyString(), Shape.class);
                return new OtherShip(node.get("life").asInt(), shipPosition, shipShape);
            case "reef":
                Position reefPosition = objectMapper.readValue(node.get("position").toPrettyString(), Position.class);
                Shape reefShape = objectMapper.readValue(node.get("shape").toPrettyString(), Shape.class);
                return new Reef(reefPosition, reefShape);

            default:
                return null;
        }
    }
}
