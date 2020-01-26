package fr.unice.polytech.si3.qgl.zecommit.parser;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.databind.SerializationFeature;
import fr.unice.polytech.si3.qgl.zecommit.action.*;

/**
 * @author Loic Bertolotto
 */

public class Output{
    ObjectMapper oM;
    List<Action> actions;


    public Output(){
        oM = new ObjectMapper();
    }

    public String afficheRound(List<Action> actions){
        try{
            return oM.writeValueAsString(actions);
        } catch (IOException e){
            System.out.println(e.getMessage());
            return "[]";
        }
    }

}