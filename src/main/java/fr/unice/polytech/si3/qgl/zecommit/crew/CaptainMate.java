package fr.unice.polytech.si3.qgl.zecommit.crew;

import fr.unice.polytech.si3.qgl.zecommit.Game;
import fr.unice.polytech.si3.qgl.zecommit.action.*;
import fr.unice.polytech.si3.qgl.zecommit.entite.Sail;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.List;

public class CaptainMate {

    Captain captain;
    List<Action> actions;
    private boolean initGame;

    public CaptainMate(Game game) {
        this.captain = new Captain(game);
        this.actions = new ArrayList<>();
        this.initGame = true;
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
        if(sailorAndAngle!=null && sailorAndAngle.getValue()!=0.0){
            double angle = sailorAndAngle.getValue();
            if(angle>0) 
                angle = Math.min(Math.PI/4, angle);
            else
                angle = Math.max(-Math.PI/4, angle);
            actions.add(new Turn(sailorAndAngle.getKey().getId(), angle));
        }
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

    public List<Action> actions(Game game){
        refreshData(game);
        if(this.initGame){
            captain.attributeEntitiesToSailors();
            this.initGame = false;
        }
        if(captain.pursueGame()){
            moveSailorsToTheirEntity(captain.doMoveSailors());
            activateOars(captain.doActivateOars());
            toTurn(captain.doTurn());
            toLiftSail(captain.doLiftSail());
            toLowerSail(captain.doLowerSail());
        }
        return this.actions;

    }

    public void refreshData(Game game){
        captain.refreshData(game);
        this.actions.removeAll(this.actions);
    }

}
