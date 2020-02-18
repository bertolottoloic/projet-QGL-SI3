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
        String json = engineSettings.thisToJson();
        EngineNextRound engineNextRound= new EngineNextRound();
        Cockpit cockpit = new Cockpit();
        cockpit.initGame(json);
        int currentStep = 0;
        String output="";
        while(!output.equals("[]")&&currentStep<3000) {
            System.out.println("ROUND :"+currentStep);
            currentStep++;
            String json2 = engineSettings.thisToJson2();
            output = cockpit.nextRound(json2);
            System.out.println(output);
            engineSettings.updateEngine(engineNextRound.getEngineNextRound(output));
            System.out.println(engineSettings.getShip().getPosition()+"\nFIN DU ROUND\n");
        }

        //System.out.println(cockpit.getLogs());




    }
}
