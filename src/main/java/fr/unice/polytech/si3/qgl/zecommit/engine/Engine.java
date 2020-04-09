package fr.unice.polytech.si3.qgl.zecommit.engine;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.unice.polytech.si3.qgl.zecommit.Cockpit;
import fr.unice.polytech.si3.qgl.zecommit.boat.Position;
import fr.unice.polytech.si3.qgl.zecommit.crew.Sailor;
import fr.unice.polytech.si3.qgl.zecommit.deckvizu.DeckVizu;
import fr.unice.polytech.si3.qgl.zecommit.engine.settings.*;

import java.util.ArrayList;
import java.util.List;


/**
 * Classe simulant l'appel du projet
 * @author Clement P
 */
public class Engine {
    public static boolean showWindow = false;
    public static boolean showDeck = true;
    public static List<List<Sailor>> DECKVIZU;


    public static EngineSettingsInterface engineSettings = new EngineSettingsWeek8();//A modifier pour changer la simulation

    public static void main(String[] args) throws CollisionException {

        List<Position> positions = new ArrayList<>();
        DECKVIZU = new ArrayList<>();

        engineSettings.initiateSettings();
        EngineCalcul engineCalcul =  new EngineCalcul(engineSettings);
        String json = engineCalcul.thisToJson();
        System.out.println(json);
        EngineNextRound engineNextRound = new EngineNextRound();
        Cockpit cockpit = new Cockpit();
        cockpit.initGame(json);

        int currentStep = 0;
        String output = "";
        while (!output.equals("[]") && currentStep < 10) {
            System.out.println("ROUND :" + currentStep);
            currentStep++;
            String json2 = engineCalcul.thisToJson2();
            //System.out.println(json2);
            output = cockpit.nextRound(json2);
            DECKVIZU.add(engineSettings.getSailors());
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

        String dieses = "##################################################################################################";
        System.out.println(dieses + "############################################## Logs ##############################################" + dieses);
        //System.out.println(cockpit.getLogs());
        System.out.println(dieses + "########################################## Fin des Logs ##########################################" + dieses);

    }
}
