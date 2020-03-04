package fr.unice.polytech.si3.qgl.zecommit.engine;

import com.fasterxml.jackson.core.JsonProcessingException;
import fr.unice.polytech.si3.qgl.zecommit.Cockpit;
import fr.unice.polytech.si3.qgl.zecommit.boat.Position;

import java.util.ArrayList;


/**
 * Classe simulant l'appel du projet
 * @auteur Clement P
 */
public class Engine {

    public static void main(String [] args) throws JsonProcessingException {
        ArrayList<Position> positions = new ArrayList<>();

        EngineSettings engineSettings= new EngineSettings();
        engineSettings.initiateSettings();
        String json = engineSettings.thisToJson();
        System.out.println(json);
        EngineNextRound engineNextRound= new EngineNextRound();
        Cockpit cockpit = new Cockpit();
        cockpit.initGame(json);

        int currentStep = 0;
        String output="";
        while(!output.equals("[]")&&currentStep<500) {
            System.out.println("ROUND :"+currentStep);
            currentStep++;
            String json2 = engineSettings.thisToJson2();
            //System.out.println(json2);
            output = cockpit.nextRound(json2);
            System.out.println(output);
            engineSettings.updateEngine(engineNextRound.getEngineNextRound(output));
            Position position = engineSettings.getShip().getPosition();
            positions.add(position);
            System.out.println(position+"\nFIN DU ROUND\n");
        }
        System.out.println("##################################################################################################");
        System.out.println("############################################## Logs ##############################################");
        System.out.println("##################################################################################################");

        System.out.println(cockpit.getLogs());

        System.out.println("##################################################################################################");
        System.out.println("########################################## Fin des Logs ##########################################");
        System.out.println("##################################################################################################");





    }
}
