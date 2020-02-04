package fr.unice.polytech.si3.qgl.zecommit;


import java.util.ArrayList;
import java.util.List;

/**
 * Classe générant les logs
 * @author Nathan
 */
public class Logs {

    public List<String> logs;

    public Logs() {
        this.logs = new ArrayList<>();
    }


    public void add(String s){
        logs.add(s);
    }

    public List<String> sortie(){
        List<String> shortLogs = new ArrayList<>();
        if(logs.size()>200) {
            for(int i =0; i<200; i++)
                shortLogs.add(logs.get(i));
            return shortLogs;
        }
        else
            return logs ;
        }
}
