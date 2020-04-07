package fr.unice.polytech.si3.qgl.zecommit.crew;

import java.util.AbstractMap.SimpleEntry;
import java.util.List;

/**
 * Interface du Captain
 */
public interface CaptainInterface {
    void attributeEntitiesToSailors();

    List<Sailor> doMoveSailors();

    List<Sailor> doActivateOars();

    SimpleEntry<Sailor, Double> doTurn();

    List<Sailor> doLiftSail();

    List<Sailor> doLowerSail();

    Sailor doUseWatch();

    boolean pursueGame();

}
