package fr.unice.polytech.si3.qgl.ZeCommiT;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import fr.unice.polytech.si3.qgl.ZeCommiT.goal.Battle;
import fr.unice.polytech.si3.qgl.ZeCommiT.goal.Goal;
import fr.unice.polytech.si3.qgl.ZeCommiT.goal.Regatta;

import java.io.IOException;

public class InitGameDeserializer extends JsonDeserializer<InitGame> {

    @Override
    public InitGame deserialize(JsonParser parser, DeserializationContext deserializer) throws IOException {
        InitGame initGame = new InitGame();
        ObjectCodec codec = parser.getCodec();
        JsonNode node = codec.readTree(parser);

        // try catch block
        JsonNode goalNode = node.get("goal");
        String goalString = goalNode.asText();
        Goal goal;
        if (goalString.equals("BATTLE"))
            goal = new Battle();
        else
            goal = new Regatta(null);


        initGame.setGoal(goal);



        return initGame;
    }
}
