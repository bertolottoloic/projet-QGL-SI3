package fr.unice.polytech.si3.qgl.zecommit.deserializer;

/*
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
*/
