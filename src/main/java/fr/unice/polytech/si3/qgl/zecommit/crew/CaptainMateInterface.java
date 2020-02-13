package fr.unice.polytech.si3.qgl.zecommit.crew;

import java.util.List;
import java.util.AbstractMap.SimpleEntry;

public interface CaptainMateInterface {
    public void moveSailorsToTheirEntity(List<Sailor> sailors);

    public void activateOars(List<Sailor> sailors);

    public void toTurn(SimpleEntry<Sailor,Double> sailorAndAngle);

    public void toLiftSail(List<Sailor> sailors);

    public void toLowerSail(List<Sailor> sailors);
}