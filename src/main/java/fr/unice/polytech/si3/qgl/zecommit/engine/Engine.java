package fr.unice.polytech.si3.qgl.zecommit.engine;

import fr.unice.polytech.si3.qgl.zecommit.Cockpit;
import fr.unice.polytech.si3.qgl.zecommit.boat.Position;
import fr.unice.polytech.si3.qgl.zecommit.crew.Sailor;
import fr.unice.polytech.si3.qgl.zecommit.deckvizu.DeckVizu;
import fr.unice.polytech.si3.qgl.zecommit.engine.settings.EngineSettings;
import fr.unice.polytech.si3.qgl.zecommit.engine.settings.*;

import java.util.ArrayList;
import java.util.List;


/**
 * Classe simulant l'appel du projet
 * @auteur Clement P
 */
public class Engine {
    public static boolean showWindow = true;
    public static boolean showDeck = false;
    public static List<List<Sailor>> sailorsDeckVizu;

    public static EngineSettings engineSettings = new EngineSettingsWeek7();//A modifier pour changer la simulation

    public static void main(String[] args) throws CollisionException {

        List<Position> positions = new ArrayList<>();
        sailorsDeckVizu = new ArrayList<>();

        engineSettings.initiateSettings();
        EngineCalcul engineCalcul =  new EngineCalcul(engineSettings);
        String json = engineCalcul.thisToJson();
        System.out.println(json);
        EngineNextRound engineNextRound = new EngineNextRound();
        Cockpit cockpit = new Cockpit();
        cockpit.initGame(json);

        int currentStep = 0;
        String output = "";
        while (!output.equals("[]") && currentStep < 300) {
            System.out.println("ROUND :" + currentStep);
            currentStep++;
            String json2 = engineCalcul.thisToJson2();
            //System.out.println(json2);
            output = cockpit.nextRound(json2);
            sailorsDeckVizu.add(engineSettings.getSailors());
            System.out.println(output);

            try {
                engineCalcul.updateEngine(engineNextRound.getEngineNextRound(output));
            } catch (Exception e) {
                System.err.println(e.getMessage());//affiche une exception en cas de collision
                //throw new CollisionException();// a commenter pour ne pas interrompre le code
            }
            Position position = engineSettings.getShip().getPosition();
            positions.add(position);
            System.out.println(position + "\nFIN DU ROUND\n");
        }

        if(showWindow)
            new Window(positions, engineSettings.getAllCheckpoints(), engineSettings.getVisibleEntities());


        if(showDeck)
            new DeckVizu();


        System.out.println("##################################################################################################");
        System.out.println("############################################## Logs ##############################################");
        System.out.println("##################################################################################################");

        System.out.println(cockpit.getLogs());

        System.out.println("##################################################################################################");
        System.out.println("########################################## Fin des Logs ##########################################");
        System.out.println("##################################################################################################");

    }
}
