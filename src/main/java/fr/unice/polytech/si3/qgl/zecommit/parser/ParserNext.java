package fr.unice.polytech.si3.qgl.zecommit.parser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.InvalidDefinitionException;
import fr.unice.polytech.si3.qgl.zecommit.Game;
import fr.unice.polytech.si3.qgl.zecommit.boat.Deck;
import fr.unice.polytech.si3.qgl.zecommit.boat.Position;
import fr.unice.polytech.si3.qgl.zecommit.boat.Ship;
import fr.unice.polytech.si3.qgl.zecommit.entite.*;
import fr.unice.polytech.si3.qgl.zecommit.other.*;
import fr.unice.polytech.si3.qgl.zecommit.shape.Circle;
import fr.unice.polytech.si3.qgl.zecommit.shape.Rectangle;
import fr.unice.polytech.si3.qgl.zecommit.shape.Shape;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ParserNext {
    public void parserNextRound(String jsonString, Game game)  throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        JsonNode rootNode = objectMapper.readTree(jsonString);

        //Création du bateau
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

        /**
         * J'ai rajouté le as text ici !!!! s'il y a un bug venir voir ici en prio !
         */
        //JsonNode type = shapeShipN.path("type");
        String type = shapeShipN.path("type").asText();
        /*
        if (type.equals("Shape")) {
            Shape shape = objectMapper.readValue(shapeShipN.toString(), Shape.class);
            ship = new Ship(lifeShip, positionShip, nameShip, deckShip, listEntitie, shape);

            game.setShip(ship);
        }

         */


        try{

            switch (type) {
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



        // Création des visibleEntities
        try {
            Iterator<JsonNode> iterator = nodeShip.path("visibleEntities").iterator();
            List<VisibleEntitie> visibleEntities = new ArrayList<>();
            while (iterator.hasNext()) {
                JsonNode current = iterator.next();
                JsonNode typeVisibleEntities = current.path("type");
                String textType = typeVisibleEntities.asText();
                switch (textType) {
                    case "stream":
                        Current courant = objectMapper.readValue(current.toString(), Current.class);
                        visibleEntities.add(courant);
                        break;
                    case "reef":
                        Reef reef = objectMapper.readValue(current.toString(), Reef.class);
                        visibleEntities.add(reef);
                        break;
                    case "ship":
                        OtherShip otherShip = objectMapper.readValue(current.toString(), OtherShip.class);
                        visibleEntities.add(otherShip);
                        break;
                }
            }
            game.setVisibleEntities(visibleEntities);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }



        JsonNode ventN = rootNode.path("wind");
        try {
            Wind wind = objectMapper.readValue(ventN.toString(), Wind.class);
            game.setWind(objectMapper.readValue(wind.toString(), Wind.class));

        }
        catch (InvalidDefinitionException e){
            //System.out.println("No wind : "+e.toString());
        }

    }

}
