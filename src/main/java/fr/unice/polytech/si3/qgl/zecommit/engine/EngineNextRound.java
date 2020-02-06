package fr.unice.polytech.si3.qgl.zecommit.engine;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.unice.polytech.si3.qgl.zecommit.crew.Sailor;
import fr.unice.polytech.si3.qgl.zecommit.entite.Oar;

import java.util.Iterator;

/**
 * @author clement
 */
public class EngineNextRound {
    int nbRameActive;
    double x;
    double y;
    double orientation;

    public EngineNextRound(String jsonOutput,double x,double y, double orientation, InfoEngine infoEngine)throws JsonProcessingException {
        this.nbRameActive=0;
        this.x=x;
        this.y=y;
        this.orientation=orientation;
        int rameDroite = 0;
        int rameGauche = 0;
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
                for (Sailor sailor: infoEngine.getSailorList()) {
                    if(sailor.getId()==Integer.valueOf(id)){
                        for(Oar oar: infoEngine.oarList){
                            if(oar.getY()==sailor.getY()&&oar.getX()==sailor.getX()&&!oar.isUsed()){
                                nbRameActive++;
                                oar.setUsed(true);
                                if(oar.getY()<=3){
                                    rameGauche++;
                                }
                                else
                                {
                                    rameDroite++;
                                }
                            }
                        }
                    }
                }

            }
            if(textType.equals("MOVING")) {
                JsonNode xdistanceNode = current.path("xdistance");
                JsonNode ydistanceNode = current.path("ydistance");
                int xDistance = Integer.valueOf(xdistanceNode.asText());
                int yDistance = Integer.valueOf(ydistanceNode.asText());


                if(xDistance+yDistance<=5){
                    for(Sailor sailor:infoEngine.sailorList){
                        if(sailor.getId()==Integer.valueOf(id)){
                            sailor.move(xDistance,yDistance);
                        }
                    }
                }
            }



        }
        double currentOrientation=orientation;
        double gap=Math.PI/(infoEngine.getOarList().size());
        double difference= Math.abs(rameDroite-rameGauche);
        if(rameDroite>rameGauche){
            currentOrientation+=gap*difference;
        }
        else if(rameDroite<rameGauche){
            currentOrientation-=gap*difference;
        }

        this.orientation=currentOrientation%(Math.PI);
        double vitesse= (double)165*nbRameActive/infoEngine.getOarList().size();
        this.x +=vitesse*Math.cos(orientation);
        this.y +=vitesse*Math.sin(orientation);

    }



    // ----------------- GETTER --------------------------------------
    public double getOrientation() {
        return orientation;
    }

    public double getY() {
        return y;
    }

    public double getX() {
        return x;
    }

    public int getNbRameActive() {
        return nbRameActive;
    }

}
