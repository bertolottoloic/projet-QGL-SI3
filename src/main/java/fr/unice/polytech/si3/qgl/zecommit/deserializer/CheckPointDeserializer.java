package fr.unice.polytech.si3.qgl.zecommit.deserializer;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import fr.unice.polytech.si3.qgl.zecommit.boat.Position;
import fr.unice.polytech.si3.qgl.zecommit.other.Checkpoint;
import fr.unice.polytech.si3.qgl.zecommit.shape.Shape;


import java.io.IOException;

public class CheckPointDeserializer extends JsonDeserializer {

    @Override
    public Checkpoint deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        ObjectCodec oc = jsonParser.getCodec();
        JsonNode node = oc.readTree(jsonParser);

        PositionDeserializer positionDeserializer = new PositionDeserializer();
        Position position = positionDeserializer.deserialize(jsonParser, deserializationContext);

        ShapeDeserializer shapeDeserializer = new ShapeDeserializer();
        Shape shape = shapeDeserializer.deserialize(jsonParser, deserializationContext);


        return new Checkpoint(position, shape);
    }
}
