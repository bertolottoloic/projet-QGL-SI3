package fr.unice.polytech.si3.qgl.zecommit.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import fr.unice.polytech.si3.qgl.zecommit.Logs;
import fr.unice.polytech.si3.qgl.zecommit.goal.Goal;
import fr.unice.polytech.si3.qgl.zecommit.goal.Regatta;
import fr.unice.polytech.si3.qgl.zecommit.other.Checkpoint;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GoalDeserializer extends JsonDeserializer {

    public GoalDeserializer(Class<?> vc) {super();}
    public GoalDeserializer(){
        this(null);
    }

    @Override
    public Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {

        Goal goal;

        ObjectCodec codec = jsonParser.getCodec();
        JsonNode node = codec.readTree(jsonParser);

        String type = node.get("mode").asText();

        if (type.equals("REGATTA")) {

            Iterator<JsonNode> iteratorShip = node.path("entities").iterator();
            List<Checkpoint> listCheckPoint = new ArrayList<>();
            while (iteratorShip.hasNext()) {
                CheckPointDeserializer checkPointDeserializer = new CheckPointDeserializer();
                Checkpoint checkpoint = checkPointDeserializer.deserialize(jsonParser, deserializationContext);
                listCheckPoint.add(checkpoint);
            }

            return new Regatta(listCheckPoint);
        }

        else {
            Logs.add("PBGoalDeser");
            return null;
        }

    }
}
