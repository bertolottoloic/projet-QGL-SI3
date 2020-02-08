package fr.unice.polytech.si3.qgl.zecommit.parser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.unice.polytech.si3.qgl.zecommit.Game;
import fr.unice.polytech.si3.qgl.zecommit.Logs;
import fr.unice.polytech.si3.qgl.zecommit.crew.Sailor;
import fr.unice.polytech.si3.qgl.zecommit.boat.Deck;
import fr.unice.polytech.si3.qgl.zecommit.boat.Position;
import fr.unice.polytech.si3.qgl.zecommit.boat.Ship;
import fr.unice.polytech.si3.qgl.zecommit.goal.Battle;
import fr.unice.polytech.si3.qgl.zecommit.goal.Regatta;
import fr.unice.polytech.si3.qgl.zecommit.other.*;
import fr.unice.polytech.si3.qgl.zecommit.shape.Circle;
import fr.unice.polytech.si3.qgl.zecommit.entite.*;
import fr.unice.polytech.si3.qgl.zecommit.shape.Rectangle;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Parseur du fichier Json
 * @author Vincent et Nathan
 */
public class ParserInit {
    int nbSailors = 0;

    public Game parserInitGame(String jsonString) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(jsonString);

        JsonNode goalMode = rootNode.path("goal").path("mode");
        String mode = goalMode.asText();
        Game game;

        switch (mode) {
            case "REGATTA":
                //Création des checkpoints
                Iterator<JsonNode> it = rootNode.path("goal").path("checkpoints").iterator();
                List<Checkpoint> checkpoints = new ArrayList<>();
                while (it.hasNext()) {
                    JsonNode current = it.next();
                    JsonNode position = current.path("position");
                    JsonNode shape = current.path("shape");
                    JsonNode type = shape.path("type");

                    Position p = objectMapper.readValue(position.toString(), Position.class);
                    if(type.asText().equals("circle")) {
                        Circle c = objectMapper.readValue(shape.toString(), Circle.class);
                        checkpoints.add(new Checkpoint(p, c));
                    }
                    else {
                        Rectangle r = objectMapper.readValue(shape.toString(), Rectangle.class);
                        checkpoints.add(new Checkpoint(p, r));
                    }
                }


                Regatta regatta = new Regatta(checkpoints);
                game = new Game();
                game.setGoal(regatta);
                break;

            case "BATTLE":
                Battle battle = new Battle();
                game = new Game();
                game.setGoal(battle);
                break;

            default:
                Logs.add("PB1-Parseur");
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
        List<Entity> listEntitie = new ArrayList<>();
        while (iteratorShip.hasNext()) {
            JsonNode current = iteratorShip.next();
            JsonNode type = current.path("type");
            String textType = type.asText();
            switch (textType) {
                case "sail":
                    Sail sail = objectMapper.readValue(current.toString(), Sail.class);
                    listEntitie.add(sail);
                    break;
                case "oar":
                    Oar oar = objectMapper.readValue(current.toString(), Oar.class);
                    listEntitie.add(oar);
                    break;
                case "rudder":
                    Rudder rudder = objectMapper.readValue(current.toString(), Rudder.class);
                    listEntitie.add(rudder);
                    break;
                case "watch":
                    Watch watch = objectMapper.readValue(current.toString(), Watch.class);
                    listEntitie.add(watch);
                    break;
            }
        }
        game.setEntityList(listEntitie);
        Position positionShip = objectMapper.readValue(positionShipN.toString(), Position.class);
        int lifeShip = objectMapper.readValue(lifeShipN.toString(), int.class);
        String nameShip = objectMapper.readValue(nameShipN.toString(), String.class);
        Deck deckShip = objectMapper.readValue(deckShipN.toString(), Deck.class);

        Ship ship;

        JsonNode type = shapeShipN.path("type");

        switch (type.asText()) {
            case "rectangle":
                Rectangle rectangleShip = objectMapper.readValue(shapeShipN.toString(), Rectangle.class);
                ship = new Ship("ship",lifeShip, positionShip, nameShip, deckShip, listEntitie, rectangleShip);
                break;

            case "circle":
                Circle circleShip = objectMapper.readValue(shapeShipN.toString(), Circle.class);
                ship = new Ship("ship",lifeShip, positionShip, nameShip, deckShip, listEntitie, circleShip);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + shapeShipN.asText());
        }


        game.setShip(ship);


        //Création des marins
        JsonNode sailorsNode = rootNode.path("sailors");
        Iterator<JsonNode> sailorsIterator = sailorsNode.iterator();
        List<Sailor> sailorsList = new ArrayList<>();
        while (sailorsIterator.hasNext()) {
            nbSailors += 1;
            JsonNode current = sailorsIterator.next();
            sailorsList.add(objectMapper.readValue(current.toString(), Sailor.class));
        }
        game.setSailors(sailorsList);


        JsonNode shipCountNode = rootNode.path("shipCount");
        game.setShipCount(objectMapper.readValue(shipCountNode.toString(), int.class));
        return game;
    }
}
    


