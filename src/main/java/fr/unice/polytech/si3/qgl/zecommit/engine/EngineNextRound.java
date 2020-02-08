package fr.unice.polytech.si3.qgl.zecommit.engine;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.unice.polytech.si3.qgl.zecommit.action.Action;
import fr.unice.polytech.si3.qgl.zecommit.action.Moving;
import fr.unice.polytech.si3.qgl.zecommit.action.ToOar;


import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author clement
 */
public class EngineNextRound {
    ArrayList<Action> actionArrayList;

    public ArrayList<Action> getEngineNextRound(String jsonOutput)throws JsonProcessingException {
        this.actionArrayList=new ArrayList<>();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        JsonNode rootNode = objectMapper.readTree(jsonOutput);
        Iterator<JsonNode> iteratorShip = rootNode.iterator();
        while (iteratorShip.hasNext()) {
            JsonNode current = iteratorShip.next();
            JsonNode sailorId = current.path("sailorId");
            String id = sailorId.asText();
            JsonNode type = current.path("type");
            String textType = type.asText();
            if(textType.equals("OAR")) {
                ToOar toOar= new ToOar(Integer.valueOf(id));
                actionArrayList.add(toOar);
            }
            if(textType.equals("MOVING")) {
                JsonNode xdistanceNode = current.path("xdistance");
                JsonNode ydistanceNode = current.path("ydistance");
                int xDistance = Integer.valueOf(xdistanceNode.asText());
                int yDistance = Integer.valueOf(ydistanceNode.asText());
                Moving moving= new Moving(Integer.valueOf(id),xDistance,yDistance);
                actionArrayList.add(moving);
            }
        }


        return actionArrayList;
    }




    // ----------------- GETTER --------------------------------------


}
