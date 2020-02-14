package fr.unice.polytech.si3.qgl.zecommit.crew;

import java.util.ArrayList;
import java.util.List;
import java.util.AbstractMap.SimpleEntry;

import fr.unice.polytech.si3.qgl.zecommit.action.Action;
import fr.unice.polytech.si3.qgl.zecommit.action.LiftSail;
import fr.unice.polytech.si3.qgl.zecommit.action.LowerSail;
import fr.unice.polytech.si3.qgl.zecommit.action.ToOar;
import fr.unice.polytech.si3.qgl.zecommit.action.Turn;
import fr.unice.polytech.si3.qgl.zecommit.entite.*;

public class CaptainMateBis {

    CaptainBis captain;
    List<Action> actions;

    public CaptainMateBis(CaptainBis captain) {
        this.captain = captain;
        this.actions = new ArrayList<>();
    }


    public void moveSailorsToTheirEntity(List<Sailor> sailors) {
        if (!captain.doMoveSailors().isEmpty()) {
            for (Sailor sailor : sailors) {
                if (sailor.hasEntity() && !sailor.isOnEntity()) {
                    actions.add(captain.getDeck().moveSailor(sailor, sailor.getEntity().getX() - sailor.getX(),
                            sailor.getEntity().getY() - sailor.getY()));
                }
            }
        }
    }

    public void activateOars(List<Sailor> sailors) {
        if(!sailors.isEmpty())
            sailors.forEach(sailor -> actions.add(new ToOar(sailor.getId())));
    }

    public void toTurn(SimpleEntry<Sailor,Double> sailorAndAngle) {
        if(sailorAndAngle.getValue()!=0)
            actions.add(new Turn(sailorAndAngle.getKey().getId(), sailorAndAngle.getValue()));
    }

    public void toLiftSail(List<Sailor> sailors) {
        if(!sailors.isEmpty()){
            sailors.forEach(sailor -> {
                actions.add(new LiftSail(sailor.getId()));
                Sail sail = (Sail)sailor.getEntity();
                sail.setOpenned(true);
            });
        }
    }

    public void toLowerSail(List<Sailor> sailors) {
        if(!sailors.isEmpty()){
            sailors.forEach(sailor -> {
                actions.add(new LowerSail(sailor.getId()));
                Sail sail = (Sail)sailor.getEntity();
                sail.setOpenned(false);
            });
        }
    }

    public List<Action> actions(){
        captain.attributeEntitiesToSailors();
        moveSailorsToTheirEntity(captain.doMoveSailors());
        activateOars(captain.doActivateOars());
        toTurn(captain.doTurn());
        toLiftSail(captain.doLiftSail());
        toLowerSail(captain.doLowerSail());
        return this.actions;
    }

}