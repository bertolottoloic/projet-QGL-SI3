package fr.unice.polytech.si3.qgl.zecommit.visualisationtools;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.unice.polytech.si3.qgl.zecommit.action.*;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * PArtie du moteur qui gère les nextRound
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
                oarParser(id);
            }
            if(textType.equals("MOVING")) {
                movingParser(id,current);
            }
            if(textType.equals("TURN")) {
                turnParser(id,current);
            }
            if(textType.equals("LIFT_SAIL")) {
                liftSail(id);
            }
            if(textType.equals("LOWER_SAIL")) {
                lowerSail(id);
            }
            if(textType.equals("USE_WATCH")){
                watchParser(id);
            }
        }


        return actionArrayList;
    }

    public void oarParser(String id){
        ToOar toOar= new ToOar(Integer.parseInt(id));
        actionArrayList.add(toOar);
    }

    public void liftSail(String id){
        LiftSail liftSail= new LiftSail(Integer.parseInt(id));
        actionArrayList.add(liftSail);
    }
    public void lowerSail(String id){
        LowerSail lowerSail= new LowerSail(Integer.parseInt(id));
        actionArrayList.add(lowerSail);
    }

    public void watchParser(String id){
        UseWatch useWatch = new UseWatch(Integer.parseInt(id));
        actionArrayList.add(useWatch);
    }

    public void movingParser(String id,JsonNode current){
        JsonNode xdistanceNode = current.path("xdistance");
        JsonNode ydistanceNode = current.path("ydistance");
        int xDistance = Integer.parseInt(xdistanceNode.asText());
        int yDistance = Integer.parseInt(ydistanceNode.asText());
        Moving moving= new Moving(Integer.parseInt(id),xDistance,yDistance);
        actionArrayList.add(moving);
    }

    public void turnParser(String id, JsonNode current){
        JsonNode rotationNode = current.path("rotation");
        double rotation= Double.parseDouble(rotationNode.asText());
        Turn turn = new Turn(Integer.parseInt(id),rotation);
        actionArrayList.add(turn);
    }




    // ----------------- GETTER --------------------------------------


}
