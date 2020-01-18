package fr.unice.polytech.si3.qgl.teamid;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.unice.polytech.si3.qgl.teamid.action.*;

/**
 * @author Loic Bertolotto
 */

class Sortie{
    ObjectMapper oM;

    Sortie(){ 
        oM = new ObjectMapper(); 
    }

    String afficheRound(List<Action> actions){
        try{
            return oM.writeValueAsString(actions);
        } catch (IOException e){
            System.out.println(e.getMessage());
            return "";
        }
    }

}