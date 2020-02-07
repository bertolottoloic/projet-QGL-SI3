package fr.unice.polytech.si3.qgl.zecommit.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import fr.unice.polytech.si3.qgl.zecommit.boat.Position;


import java.io.IOException;
import java.util.concurrent.atomic.DoubleAccumulator;




public class PositionDeserializer extends StdDeserializer<Position> {


    protected PositionDeserializer(Class<?> vc) {
        super(vc);
    }

    public PositionDeserializer(){
        this(null);
    }

    @Override
    public Position deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        ObjectCodec oc = jsonParser.getCodec();
        JsonNode node = oc.readTree(jsonParser);
        return new Position(node.get("x").asDouble(), node.get("y").asDouble(), node.get("orientation").asDouble());
    }
}


