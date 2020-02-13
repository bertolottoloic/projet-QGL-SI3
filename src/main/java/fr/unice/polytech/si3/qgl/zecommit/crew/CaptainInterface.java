package fr.unice.polytech.si3.qgl.zecommit.crew;

import java.util.List;

public interface CaptainInterface{
    void attributeEntitiesToSailors();

    public List<Sailor> doMoveSailors();

    public List<Sailor> doActivateOars();

    public Sailor doTurn();

    public Sailor doLiftSail();

    public Sailor doLowerSail();

}