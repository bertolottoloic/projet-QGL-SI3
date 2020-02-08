package fr.unice.polytech.si3.qgl.zecommit.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import fr.unice.polytech.si3.qgl.zecommit.shape.Circle;
import fr.unice.polytech.si3.qgl.zecommit.shape.Rectangle;
import fr.unice.polytech.si3.qgl.zecommit.shape.Shape;

import java.io.IOException;
<<<<<<< HEAD
=======

>>>>>>> 0c04a343eebd557f1eec5f7252f441e235f3cfb1


<<<<<<< HEAD
public class ShapeDeserializer extends JsonDeserializer {

    public ShapeDeserializer(Class<?> vc) {super();}
=======
    public ShapeDeserializer(Class<?> vc) {super(vc);}
>>>>>>> 0c04a343eebd557f1eec5f7252f441e235f3cfb1
    public ShapeDeserializer(){
        this(null);
    }


    @Override
    public Shape deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {

        Shape shape;

        ObjectCodec codec = jsonParser.getCodec();
        JsonNode nodeShape = codec.readTree(jsonParser);

        String type = nodeShape.get("type").asText();

        if (type.equals("circle")) {
             return shape = new Circle(nodeShape.get("radius").asDouble());
        }

        return shape = new Rectangle(nodeShape.get("width").asDouble(), nodeShape.get("height").asDouble(), nodeShape.get("orientation").asDouble());

    }
}

