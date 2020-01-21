package fr.unice.polytech.si3.qgl.zecommit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.InvalidDefinitionException;
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
public class Parser {
    public static int nbSailors = 0;
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
                    JsonNode type = shape.path("type");

                    Position p = objectMapper.readValue(position.toString(), Position.class);
                    switch (type.asText()) {
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

        JsonNode type = shapeShipN.path("type");

        switch (type.asText()) {
            case "rectangle":
                Rectangle rectangleShip = objectMapper.readValue(shapeShipN.toString(), Rectangle.class);
                ship = new Ship(lifeShip, positionShip, nameShip, deckShip, listEntitie, rectangleShip);
                break;

            case "circle":
                Circle circleShip = objectMapper.readValue(shapeShipN.toString(), Circle.class);
                ship = new Ship(lifeShip, positionShip, nameShip, deckShip, listEntitie, circleShip);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + shapeShipN.asText());
        }

        
        newInitGame.setShip(ship);


        //Création des marins
        JsonNode sailorsNode = rootNode.path("sailors");
        Iterator<JsonNode> sailorsIterator = sailorsNode.iterator();
        List<Sailor> sailorsList = new ArrayList<>();
        while (sailorsIterator.hasNext()){
            nbSailors+=1;
            JsonNode current = sailorsIterator.next();
            sailorsList.add(objectMapper.readValue(current.toString(), Sailor.class));
        }
        newInitGame.setSailors(sailorsList);


        JsonNode shipCountNode = rootNode.path("shipCount");
        newInitGame.setShipCount(objectMapper.readValue(shipCountNode.toString(), int.class));
        return newInitGame;
    }


    public static NextRound parserNextRound(String jsonString) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        JsonNode rootNode = objectMapper.readTree(jsonString);

        NextRound newNextRound = new NextRound();

        //Création du bateau
        JsonNode nodeShip = rootNode.path("ship");
        JsonNode positionShipN = nodeShip.path("position");
        JsonNode shapeShipN = nodeShip.path("shape");
        JsonNode lifeShipN = nodeShip.path("life");
        JsonNode nameShipN = nodeShip.path("name");
        JsonNode deckShipN = nodeShip.path("deck");

        Iterator<JsonNode> iteratorShip = nodeShip.path("entities").iterator();
        List<Entite> listEntitie = new ArrayList<>();
        while (iteratorShip.hasNext()) {
            JsonNode current = iteratorShip.next();
            JsonNode type = current.path("type");
            String textType = type.asText();
            switch (textType) {
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

        JsonNode type = shapeShipN.path("type");
        try{
            switch (type.asText()) {
                case "rectangle":
                    Rectangle rectangleShip = objectMapper.readValue(shapeShipN.toString(), Rectangle.class);
                    ship = new Ship(lifeShip, positionShip, nameShip, deckShip, listEntitie, rectangleShip);
                    break;

                case "circle":
                    Circle circleShip = objectMapper.readValue(shapeShipN.toString(), Circle.class);
                    ship = new Ship(lifeShip, positionShip, nameShip, deckShip, listEntitie, circleShip);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + shapeShipN.asText());
            }
        }
        catch(IllegalStateException e){
            ship=new Ship(lifeShip, positionShip, nameShip, deckShip, listEntitie, null);
            //System.out.println("No shape : "+e.toString());
        }

        newNextRound.setShip(ship);

        // Création des visibleEntities
        try {
            Iterator<JsonNode> iterator = nodeShip.path("visibleEntities").iterator();
            List<VisibleEntitie> visibleEntities = null;
            while (iterator.hasNext()) {
                JsonNode current = iterator.next();
                JsonNode typeVisibleEntities = current.path("type");
                String textType = typeVisibleEntities.asText();
                switch (textType) {
                    case "stream":
                        Courant courant = objectMapper.readValue(current.toString(), Courant.class);
                        visibleEntities.add(courant);
                        break;
                    case "reef":
                        Recif recif = objectMapper.readValue(current.toString(), Recif.class);
                        visibleEntities.add(recif);
                        break;
                    case "ship":
                        OtherShip otherShip = objectMapper.readValue(current.toString(), OtherShip.class);
                        visibleEntities.add(otherShip);
                        break;
                }
            }
            newNextRound.setVisibleEntities(visibleEntities);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }



        JsonNode ventN = rootNode.path("wind");
        try {
            Vent vent = objectMapper.readValue(ventN.toString(), Vent.class);
            newNextRound.setWind(objectMapper.readValue(vent.toString(), Vent.class));

        }
        catch (InvalidDefinitionException e){
            //System.out.println("No wind : "+e.toString());
        }


        return newNextRound;

    }


}