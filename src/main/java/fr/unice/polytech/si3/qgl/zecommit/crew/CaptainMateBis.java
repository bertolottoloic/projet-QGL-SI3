package fr.unice.polytech.si3.qgl.zecommit.crew;

import java.util.ArrayList;
import java.util.List;

import fr.unice.polytech.si3.qgl.zecommit.action.Action;
import fr.unice.polytech.si3.qgl.zecommit.action.LiftSail;
import fr.unice.polytech.si3.qgl.zecommit.action.LowerSail;
import fr.unice.polytech.si3.qgl.zecommit.action.ToOar;
import fr.unice.polytech.si3.qgl.zecommit.action.Turn;
import fr.unice.polytech.si3.qgl.zecommit.entite.*;

public class CaptainMateBis implements CaptainMateInterface {

    CaptainBis captain;
    List<Action> actions;

    public CaptainMateBis(CaptainBis captain) {
        this.captain = captain;
        this.actions = new ArrayList<>();
    }

    @Override
    public void moveSailorsToTheirEntity(List<Sailor> sailors) {
        if (!captain.doMoveSailors().isEmpty()) {
            for (Sailor sailor : sailors) {
                if (sailor.hasEntity() && !sailor.isOnEntity())
                    captain.getDeck().moveSailor(sailor, sailor.getEntity().getX() - sailor.getX(),
                            sailor.getEntity().getY() - sailor.getY());
            }
        }

    }

    @Override
    public void activateOars(List<Sailor> sailors) {
        if(!sailors.isEmpty())
            sailors.forEach(sailor -> actions.add(new ToOar(sailor.getId())));
    }

    @Override
    public void doTurn(Sailor sailor, double angle) {
        if(sailor!=null)
            actions.add(new Turn(sailor.getId(), angle));

    }

    @Override
    public void doLiftSail(List<Sailor> sailors) {
        if(!sailors.isEmpty()){
            sailors.forEach(sailor -> {
                actions.add(new LiftSail(sailor.getId()));
                Sail sail = (Sail)sailor.getEntity();
                sail.setOpenned(true);
            });
        }
    }

    @Override
    public void toLowerSail(List<Sailor> sailors) {
        if(!sailors.isEmpty()){
            sailors.forEach(sailor -> {
                actions.add(new LowerSail(sailor.getId()));
                Sail sail = (Sail)sailor.getEntity();
                sail.setOpenned(false);
            });
        }

    }

}