package fr.unice.polytech.si3.qgl.zecommit.engine;

import fr.unice.polytech.si3.qgl.zecommit.Cockpit;
import fr.unice.polytech.si3.qgl.zecommit.boat.Position;

import java.util.ArrayList;


/**
 * Classe simulant l'appel du projet
 * @auteur Clement P
 */
public class Engine {


    public static void main(String[] args) throws CollisionException {

        ArrayList<Position> positions = new ArrayList<>();

        EngineSettings engineSettings = new EngineSettings();
        engineSettings.initiateSettings();
        EngineCalcul engineCalcul =  new EngineCalcul(engineSettings);
        String json = engineCalcul.thisToJson();
        System.out.println(json);
        EngineNextRound engineNextRound = new EngineNextRound();
        Cockpit cockpit = new Cockpit();
        cockpit.initGame(json);

        int currentStep = 0;
        String output = "";
        while (!output.equals("[]") && currentStep < 500) {
            System.out.println("ROUND :" + currentStep);
            currentStep++;
            String json2 = engineCalcul.thisToJson2();
            System.out.println(json2);
            output = cockpit.nextRound(json2);
            System.out.println(output);

            try {
                engineCalcul.updateEngine(engineNextRound.getEngineNextRound(output));
            } catch (Exception e) {
                System.err.println(e.getMessage());//affiche une exception en cas de collision
                throw new CollisionException();
            }
            Position position = engineSettings.getShip().getPosition();
            positions.add(position);
            System.out.println(position + "\nFIN DU ROUND\n");
        }

        Window fenetre = new Window(positions, engineSettings.getAllCheckpoints(), engineSettings.getVisibleEntities());

        System.out.println("##################################################################################################");
        System.out.println("############################################## Logs ##############################################");
        System.out.println("##################################################################################################");

        System.out.println(cockpit.getLogs());

        System.out.println("##################################################################################################");
        System.out.println("########################################## Fin des Logs ##########################################");
        System.out.println("##################################################################################################");

    }
}
