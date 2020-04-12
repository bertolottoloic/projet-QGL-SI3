package fr.unice.polytech.si3.qgl.zecommit.visualisationtools;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.SerializationFeature;
import fr.unice.polytech.si3.qgl.zecommit.action.*;
import fr.unice.polytech.si3.qgl.zecommit.boat.Position;
import fr.unice.polytech.si3.qgl.zecommit.crew.Sailor;
import fr.unice.polytech.si3.qgl.zecommit.visualisationtools.settings.EngineSettingsInterface;
import fr.unice.polytech.si3.qgl.zecommit.entite.Oar;
import fr.unice.polytech.si3.qgl.zecommit.entite.Sail;
import fr.unice.polytech.si3.qgl.zecommit.maths.Collision;
import fr.unice.polytech.si3.qgl.zecommit.other.Reef;
import fr.unice.polytech.si3.qgl.zecommit.other.Stream;
import fr.unice.polytech.si3.qgl.zecommit.other.VisibleEntitie;
import fr.unice.polytech.si3.qgl.zecommit.other.VisibleEntityType;
import fr.unice.polytech.si3.qgl.zecommit.shape.Rectangle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Partie du moteur en charge des calculs
 */
public class EngineCalcul {

    Random random = new Random();
    EngineSettingsInterface settings;

    @JsonIgnore
    EngineCalcul(EngineSettingsInterface engineSettings) {
        this.settings= engineSettings;
    }


    /*
     * ################################################ ENGINE ################################################
     */
    public String thisToJson() {
        try {
            settings.getoM().configure(SerializationFeature.INDENT_OUTPUT, true);
            return settings.getoM().writeValueAsString(new EngineSettingsInit(settings.getGoal(), settings.getShip(), settings.getSailors()));
        } catch (IOException e) {
            System.err.println(e);
            return "{}";
        }
    }

    public String thisToJson2() {
        try {
            settings.getoM().configure(SerializationFeature.INDENT_OUTPUT, true);
            return settings.getoM().writeValueAsString(new EngineSettingsNextRound(settings.getShip(), settings.getVisibleEntities(), settings.getWind()));

        } catch (IOException e) {
            System.err.println(e);
            return "{}";
        }
    }

    public void updateEngine(List<Action> actions) throws Exception {
        settings.setRightSailors(new ArrayList<>());
        settings.setLeftSailors(new ArrayList<>());
        settings.setRotation(0);
        settings.setVisibleDistance(1000);
        changeWind();
        Position lastPosition = settings.getShip().getPosition();

        giveVisibleEntities();

        for (Action action : actions) {
            if (action.getType() == ActionType.MOVING) {
                engineMoving((Moving) action);
            }
            if (action.getType() == ActionType.OAR) {
                engineOar((ToOar) action);
            }
            if (action.getType() == ActionType.TURN) {
                engineTurn((Turn) action);
            }
            if (action.getType() == ActionType.LIFT_SAIL) {
                engineLiftSail((LiftSail) action);
            }
            if (action.getType() == ActionType.LOWER_SAIL) {
                engineLowerSail((LowerSail) action);
            }
            if (action.getType() == ActionType.USE_WATCH) {
                engineUseWatch((UseWatch) action);
            }
        }
        try {
            for (int i = 0; i < settings.getN(); i++) {
                calcul();
            }

        } catch (Exception e) {
            settings.getShip().setPosition(lastPosition);
            throw new Exception("Collision Détectée ! Déplacement annulé !");
        }

    }


    public void engineTurn(Turn turn) {
        for (Sailor sailor : settings.getSailors()) {
            if (settings.getRudder() != null &&
                    turn.getSailorId() == sailor.getId() && settings.getRudder().getX() == sailor.getX() && settings.getRudder().getY() == sailor.getY()) {
                settings.setRotation( turn.getRotation());
            }
        }
    }

    public void engineOar(ToOar toOar) {
        for (Sailor sailor : settings.getSailors()) {
            if (toOar.getSailorId() == sailor.getId()) {
                engineOarLeftRight(sailor);
            }
        }
    }

    public void engineOarLeftRight(Sailor sailor) {
        for (Oar oar : settings.getOarArrayList()) {
            if (sailor.getX() == oar.getX() && sailor.getY() == oar.getY()) {
                if (settings.getDeck().isLeft(oar)) {
                    settings.getLeftSailors().add(sailor);
                } else {
                    settings.getRightSailors().add(sailor);
                }
            }
        }
    }

    public void engineMoving(Moving toMove) {
        if (!(toMove.getYDistance() == 0 && toMove.getXDistance() == 0) && toMove.getXDistance() + toMove.getYDistance() <= 5) {
            for (Sailor sailor : settings.getSailors()) {
                if (sailor.getId() == toMove.getSailorId()) {
                    sailor.setX(sailor.getX() + toMove.getXDistance());
                    sailor.setY(sailor.getY() + toMove.getYDistance());
                }
            }
        }
    }

    public void engineLiftSail(LiftSail liftSail) {
        for (Sailor sailor : settings.getSailors()) {
            if (liftSail.getSailorId() == sailor.getId()) {
                engineLiftSailAction(sailor);
            }
        }
    }

    public void engineUseWatch(UseWatch useWatch){
        for (Sailor sailor : settings.getSailors()) {
            if (useWatch.getSailorId() == sailor.getId()) {
                engineUseWatchAction(sailor);
            }
        }
    }

    public void engineLiftSailAction(Sailor sailor) {
        for (Sail sail : settings.getSailArrayList()) {
            if (sail.getX() == sailor.getX() && sail.getY() == sailor.getY() && !sail.isOpenned()) {
                sail.setOpenned(true);
                settings.setNbSailUsed(settings.getNbSailUsed()+1);
            }
        }
    }

    public void engineLowerSail(LowerSail lowerSail) {
        for (Sailor sailor : settings.getSailors()) {
            if (lowerSail.getSailorId() == sailor.getId()) {
                engineLowerSailAction(sailor);
            }
        }
    }

    public void engineLowerSailAction(Sailor sailor) {
        for (Sail sail : settings.getSailArrayList()) {
            if (sail.getX() == sailor.getX() && sail.getY() == sailor.getY() && sail.isOpenned()) {
                sail.setOpenned(false);
                settings.setNbSailUsed(settings.getNbSailUsed()-1);
            }
        }
    }

    public void engineUseWatchAction(Sailor sailor) {
            if (settings.getWatch().getX() == sailor.getX() && settings.getWatch().getY() == sailor.getY()) {
                settings.setVisibleDistance(5000);
            }
    }


    public double calculWind() {
        double value = 0;
        if (!settings.getSailArrayList().isEmpty()) {
            value = ((double) settings.getNbSailUsed() / settings.getSailArrayList().size()) * settings.getWind().getStrength() *
                    Math.cos(Math.abs(settings.getWind().getOrientation()) - Math.abs(settings.getShip().getPosition().getOrientation()));
        }
        return value / settings.getN();
    }


    public void calcul() throws Exception {
        double vitesse = ((double) 165 / settings.getN()) * (settings.getLeftSailors().size() + settings.getRightSailors().size()) / settings.getOarArrayList().size();
        vitesse += calculWind();

        double x = vitesse * Math.cos(settings.getShip().getPosition().getOrientation()) + settings.getShip().getPosition().getX();
        double y = vitesse * Math.sin(settings.getShip().getPosition().getOrientation()) + settings.getShip().getPosition().getY();

        Stream stream = getCurrentOn();
        if (stream != null) {

            x += (stream.getStrength() / settings.getN()) * Math.cos(Math.abs(settings.getShip().getPosition().getOrientation() - (stream.getPosition().getOrientation() + ((Rectangle) stream.getShape()).getOrientation())));
            y += (stream.getStrength() / settings.getN()) * Math.sin(Math.abs(settings.getShip().getPosition().getOrientation() - (stream.getPosition().getOrientation() + ((Rectangle) stream.getShape()).getOrientation())));

        }

        Position newPosition = new Position(x, y, angleCalcul());

        boolean res = checkCollision(newPosition);

        if (res)//s'il y a une collision avec l'un des récifs, le déplacement n'a pas lieu
            throw new Exception();
        else {
            settings.getShip().setPosition(newPosition);
        }
        //System.out.println(ship.getPosition());
        checkCheckpoints();
    }

    /**
     * Méthode vérifiant les collisions avec tous les récifs présents
     *
     * @param newPosition la prochaine position
     * @return boolean : true en cas de collision
     */
    public boolean checkCollision(Position newPosition) {
        boolean res = false;
        for (Reef reef : settings.getReefs()) {
            Collision collision = new Collision(reef.getShape(), reef.getPosition(), newPosition);
            if (collision.collide()) {
                res = true;
            }
        }
        return res;
    }


    public double angleCalcul() {
        double currentOrientation = settings.getShip().getPosition().getOrientation();
        double gap = Math.PI / (settings.getOarArrayList().size());
        int balanced = settings.getRightSailors().size() - settings.getLeftSailors().size();
        currentOrientation += (balanced * gap / settings.getN());
        currentOrientation += settings.getRotation() / settings.getN();
        if (currentOrientation < -Math.PI) {
            currentOrientation = 2 * Math.PI + currentOrientation;
        }
        if (currentOrientation > Math.PI) {
            currentOrientation = -2 * Math.PI + currentOrientation;
        }
        return currentOrientation;
    }


    public void checkCheckpoints() {
        if (settings.getShip().isInCheckpoint(settings.getCheckpoints().get(0)) && settings.getCheckpoints().size() > 1) {
            //System.out.println("Checkpoint valide :"+checkpoints.get(0).getPosition());
            settings.getCheckpoints().remove(0);
        }
    }

    public void changeWind() {
        settings.setWind(settings.getWinds().get(random.nextInt(settings.getWinds().size())));
    }




    public void giveVisibleEntities() {
        for (VisibleEntitie visible : settings.getVisibleEntities()) {
            Collision collision = new Collision(visible.getShape(), visible.getPosition(), settings.getShip().getPosition());
            if (collision.distanceTo() <= settings.getVisibleDistance()) {
                settings.getVisibles().add(visible);
            }

        }
    }



    public Stream getCurrentOn() {
        if(settings.getVisibleEntities().size()>0) {
            for (VisibleEntitie entity : settings.getVisibleEntities()) {
                Collision collision = new Collision(entity.getShape(), entity.getPosition(), settings.getShip().getPosition());
                if (entity.getType() == VisibleEntityType.stream && collision.collide()) {
                    return (Stream) entity;
                }
            }
        }
        return null;
    }


}

