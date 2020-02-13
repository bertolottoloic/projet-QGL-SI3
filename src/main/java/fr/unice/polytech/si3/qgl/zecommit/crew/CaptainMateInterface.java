package fr.unice.polytech.si3.qgl.zecommit.crew;

import java.util.List;

public interface CaptainMateInterface{
    public void moveSailorsToTheirEntity(List<Sailor> sailors);

    public void activateOars(List<Sailor> sailors);

    public void doTurn(Sailor sailor);

    public void doLiftSail(List<Sailor> sailors);

    public void toLowerSail(List<Sailor> sailors);
}