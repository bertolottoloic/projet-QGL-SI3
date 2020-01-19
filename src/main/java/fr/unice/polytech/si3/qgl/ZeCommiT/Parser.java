package fr.unice.polytech.si3.qgl.ZeCommiT;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.unice.polytech.si3.qgl.ZeCommiT.goal.Battle;
import fr.unice.polytech.si3.qgl.ZeCommiT.goal.Regatta;
import fr.unice.polytech.si3.qgl.ZeCommiT.other.Checkpoint;
import fr.unice.polytech.si3.qgl.ZeCommiT.shape.Circle;
import fr.unice.polytech.si3.qgl.ZeCommiT.entite.*;
import fr.unice.polytech.si3.qgl.ZeCommiT.shape.Rectangle;
import fr.unice.polytech.si3.qgl.ZeCommiT.shape.Shape;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Parseur du fichier Json
 * @author Vincent et Nathan
 */
public class Parser {
    public static InitGame parserInitGame(String jsonString) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(jsonString);

        JsonNode goalMode = rootNode.path("goal").path("mode");
        String mode = goalMode.asText();
        InitGame newInitGame;

        switch (mode) {
            case "REGATTA":
                //Création des checkpoints
                Iterator<JsonNode> it = rootNode.path("goal").path("checkpoints").iterator();
                List<Checkpoint> checkpoints = new ArrayList<>();
                while (it.hasNext()) {
                    JsonNode current = it.next();
                    JsonNode position = current.path("position");
                    JsonNode shape = current.path("shape");

                    Position p = objectMapper.readValue(position.toString(), Position.class);
                    switch (shape.asText()) {
                        case "circle":
                            Circle c = objectMapper.readValue(shape.toString(), Circle.class);
                            checkpoints.add(new Checkpoint(p, c));
                            break;
                        case "rectangle":
                            Rectangle r = objectMapper.readValue(shape.toString(), Rectangle.class);
                            checkpoints.add(new Checkpoint(p, r));
                            break;
                    }
                }

                Regatta regatta = new Regatta(checkpoints);
                newInitGame = new InitGame();
                newInitGame.setGoal(regatta);
                break;

            case "BATTLE":
                Battle battle = new Battle();
                newInitGame = new InitGame();
                newInitGame.setGoal(battle);
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + mode);
        }




        //Création du Bateau
        JsonNode nodeShip = rootNode.path("ship");
        JsonNode positionShipN = nodeShip.path("position");
        JsonNode shapeShipN = nodeShip.path("shape");
        JsonNode lifeShipN = nodeShip.path("life");
        JsonNode nameShipN = nodeShip.path("name");
        JsonNode deckShipN = nodeShip.path("deck");

        Iterator<JsonNode> iteratorShip = nodeShip.path("entities").iterator();
        List<Entite> listEntitie = new ArrayList<>();
        while(iteratorShip.hasNext()){
            JsonNode current = iteratorShip.next();
            JsonNode type = current.path("type");
            String textType = type.asText();
            switch (textType){
                case "sail":
                    Voile voile = objectMapper.readValue(current.toString(), Voile.class);
                    listEntitie.add(voile);
                    break;
                case "oar":
                    Rame rame = objectMapper.readValue(current.toString(), Rame.class);
                    listEntitie.add(rame);
                    break;
                case "rudder":
                    Gouvernail gouvernail = objectMapper.readValue(current.toString(), Gouvernail.class);
                    listEntitie.add(gouvernail);
                    break;
                case "watch":
                    Vigie vigie = objectMapper.readValue(current.toString(), Vigie.class);
                    listEntitie.add(vigie);
                    break;
            }
        }
        Position positionShip = objectMapper.readValue(positionShipN.toString(), Position.class);
        int lifeShip = objectMapper.readValue(lifeShipN.toString(), int.class);
        String nameShip = objectMapper.readValue(nameShipN.toString(), String.class);
        Deck deckShip = objectMapper.readValue(deckShipN.toString(), Deck.class);

        Ship ship;


        switch (shapeShipN.asText()) {
            case "rectangle":
                Rectangle rectangleShip = objectMapper.readValue(shapeShipN.toString(), Rectangle.class);
                ship = new Ship(lifeShip, positionShip, nameShip, deckShip, listEntitie, rectangleShip);
                break;

            case "circle":
                Circle circleShip = objectMapper.readValue(shapeShipN.toString(), Circle.class);
                ship = new Ship(lifeShip, positionShip, nameShip, deckShip, listEntitie, circleShip);
                break;

            default:
                Rectangle rectangleShipDefault = objectMapper.readValue(shapeShipN.toString(), Rectangle.class);
                ship = new Ship(lifeShip, positionShip, nameShip, deckShip, listEntitie, rectangleShipDefault);        }

        
        newInitGame.setShip(ship);


        //Création des marins
        JsonNode sailorsNode = rootNode.path("sailors");
        Iterator<JsonNode> sailorsIterator = sailorsNode.iterator();
        List<Sailor> sailorsList = new ArrayList<>();
        while (sailorsIterator.hasNext()){
            JsonNode current = sailorsIterator.next();
            sailorsList.add(objectMapper.readValue(current.toString(), Sailor.class));
        }
        newInitGame.setSailors(sailorsList);


        JsonNode shipCountNode = rootNode.path("shipCount");
        newInitGame.setShipCount(objectMapper.readValue(shipCountNode.toString(), int.class));
        return newInitGame;
    }
}
