package fr.unice.polytech.si3.qgl.zecommit.crew;

import java.util.List;
import java.util.AbstractMap.SimpleEntry;

public interface CaptainInterface{
    void attributeEntitiesToSailors();

    public List<Sailor> doMoveSailors();

    public List<Sailor> doActivateOars();

    public SimpleEntry<Sailor,Double> doTurn();

    public List<Sailor> doLiftSail();

    public List<Sailor> doLowerSail();

}