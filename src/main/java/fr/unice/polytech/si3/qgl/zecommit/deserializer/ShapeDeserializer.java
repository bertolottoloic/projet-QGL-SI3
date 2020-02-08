package fr.unice.polytech.si3.qgl.zecommit.deserializer;

/*

public class ShapeDeserializer extends StdDeserializer<Shape> {

    public ShapeDeserializer(Class<?> vc) {super(vc);}


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

 */
