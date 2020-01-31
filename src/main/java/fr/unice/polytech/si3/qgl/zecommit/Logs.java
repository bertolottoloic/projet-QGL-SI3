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
        return logs;//TODO verifier taille de sortie
    }
}
