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
import fr.unice.polytech.si3.qgl.zecommit.Logs;
import fr.unice.polytech.si3.qgl.zecommit.goal.Goal;
import fr.unice.polytech.si3.qgl.zecommit.goal.Regatta;
import fr.unice.polytech.si3.qgl.zecommit.other.Checkpoint;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GoalDeserializer extends StdDeserializer<Goal> {


    public GoalDeserializer(Class<?> vc) {
        super(vc);
    }
    public GoalDeserializer() {
        this(null);
    }

    @Override
    public Goal deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {

        Goal goal;

        ObjectMapper objectMapper = new ObjectMapper();

        ObjectCodec codec = jsonParser.getCodec();
        JsonNode node = codec.readTree(jsonParser);

        String type = node.get("mode").asText();
        System.out.println(node.toPrettyString());

        if (type.equals("REGATTA")) {

            goal = new Regatta(objectMapper.readValue(node.get("checkpoints").toPrettyString(), new TypeReference<>() {}));
            return goal;
        }

        else {
            Logs.add("PBGoalDeser");
            return null;
        }

    }
}
