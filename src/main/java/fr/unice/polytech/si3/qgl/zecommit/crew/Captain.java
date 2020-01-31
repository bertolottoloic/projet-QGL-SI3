package fr.unice.polytech.si3.qgl.zecommit.crew;
import fr.unice.polytech.si3.qgl.zecommit.Game;
import fr.unice.polytech.si3.qgl.zecommit.Logs;
import fr.unice.polytech.si3.qgl.zecommit.Road;
import fr.unice.polytech.si3.qgl.zecommit.boat.Ship;
import fr.unice.polytech.si3.qgl.zecommit.entite.Entity;
import fr.unice.polytech.si3.qgl.zecommit.entite.Oar;
import fr.unice.polytech.si3.qgl.zecommit.goal.Regatta;
import fr.unice.polytech.si3.qgl.zecommit.other.Checkpoint;
import fr.unice.polytech.si3.qgl.zecommit.shape.Circle;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Clement P
 *
 *Classe qui definit le capitaine
 * Le capitaine se charge de la decision.
 */

public class Captain {
    private Ship ship;
    private Regatta regatta;
    private List<Sailor> sailorList;
    private List<Oar> oarList;
    private CaptainMate captainMate;
    private Game game;
    private Logs logs;

    public Captain(Game game, CaptainMate CM, Logs logs){
        this.ship=game.getShip();
        this.regatta=(Regatta) game.getGoal();
        this.sailorList=new ArrayList<>(game.getSailors());
        this.captainMate= CM;
        this.oarList= new ArrayList<>();
        sortEntities(game.getEntityList());
        this.logs=logs;
    }

    /**
     * main du capitaine
     */
    public void actions() {
        captainMate.getActionList().removeAll(captainMate.getActionList());

        if(ship.isInCheckpoint(regatta.getFirstCheckpoint()) && regatta.getCheckpoints().size()>1) {
            regatta.getCheckpoints().remove(0);
            logs.add("Checkpoint done");
        }

        if(!game.getShip().isInCheckpoint(regatta.getFirstCheckpoint())) {
            Road road = new Road(ship.getPosition(),regatta.getFirstCheckpoint().getPosition());
            decisionOrientation(road);
        }
    }


    // TODO méthode à restructurer
    public void decisionOrientation(Road road){
        //avance tout droit
        //TODO s'assurer qu'autant de marins gauche-droite rament
        boolean a = road.DistanceYToGoal()>(165-regatta.getFirstCheckpoint().getCircleRadius());

        if(road.inCapIntervalle(0.2) && !a){ // TODO mettre un intervalle pour l'angle aller tout droit
            for(int i=0; i<sailorList.size(); i++){
                for(int j=0;j<oarList.size();j++){
                    if(sailorList.get(i).getX()==oarList.get(j).getX() && sailorList.get(i).getY()==oarList.get(j).getY()) {
                        captainMate.toOar(sailorList.get(i), oarList.get(j));
                    }
                }
            }
        }
        else if (road.inCapIntervalle(0.2) && a) {
            //TODO idem
            //avancer lentement tout droit : on ne fait ramer que deux marins
            int k = 0;
            for(int i=0; i<sailorList.size(); i++){
                for(int j=0;j<oarList.size();j++){
                    if(sailorList.get(i).getX()==oarList.get(j).getX() && sailorList.get(i).getY()==oarList.get(j).getY() && k<2) {
                        captainMate.toOar(sailorList.get(i), oarList.get(j));
                        k++;
                    }
                }
            }
        }
        else if(road.orientationToGoal()<0){
            //tourner jusqu'a -pi/2
            //TODO idem
            for(int i=0; i<sailorList.size(); i++){
                for(int j=0;j<oarList.size();j++){
                    if(sailorList.get(i).getX()==oarList.get(j).getX() && sailorList.get(i).getY()==oarList.get(j).getY() && oarList.get(j).isLeft()) {
                        captainMate.toOar(sailorList.get(i), oarList.get(j));
                    }
                }
            }


        }
        else if(road.orientationToGoal()>0){
            //tourner jusqu'a pi/2 = tourner à gauche
            //TODO idem
            for(int i=0; i<sailorList.size(); i++){
                for(int j=0;j<oarList.size();j++){
                    if(sailorList.get(i).getX()==oarList.get(j).getX() && sailorList.get(i).getY()==oarList.get(j).getY() && !oarList.get(j).isLeft()) {
                        captainMate.toOar(sailorList.get(i), oarList.get(j));
                    }
                }
            }
        }

    }

    /**
     * Tri les differentes entites donnees et les ajoute a la liste correspondante
     */
    public void sortEntities(List<Entity> entityList){
        for (Entity entity : entityList){
            switch (entity.getType()){
                case "oar":
                    this.oarList.add((Oar) entity);
            }
        }
    }

    /**
     * Met a jour les informations du capitaine récupérées par le parseurNext
     * @param game
     */
    public void refreshGame(Game game){
        this.oarList.removeAll(oarList);
        this.game=game;
        ship=game.getShip();
        sortEntities(game.getEntityList());
    }

    //---------------------------GETTER-----------------------------------------


    public List<Sailor> getSailorList() {
        return sailorList;
    }

    public Game getGame() {
        return game;
    }

    public Regatta getRegatta() {
        return regatta;
    }

    public Ship getShip() {
        return ship;
    }


    //-------------------------SETTER------------------------------


    public void setGame(Game game) {
        this.game = game;
    }
}
