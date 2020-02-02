package fr.unice.polytech.si3.qgl.zecommit.engine;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

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
                for(int i=0; i<infoEngine.getOarList().size();i++){
                    for(int j=0;j<infoEngine.getSailorList().size();j++){
                        if(infoEngine.getSailorList().get(j).getX()==infoEngine.getOarList().get(i).getX()
                            && infoEngine.getSailorList().get(j).getY()==infoEngine.getOarList().get(i).getY()
                                &&!infoEngine.getOarList().get(i).isUsed()){
                            nbRameActive++;
                            infoEngine.getOarList().get(i).setUsed(true);
                            if(infoEngine.getOarList().get(i).getY()==0){
                                rameDroite++;
                            }
                            else
                            {
                                rameGauche++;
                            }
                        }
                    }
                }
            }
        }

        double marge=Math.PI/(infoEngine.oarList.size()+1);
        double val=orientation;
        if(rameDroite>rameGauche){
            val-=rameDroite*marge;
        }
        else if(rameDroite<rameGauche){
            val+=rameDroite*marge;
        }
        else {
            val=0;
        }
        this.orientation=val;
        double vitesse= 165*nbRameActive/infoEngine.getOarList().size();
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
