package fr.unice.polytech.si3.qgl.zecommit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.InvalidDefinitionException;
import fr.unice.polytech.si3.qgl.zecommit.entite.*;
import fr.unice.polytech.si3.qgl.zecommit.other.*;
import fr.unice.polytech.si3.qgl.zecommit.shape.Circle;
import fr.unice.polytech.si3.qgl.zecommit.shape.Rectangle;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ParserNext {
    public NextRound parserNextRound(String jsonString) throws JsonProcessingException {
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
        List<Entities> listEntitie = new ArrayList<>();
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
            List<VisibleEntitie> visibleEntities = new ArrayList<>();
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
