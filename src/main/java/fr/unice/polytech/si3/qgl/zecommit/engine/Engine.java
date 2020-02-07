package fr.unice.polytech.si3.qgl.zecommit.engine;

import com.fasterxml.jackson.core.JsonProcessingException;
import fr.unice.polytech.si3.qgl.zecommit.Cockpit;


/**
 * Classe simulant l'appel du projet
 * @auteur Clement P
 */
public class Engine {

    public static void main(String [] args) throws JsonProcessingException {


        EngineSettings engineSettings= new EngineSettings();
        String json= engineSettings.thisToJson();

        Cockpit cockpit = new Cockpit();
        cockpit.initGame(json);

        int steps=engineSettings.getN();
        String output="";
        while(output!="[]") {
            for (int i = 0; i < steps; i++) {
                String json2 = engineSettings.thisToJson2();
                output = cockpit.nextRound(json2);
                EngineNextRound engineNextRound= new EngineNextRound();
                engineSettings.updateEngine(engineNextRound.getEngineNextRound(output));

            }
        }

        System.out.println(cockpit.getLogs());




    }
}
