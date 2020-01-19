package fr.unice.polytech.si3.qgl.teamid;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import fr.unice.polytech.si3.qgl.teamid.action.Action;
import fr.unice.polytech.si3.qgl.teamid.action.Lift_Sail;
import fr.unice.polytech.si3.qgl.teamid.action.Lower_Sail;
import fr.unice.polytech.si3.qgl.teamid.action.Moving;
import fr.unice.polytech.si3.qgl.teamid.action.Oar;
import fr.unice.polytech.si3.qgl.teamid.action.Turn;
import fr.unice.polytech.si3.qgl.teamid.action.Use_Watch;

class SortieTest {
    Sortie s;
    ArrayList<Action> actions;

    @Test
    void afficheSortieTest(){
        String resultString = "[{\"sailorId\":1,\"type\":\"OAR\"},"+
        "{\"sailorId\":2,\"type\":\"MOVING\",\"xdistance\":3,\"ydistance\":4},"+
        "{\"sailorId\":3,\"type\":\"LIFT_SAIL\"},"+
        "{\"sailorId\":4,\"type\":\"LOWER_SAIL\"},"+
        "{\"sailorId\":5,\"type\":\"TURN\",\"rotation\":3.5},"+
        "{\"sailorId\":6,\"type\":\"USE_WATCH\"}]";
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