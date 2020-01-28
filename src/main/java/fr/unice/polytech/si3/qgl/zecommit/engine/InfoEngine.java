package fr.unice.polytech.si3.qgl.zecommit.engine;

import fr.unice.polytech.si3.qgl.zecommit.crew.Sailor;
import fr.unice.polytech.si3.qgl.zecommit.entite.Oar;
import fr.unice.polytech.si3.qgl.zecommit.other.Checkpoint;

import java.util.ArrayList;
import java.util.List;

/**
 * @author clement
 */
public class InfoEngine {
    List<Oar> oarList;
    List<Sailor> sailorList;
    List<Checkpoint> checkpointList;


    public InfoEngine(List<Oar> oarList, List<Sailor> sailorList, List<Checkpoint> checkpointList){
        this.sailorList= new ArrayList<>(sailorList);
        this.oarList= new ArrayList<>(oarList);
        this.checkpointList= new ArrayList<>(checkpointList);
    }

    public List<Sailor> getSailorList() {
        return sailorList;
    }

    public List<Checkpoint> getCheckpointList() {
        return checkpointList;
    }

    public List<Oar> getOarList() {
        return oarList;
    }
}
