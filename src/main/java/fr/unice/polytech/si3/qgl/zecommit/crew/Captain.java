package fr.unice.polytech.si3.qgl.zecommit.crew;
import fr.unice.polytech.si3.qgl.zecommit.Game;
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

    public Captain(Game game, CaptainMate CM){
        this.ship=game.getShip();
        this.regatta=(Regatta) game.getGoal();
        this.sailorList=new ArrayList<>(game.getSailors());
        this.captainMate= CM;
        this.oarList= new ArrayList<>();
        sortEntities(game.getEntityList());
    }

    /**
     * main du capitaine
     */
    public void actions() {
        captainMate.getActionList().removeAll(captainMate.getActionList());
        if(game.getShip().isInCheckpoint(((Regatta)game.getGoal()).getCheckpoints().get(0))
                &&((Regatta)game.getGoal()).getCheckpoints().size()>1){
            ((Regatta)game.getGoal()).getCheckpoints().remove(0);
        }

        if(!game.getShip().isInCheckpoint(((Regatta)game.getGoal()).getCheckpoints().get(0))) {
            for(int i=0; i<sailorList.size(); i++){
                for(int j=0;j<oarList.size();j++){
                    if(sailorList.get(i).getX()==oarList.get(j).getX()&&sailorList.get(i).getY()==oarList.get(j).getY()) {
                        captainMate.toOar(sailorList.get(i), oarList.get(j));
                    }
                }
            }
        }
    }

    /**
     * BROUILLON Clement
     * @param checkpoint
     */
    public void direction(Checkpoint checkpoint){
        String direction ="";
        double differenceX=ship.getPosition().getX()-checkpoint.getPosition().getX();
        double differenceY=ship.getPosition().getY()-checkpoint.getPosition().getY();

        if(differenceX<((Circle)checkpoint.getShape()).getRadius()){
            direction="";
        }
        else if(differenceY<((Circle)checkpoint.getShape()).getRadius()){
            direction="";
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
