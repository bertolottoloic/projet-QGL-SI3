package fr.unice.polytech.si3.qgl.zecommit.visualisationtools;

import fr.unice.polytech.si3.qgl.zecommit.Cockpit;
import fr.unice.polytech.si3.qgl.zecommit.boat.Position;
import fr.unice.polytech.si3.qgl.zecommit.crew.Sailor;
import fr.unice.polytech.si3.qgl.zecommit.entite.Entity;
import fr.unice.polytech.si3.qgl.zecommit.visualisationtools.deckvizu.DeckVizu;
import fr.unice.polytech.si3.qgl.zecommit.visualisationtools.exception.CollisionException;
import fr.unice.polytech.si3.qgl.zecommit.visualisationtools.exception.UnfinishedException;
import fr.unice.polytech.si3.qgl.zecommit.visualisationtools.settings.EngineSettingsInterface;
import fr.unice.polytech.si3.qgl.zecommit.visualisationtools.settings.EngineSettingsWeek11;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.profile.StackProfiler;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
//@Fork(value = 2, jvmArgs = {"-Xms2G", "-Xmx2G"})
@Fork()
@Warmup(iterations = 2)
@Measurement(iterations = 5)

public class MyBenchmark {

    public static boolean showWindow = true;
    public static boolean showDeck = false;
    public static List<List<Sailor>> SAILORS_VIZU;
    public static List<Entity> ENTITIES_VIZU;


    public static EngineSettingsInterface engineSettings = new EngineSettingsWeek11();//A modifier pour changer la simulation

    private Engine engine;

    public static void main(String[] args) throws RunnerException {

        org.openjdk.jmh.runner.options.Options opt = new OptionsBuilder()
                .include(MyBenchmark.class.getSimpleName())
                .shouldDoGC(true)
                .resultFormat(ResultFormatType.JSON)
                .result(".result/result" + System.currentTimeMillis() +".JSON")

                .addProfiler(StackProfiler.class)
                .jvmArgsAppend("-Dmjh.stack.period=1")
                .forks(1)
                .build();

        new Runner(opt).run();

    }



    @Benchmark
    public void engineTestTime() throws UnfinishedException, CollisionException {
        List<Position> positions = new ArrayList<>();
        SAILORS_VIZU = new ArrayList<>();

        engineSettings.initiateSettings();

        ENTITIES_VIZU = engineSettings.getEntities();

        EngineCalcul engineCalcul =  new EngineCalcul(engineSettings);
        String json = engineCalcul.thisToJson();
        System.out.println(json);
        EngineNextRound engineNextRound = new EngineNextRound();
        Cockpit cockpit = new Cockpit();
        cockpit.initGame(json);

        int currentStep = 0;
        int maxStep = 300;
        String output = "";
        while (!output.equals("[]") && currentStep < maxStep) {
            System.out.println("ROUND :" + currentStep);
            currentStep++;
            SAILORS_VIZU.add(engineSettings.getSailors());
            String json2 = engineCalcul.thisToJson2();
            //System.out.println(json2);
            output = cockpit.nextRound(json2);
            System.out.println(output);

            try {
                engineCalcul.updateEngine(engineNextRound.getEngineNextRound(output));
            } catch (Exception e) {
                System.err.println(e.getMessage());//affiche une exception en cas de collision
                throw new CollisionException();// a commenter pour ne pas interrompre le code
            }
            Position position = engineSettings.getShip().getPosition();
            positions.add(position);
            System.out.println(position + "\nFIN DU ROUND\n");
            if(currentStep==maxStep) {
                if(showWindow)
                    new Window(positions, engineSettings.getAllCheckpoints(), engineSettings.getVisibleEntities());
                throw new UnfinishedException();
            }
        }

        if(showWindow)
            new Window(positions, engineSettings.getAllCheckpoints(), engineSettings.getVisibleEntities());


        if(showDeck)
            new DeckVizu();

        String dieses = "##################################################################################################";
        System.out.println(dieses + "############################################## Logs ##############################################" + dieses);
        System.out.println(cockpit.getLogs());
        System.out.println(dieses + "########################################## Fin des Logs ##########################################" + dieses);
    }
}
