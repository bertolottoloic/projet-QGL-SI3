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
                "          \"x\": 1000,\n" +
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
                "      \"width\": 4,\n" +
                "      \"length\": 10\n" +
                "    },\n" +
                "    \"entities\": [\n" +
                "      {\n" +
                "        \"x\": 2,\n" +
                "        \"y\": 0,\n" +
                "        \"type\": \"oar\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"x\": 2,\n" +
                "        \"y\": 3,\n" +
                "        \"type\": \"oar\"\n" +
                "      },\n" +
                "       {\n" +
                "        \"x\": 7,\n" +
                "        \"y\": 0,\n" +
                "        \"type\": \"oar\"\n" +
                "      },\n"
                +
                "       {\n" +
                "        \"x\": 7,\n" +
                "        \"y\": 3,\n" +
                "        \"type\": \"oar\"\n" +
                "      }\n"
                +
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
                "      \"x\": 4,\n" +
                "      \"y\": 1,\n" +
                "      \"id\": 0,\n" +
                "      \"name\": \"Edward Teach\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"x\": 4,\n" +
                "      \"y\": 2,\n" +
                "      \"id\": 1,\n" +
                "      \"name\": \"Tom Pouce\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"x\": 5,\n" +
                "      \"y\": 2,\n" +
                "      \"id\": 2,\n" +
                "      \"name\": \"Tom Pouce\"\n" +
                "    },\n"+
                "    {\n" +
                "      \"x\": 5,\n" +
                "      \"y\": 1,\n" +
                "      \"id\": 3,\n" +
                "      \"name\": \"Tom Pouce\"\n" +
                "    }\n"+
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
                "      \"width\": 4,\n" +
                "      \"length\": 10\n" +
                "    },\n" +
                "    \"entities\": [\n" +
                "   {\n" +
                "        \"x\": 2,\n" +
                "        \"y\": 0,\n" +
                "        \"type\": \"oar\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"x\": 2,\n" +
                "        \"y\": 3,\n" +
                "        \"type\": \"oar\"\n" +
                "      },\n" +
                "       {\n" +
                "        \"x\": 7,\n" +
                "        \"y\": 0,\n" +
                "        \"type\": \"oar\"\n" +
                "      },\n"
                +
                "       {\n" +
                "        \"x\": 7,\n" +
                "        \"y\": 3,\n" +
                "        \"type\": \"oar\"\n" +
                "      }\n"+
                "    ]\n" +
                "  },\n" +
                "  \"visibleEntities\": []\n" +
                "}";

        ArrayList<Oar> oarArrayList= new ArrayList<>();
        oarArrayList.add(new Oar(2,0));
        oarArrayList.add(new Oar(2,3));
        oarArrayList.add(new Oar(7,0));
        oarArrayList.add(new Oar(7,3));

        ArrayList<Sailor> sailorArrayList= new ArrayList<>();
        sailorArrayList.add(new Sailor(0,4,1,"Edward Teach"));
        sailorArrayList.add(new Sailor(1,4,2,"Tom Pouce"));
        sailorArrayList.add(new Sailor(2,5,2,"Tom Pouce"));
        sailorArrayList.add(new Sailor(3,5,1,"Tom Pouce"));



        ArrayList<Checkpoint> checkpointArrayList= new ArrayList<>();
        checkpointArrayList.add(new Checkpoint(new Position(1000,1000,0),new Circle(50)));
        InfoEngine infoEngine = new InfoEngine(oarArrayList,sailorArrayList,checkpointArrayList);
        String output = cockpit.nextRound(json2);
        while(!output.equals("[]")&& step<30) {

                //TODO Multicheckpoints


                EngineNextRound engineNextRound = new EngineNextRound(output, x, y,orientation, infoEngine);
                x = engineNextRound.getX();
                y = engineNextRound.getY();
                orientation = engineNextRound.getOrientation();
                cockpit.getLogs().add("\nROUND "+step + " :"+ "("+x+","+y+")");

                step++;




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
                        "      \"width\": 4,\n" +
                        "      \"length\": 10\n" +
                        "    },\n" +
                        "    \"entities\": [\n" +
                        "{\n" +
            "        \"x\": 2,\n" +
                    "        \"y\": 0,\n" +
                    "        \"type\": \"oar\"\n" +
                    "      },\n" +
                    "      {\n" +
                    "        \"x\": 2,\n" +
                    "        \"y\": 3,\n" +
                    "        \"type\": \"oar\"\n" +
                    "      },\n" +
                    "       {\n" +
                    "        \"x\": 7,\n" +
                    "        \"y\": 0,\n" +
                    "        \"type\": \"oar\"\n" +
                    "      },\n"
                    +
                    "       {\n" +
                    "        \"x\": 7,\n" +
                    "        \"y\": 3,\n" +
                    "        \"type\": \"oar\"\n" +
                    "      }\n"
                        +
                        "    ]\n" +
                        "  },\n" +
                        "  \"visibleEntities\": []\n" +
                        "}";
            output = cockpit.nextRound(json2);
            }
            System.out.println(cockpit.getLogs());
    }
}
