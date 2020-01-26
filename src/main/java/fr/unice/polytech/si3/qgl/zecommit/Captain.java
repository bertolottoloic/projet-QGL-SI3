package fr.unice.polytech.si3.qgl.zecommit;
import fr.unice.polytech.si3.qgl.zecommit.entite.Entity;
import fr.unice.polytech.si3.qgl.zecommit.entite.Oar;
import fr.unice.polytech.si3.qgl.zecommit.goal.Regatta;

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

    public void actions() {
        captainMate.getActionList().removeAll(captainMate.getActionList());
        if(!game.getShip().estDedans(((Regatta)game.getGoal()).getCheckpoints().get(0))) {//TODO plusieurs checkpoints
            for(int i=0; i<sailorList.size(); i++){
                for(int j=0;j<oarList.size();j++){
                    if(sailorList.get(i).getX()==oarList.get(j).getX()&&sailorList.get(i).getY()==oarList.get(j).getY()) {
                        captainMate.toOar(sailorList.get(i), oarList.get(j));
                    }
                }
            }
        }
    }

    private void sortEntities(List<Entity> entityList){
        for (Entity entity : entityList){
            switch (entity.getType()){
                case "oar":
                    this.oarList.add((Oar) entity);
            }
        }
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
