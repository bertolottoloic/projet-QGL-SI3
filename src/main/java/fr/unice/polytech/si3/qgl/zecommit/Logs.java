package fr.unice.polytech.si3.qgl.zecommit;


import java.util.ArrayList;
import java.util.List;

/**
 * Classe générant les logs
 * @author Nathan
 */
public class Logs {
    Logs() {
    }

    protected static final List<String> myLogs = new ArrayList<>();

    public static void add(String s){
        myLogs.add(s);
    }

    public static List<String> sortie(){
        List<String> shortLogs = new ArrayList<>();
        if(myLogs.size()>=200) {
            for(int i = 0; i<200; i++)
                shortLogs.add(myLogs.get(i));
            return shortLogs;
        }
        else
            return myLogs ;
        }
}
