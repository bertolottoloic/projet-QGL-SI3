package fr.unice.polytech.si3.qgl.zecommit.engine;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import fr.unice.polytech.si3.qgl.zecommit.action.*;
import fr.unice.polytech.si3.qgl.zecommit.boat.Deck;
import fr.unice.polytech.si3.qgl.zecommit.boat.Position;
import fr.unice.polytech.si3.qgl.zecommit.boat.Ship;
import fr.unice.polytech.si3.qgl.zecommit.crew.Sailor;
import fr.unice.polytech.si3.qgl.zecommit.entite.Entity;
import fr.unice.polytech.si3.qgl.zecommit.entite.Oar;
import fr.unice.polytech.si3.qgl.zecommit.entite.Rudder;
import fr.unice.polytech.si3.qgl.zecommit.entite.Sail;
import fr.unice.polytech.si3.qgl.zecommit.goal.Goal;
import fr.unice.polytech.si3.qgl.zecommit.goal.Regatta;
import fr.unice.polytech.si3.qgl.zecommit.other.Checkpoint;
import fr.unice.polytech.si3.qgl.zecommit.shape.Circle;
import fr.unice.polytech.si3.qgl.zecommit.shape.Rectangle;
import fr.unice.polytech.si3.qgl.zecommit.shape.Shape;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EngineSettings {
    Goal goal;
    ArrayList<Checkpoint> checkpoints;
    Ship ship;
    Deck deck;
    ArrayList<Entity> entities;
    Shape shape;
    ArrayList<Sailor> sailors;
    ArrayList<Entity> visibleEntities;
    @JsonIgnore
    private ObjectMapper oM;
    ///////////////////////////:
    @JsonIgnore
    ArrayList<Sailor> leftSailors;
    @JsonIgnore
    ArrayList<Sailor> rightSailors;
    @JsonIgnore
    final int n=100;
    @JsonIgnore
    double rotation=0;


    EngineSettings(){
        setCheckpoints();
        setGoal();
        setEntities();
        setSailors();
        setDeck();
        setShape();
        setShip();
        setVisibleEntities();
        this.oM = new ObjectMapper();
    }

    public String thisToJson(){
        try{
            oM.configure(SerializationFeature.INDENT_OUTPUT, true);
            return oM.writeValueAsString(this);
        } catch(IOException e ) { 
            System.err.println(e);
            return "{}";
        }
    }

    public String thisToJson2(){
        try{
            oM.configure(SerializationFeature.INDENT_OUTPUT, true);
            return oM.writeValueAsString(new EngineSettingsNextRound(ship,visibleEntities));
        } catch(IOException e ) {
            System.err.println(e);
            return "{}";
        }
    }

    public void updateEngine(ArrayList<Action> actions){
        rightSailors=new ArrayList<>();
        leftSailors=new ArrayList<>();
        rotation=0;
        for (Action action: actions) {
            if(action.getType()== ActionType.MOVING){
                engineMoving((Moving) action);
            }
            if(action.getType()== ActionType.OAR){
                engineOar((ToOar) action);
            }
            if(action.getType()==ActionType.TURN){
                engineTurn((Turn) action);
            }
        }
        for(int i=0; i<n;i++) {
            calcul();
        }
    }

    public void engineTurn(Turn turn){
        for(Sailor sailor :sailors){
            if(turn.getSailorId()==sailor.getId()&&
                    ship.getRudder().getX()==sailor.getX()&&
                    ship.getRudder().getY()==sailor.getY()){
                rotation=turn.getRotation();
            }
        }
    }

    public void engineOar(ToOar toOar){
        for (Sailor sailor: sailors) {
            if(toOar.getSailorId()==sailor.getId()){
                for (Oar oar: ship.getOars()) {
                    if(sailor.getX()==oar.getX()&&sailor.getY()==oar.getY()){
                        if(deck.isLeft(oar)){
                            leftSailors.add(sailor);
                        }
                        else{
                            rightSailors.add(sailor);
                        }
                    }
                }
            }
        }
    }

    public void engineMoving(Moving toMove){
        if(!(toMove.getYDistance()==0&&toMove.getXDistance()==0)&&toMove.getXDistance()+toMove.getYDistance()<=5){
            for (Sailor sailor: sailors) {
                if(sailor.getId()==toMove.getSailorId()) {
                    sailor.setX(sailor.getX() + toMove.getXDistance());
                    sailor.setY(sailor.getY() + toMove.getYDistance());
                }
            }
        }
    }

    public void calcul(){

        double vitesse=((double) 165/n)*(leftSailors.size()+rightSailors.size())/ship.getOars().size();
        double x =vitesse*Math.cos(ship.getPosition().getOrientation())+ship.getPosition().getX();
        double y =vitesse*Math.sin(ship.getPosition().getOrientation())+ship.getPosition().getY();


        double currentOrientation=ship.getPosition().getOrientation();
        double gap= Math.PI/(ship.getOars().size());
        int balanced= rightSailors.size()-leftSailors.size();
        currentOrientation+=(balanced*gap/n);
        currentOrientation+=rotation/n;

        ship.setPosition(new Position(x,y,currentOrientation));

    }

    //--------------------SETTINGS-------------------//

    public void setShip() {
        this.ship= new Ship(100,new Position(0,0,0),"ZECOMMIT",deck,entities,shape);
    }

    public void setVisibleEntities() {
        this.visibleEntities=new ArrayList<>();
    }

    public void setSailors() {
        this.sailors= new ArrayList<>();
        this.sailors.add(new Sailor(0,0,0,"jean"));
        this.sailors.add(new Sailor(1,0,1,"paul"));
        this.sailors.add(new Sailor(2,1,0,"jacques"));
        this.sailors.add(new Sailor(3,1,1,"pierre"));

    }

    public void setGoal() {
        this.goal= new Regatta(checkpoints);
    }

    public void setCheckpoints() {
        this.checkpoints= new ArrayList<>();
        this.checkpoints.add(new Checkpoint(new Position(-200,1000,0), new Circle(50)));
        this.checkpoints.add(new Checkpoint(new Position(500,1100,0), new Circle(50)));

    }

    public void setDeck() {
        this.deck=new Deck(2,4);
    }

    public void setEntities() {
        this.entities= new ArrayList<>();
        this.entities.add(new Oar(1,0));
        this.entities.add(new Oar(1,1));
        this.entities.add(new Oar(2,0));
        this.entities.add(new Rudder(2,1));
        this.entities.add(new Oar(3,0));
        this.entities.add(new Oar(3,1));

    }

    public void setShape() {
        this.shape=new Rectangle(2,4,0);
    }

    //---------------------Getter----------------------//

    public Goal getGoal(){
        return this.goal;
    }

    public int getN() {
        return n;
    }

    @JsonIgnore
    /**
     * @return the checkpoints
     */
    public List<Checkpoint> getCheckpoints() {
        return checkpoints;
    }

    /**
     * @return the ship
     */
    public Ship getShip() {
        return ship;
    }

    @JsonIgnore
    /**
     * @return the deck
     */
    public Deck getDeck() {
        return deck;
    }

    @JsonIgnore
    /**
     * @return the entities
     */
    public List<Entity> getEntities() {
        return entities;
    }

    @JsonIgnore
    /**
     * @return the shape
     */
    public Shape getShape() {
        return shape;
    }

    /**
     * @return the sailors
     */
    public List<Sailor> getSailors() {
        return sailors;
    }

    @JsonIgnore
    /**
     * @return the visibleEntities
     */
    public List<Entity> getVisibleEntities() {
        return visibleEntities;
    }

    private class EngineSettingsNextRound{
        private Ship ship;
        private List<Entity> visibleEntities;

        EngineSettingsNextRound(Ship s,List<Entity> vE){
            this.ship = s;
            this.visibleEntities = vE;
        }

        public Ship getShip() {
            return ship;
        }

        public List<Entity> getVisibleEntities() {
            return visibleEntities;
        }
    }

}
