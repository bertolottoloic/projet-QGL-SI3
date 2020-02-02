package fr.unice.polytech.si3.qgl.zecommit.engine;

import com.fasterxml.jackson.core.JsonProcessingException;
import fr.unice.polytech.si3.qgl.zecommit.Cockpit;
import fr.unice.polytech.si3.qgl.zecommit.boat.Position;
import fr.unice.polytech.si3.qgl.zecommit.crew.Sailor;
import fr.unice.polytech.si3.qgl.zecommit.entite.Oar;
import fr.unice.polytech.si3.qgl.zecommit.other.Checkpoint;
import fr.unice.polytech.si3.qgl.zecommit.shape.Circle;

import java.util.ArrayList;

/**
 * Classe simulant l'appel du projet
 * @auteur Clement P
 */
public class Engine {

    public static void main(String [] args) throws JsonProcessingException {



        String json="{\n" +
                "  \"goal\": {\n" +
                "    \"mode\": \"REGATTA\",\n" +
                "    \"checkpoints\": [\n" +
                "      {\n" +
                "        \"position\": {\n" +
                "          \"x\": 100,\n" +
                "          \"y\": 1000,\n" +
                "          \"orientation\": 0\n" +
                "        },\n" +
                "        \"shape\": {\n" +
                "          \"type\": \"circle\",\n" +
                "          \"radius\": 50\n" +
                "        }\n" +
                "      }\n" +
                "    ]\n" +
                "  },\n" +
                "  \"shipCount\": 1,\n" +
                "  \"ship\": {\n" +
                "    \"type\": \"ship\",\n" +
                "    \"life\": 100,\n" +
                "    \"position\": {\n" +
                "      \"x\": 0,\n" +
                "      \"y\": 0,\n" +
                "      \"orientation\": 0\n" +
                "    },\n" +
                "    \"name\": \"Les copaings d'abord!\",\n" +
                "    \"deck\": {\n" +
                "      \"width\": 2,\n" +
                "      \"length\": 1\n" +
                "    },\n" +
                "    \"entities\": [\n" +
                "      {\n" +
                "        \"x\": 1,\n" +
                "        \"y\": 0,\n" +
                "        \"type\": \"oar\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"x\": 0,\n" +
                "        \"y\": 1,\n" +
                "        \"type\": \"oar\"\n" +
                "      }\n" +
                "    ],\n" +
                "    \"shape\": {\n" +
                "      \"type\": \"rectangle\",\n" +
                "      \"width\": 2,\n" +
                "      \"height\": 3,\n" +
                "      \"orientation\": 0\n" +
                "    }\n" +
                "  },\n" +
                "  \"sailors\": [\n" +
                "    {\n" +
                "      \"x\": 0,\n" +
                "      \"y\": 0,\n" +
                "      \"id\": 0,\n" +
                "      \"name\": \"Edward Teach\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"x\": 0,\n" +
                "      \"y\": 0,\n" +
                "      \"id\": 1,\n" +
                "      \"name\": \"Tom Pouce\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        Cockpit cockpit = new Cockpit();
        cockpit.initGame(json);

        int step=0;
        double x=0;
        double y =0;
        double orientation =0;
        String json2 = "{\n" +
                "  \"ship\": {\n" +
                "    \"type\": \"ship\",\n" +
                "    \"life\": 100,\n" +
                "    \"position\": {\n" +
                "      \"x\": "+x+",\n" +
                "      \"y\": "+y+",\n" +
                "      \"orientation\": "+orientation+"\n" +
                "    },\n" +
                "    \"name\": \"Les copaings d'abord!\",\n" +
                "    \"deck\": {\n" +
                "      \"width\": 2,\n" +
                "      \"length\": 1\n" +
                "    },\n" +
                "    \"entities\": [\n" +
                "      {\n" +
                "        \"x\": 1,\n" +
                "        \"y\": 0,\n" +
                "        \"type\": \"oar\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"x\": 0,\n" +
                "        \"y\": 1,\n" +
                "        \"type\": \"oar\"\n" +
                "      }\n" +
                "    ]\n" +
                "  },\n" +
                "  \"visibleEntities\": []\n" +
                "}";

        ArrayList<Oar> oarArrayList= new ArrayList<>();
        oarArrayList.add(new Oar(1,0));
        oarArrayList.add(new Oar(0,1));
        ArrayList<Sailor> sailorArrayList= new ArrayList<>();
        sailorArrayList.add(new Sailor(0,0,0,"Edward Teach"));
        sailorArrayList.add(new Sailor(1,0,0,"Tom Pouce"));
        ArrayList<Checkpoint> checkpointArrayList= new ArrayList<>();
        checkpointArrayList.add(new Checkpoint(new Position(100,1000,0),new Circle(50)));
        InfoEngine infoEngine = new InfoEngine(oarArrayList,sailorArrayList,checkpointArrayList);
        String output = cockpit.nextRound(json2);
        while(!output.equals("[]")) {

                //TODO Multicheckpoints


                System.out.println(output);
                EngineNextRound engineNextRound = new EngineNextRound(output, x, y,orientation, infoEngine);
                x = engineNextRound.getX();
                y = engineNextRound.getY();
                orientation = engineNextRound.getOrientation();
                cockpit.getLogs().add("\nROUND "+step + " :"+ "("+x+","+y+")");
                System.out.println("ROUND "+step + " :"+ "("+x+","+y+")");
                System.out.println(orientation);
                step++;



                for(int j=0;j<infoEngine.getOarList().size();j++){
                    infoEngine.getOarList().get(j).setUsed(false);
                }
                json2 = "{\n" +
                        "  \"ship\": {\n" +
                        "    \"type\": \"ship\",\n" +
                        "    \"life\": 100,\n" +
                        "    \"position\": {\n" +
                        "      \"x\": "+x+",\n" +
                        "      \"y\": "+y+",\n" +
                        "      \"orientation\": "+orientation+"\n" +
                        "    },\n" +
                        "    \"name\": \"Les copaings d'abord!\",\n" +
                        "    \"deck\": {\n" +
                        "      \"width\": 2,\n" +
                        "      \"length\": 1\n" +
                        "    },\n" +
                        "    \"entities\": [\n" +
                        "      {\n" +
                        "        \"x\": 0,\n" +
                        "        \"y\": 0,\n" +
                        "        \"type\": \"oar\"\n" +
                        "      },\n" +
                        "      {\n" +
                        "        \"x\": 1,\n" +
                        "        \"y\": 0,\n" +
                        "        \"type\": \"oar\"\n" +
                        "      }\n" +
                        "    ]\n" +
                        "  },\n" +
                        "  \"visibleEntities\": []\n" +
                        "}";
            output = cockpit.nextRound(json2);
            }
            //System.out.println(cockpit.getLogs());
    }
}
