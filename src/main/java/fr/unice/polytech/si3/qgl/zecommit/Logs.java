package fr.unice.polytech.si3.qgl.zecommit;


import java.util.ArrayList;
import java.util.List;

/**
 * Classe générant les logs
 * @author Nathan
 */
public class Logs {

    public static List<String> logs = new ArrayList<>();

    public Logs() {
    }


    public static void add(String s){
        logs.add(s);
    }

    public static List<String> sortie(){
        List<String> shortLogs = new ArrayList<>();
        if(logs.size()>=200) {
            for(int i = 0; i<200; i++)
                shortLogs.add(logs.get(i));
            return shortLogs;
        }
        else
            return logs ;
        }
}
