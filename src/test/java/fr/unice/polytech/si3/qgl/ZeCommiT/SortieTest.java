package fr.unice.polytech.si3.qgl.ZeCommiT;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import fr.unice.polytech.si3.qgl.ZeCommiT.action.Action;
import fr.unice.polytech.si3.qgl.ZeCommiT.action.Lift_Sail;
import fr.unice.polytech.si3.qgl.ZeCommiT.action.Lower_Sail;
import fr.unice.polytech.si3.qgl.ZeCommiT.action.Moving;
import fr.unice.polytech.si3.qgl.ZeCommiT.action.Oar;
import fr.unice.polytech.si3.qgl.ZeCommiT.action.Turn;
import fr.unice.polytech.si3.qgl.ZeCommiT.action.Use_Watch;

class SortieTest {
    Sortie s;
    ArrayList<Action> actions;

    @Test
    void afficheSortieTest(){
        String resultString = "[ {\n" +
                "  \"sailorId\" : 1,\n" +
                "  \"type\" : \"OAR\"\n" +
                "}, {\n" +
                "  \"sailorId\" : 2,\n" +
                "  \"type\" : \"MOVING\",\n" +
                "  \"xdistance\" : 3,\n" +
                "  \"ydistance\" : 4\n" +
                "}, {\n" +
                "  \"sailorId\" : 3,\n" +
                "  \"type\" : \"LIFT_SAIL\"\n" +
                "}, {\n" +
                "  \"sailorId\" : 4,\n" +
                "  \"type\" : \"LOWER_SAIL\"\n" +
                "}, {\n" +
                "  \"sailorId\" : 5,\n" +
                "  \"type\" : \"TURN\",\n" +
                "  \"rotation\" : 3.5\n" +
                "}, {\n" +
                "  \"sailorId\" : 6,\n" +
                "  \"type\" : \"USE_WATCH\"\n" +
                "} ]";
        s = new Sortie();
        Action o1 = new Oar(1);
        Action o2 = new Moving(2,3,4);
        Action o3 = new Lift_Sail(3);
        Action o4 = new Lower_Sail(4);
        Action o5 = new Turn(5,3.5);
        Action o6 = new Use_Watch(6);
        List<Action> actions =List.of(o1,o2,o3,o4,o5,o6);
        assertEquals(resultString, s.afficheRound(actions));

    }

}