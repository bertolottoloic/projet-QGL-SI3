package fr.unice.polytech.si3.qgl.ZeCommiT;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.databind.SerializationFeature;
import fr.unice.polytech.si3.qgl.ZeCommiT.action.*;

/**
 * @author Loic Bertolotto
 */

class Sortie{
    ObjectMapper oM;
    List<Action> actions;


    Sortie(){
        oM = new ObjectMapper();
    }

    String afficheRound(List<Action> actions){
        try{
            oM.configure(SerializationFeature.INDENT_OUTPUT, true);//Option pour ne pas afficher la sortie sur une seule ligne
            return oM.writeValueAsString(actions);
        } catch (IOException e){
            System.out.println(e.getMessage());
            return "[]";
        }
    }

}