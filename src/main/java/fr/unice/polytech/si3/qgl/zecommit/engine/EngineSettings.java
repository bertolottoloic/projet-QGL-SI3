package fr.unice.polytech.si3.qgl.zecommit.engine;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import fr.unice.polytech.si3.qgl.zecommit.action.*;
import fr.unice.polytech.si3.qgl.zecommit.boat.Deck;
import fr.unice.polytech.si3.qgl.zecommit.boat.Position;
import fr.unice.polytech.si3.qgl.zecommit.boat.Ship;
import fr.unice.polytech.si3.qgl.zecommit.crew.Sailor;
import fr.unice.polytech.si3.qgl.zecommit.entite.*;
import fr.unice.polytech.si3.qgl.zecommit.goal.Goal;
import fr.unice.polytech.si3.qgl.zecommit.goal.Regatta;
import fr.unice.polytech.si3.qgl.zecommit.other.Checkpoint;
import fr.unice.polytech.si3.qgl.zecommit.other.Wind;
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
    @JsonIgnore
    int nbSailUsed=0;
    @JsonIgnore
    ArrayList<Oar> oarArrayList;
    @JsonIgnore
    ArrayList<Sail> sailArrayList;
    @JsonIgnore
    Rudder rudder;
    @JsonIgnore
    Wind wind;


    EngineSettings(){
        this.oarArrayList=new ArrayList<>();
        this.sailArrayList=new ArrayList<>();
        this.oM = new ObjectMapper();
        setCheckpoints();
        setGoal();
        setEntities();
        setSailors();
        setDeck();
        setShape();
        setShip();
        setVisibleEntities();
        setWind();
        sortEntities();
    }

    //--------------------SETTINGS-------------------//

    public void setShip() {
        this.ship= new Ship(100,new Position(0,0,0),"ZECOMMIT",deck,entities,shape);
    }

    public void setVisibleEntities() {
        this.visibleEntities=new ArrayList<>();
    }

    public void setWind(){
        this.wind=new Wind(Math.PI,100);
    }

    public void setSailors() {
        this.sailors= new ArrayList<>();
        this.sailors.add(new Sailor(0,0,0,"jean"));
        this.sailors.add(new Sailor(1,0,1,"paul"));
        this.sailors.add(new Sailor(2,1,0,"jacques"));
        this.sailors.add(new Sailor(3,1,1,"pierre"));
        //this.sailors.add(new Sailor(4,0,0,"Vincent"));


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
        this.entities.add(new Oar(3,1));
        this.entities.add(new Sail(3,2,false));
    }

    public void setShape() {
        this.shape=new Rectangle(2,4,0);
    }


    ///////////////////////////////////////: ENGINE : ///////////////////////////////////////:

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
            return oM.writeValueAsString(new EngineSettingsNextRound(ship,visibleEntities,wind));
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
            if(action.getType()==ActionType.LIFT_SAIL){
                engineLiftSail((LiftSail) action);
            }
            if(action.getType()==ActionType.LOWER_SAIL){
                engineLowerSail((LowerSail) action);
            }
        }
        for(int i=0; i<n;i++) {
            calcul();
        }
    }

    public void engineTurn(Turn turn){
        for(Sailor sailor :sailors){
            if(rudder!=null){
                if(turn.getSailorId()==sailor.getId()&&
                        rudder.getX()==sailor.getX()&&
                        rudder.getY()==sailor.getY()){
                    rotation=turn.getRotation();
                }
            }
        }
    }

    public void engineOar(ToOar toOar){
        for (Sailor sailor: sailors) {
            if(toOar.getSailorId()==sailor.getId()){
                for (Oar oar: oarArrayList) {
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

    public void engineLiftSail(LiftSail liftSail){
        for (Sailor sailor: sailors) {
            if(liftSail.getSailorId()==sailor.getId()){
                for (Sail sail: sailArrayList) {
                    if(sail.getX()==sailor.getX()&&sail.getY()==sailor.getY()) {
                        if (!sail.isOpenned()){
                            sail.setOpenned(true);
                            nbSailUsed++;
                        }
                    }
                }
            }
        }
    }
    public void engineLowerSail(LowerSail lowerSail){
        for (Sailor sailor: sailors) {
            if(lowerSail.getSailorId()==sailor.getId()){
                for (Sail sail: sailArrayList) {
                    if(sail.getX()==sailor.getX()&&sail.getY()==sailor.getY()){
                        if (sail.isOpenned()) {
                            sail.setOpenned(false);
                            nbSailUsed--;
                        }
                    }
                }
            }
        }
    }

    public double calculWind(){
        double value=0;
        if(sailArrayList.size()>0) {
            value = ((double) nbSailUsed / sailArrayList.size()) * wind.getStrength() *
                    Math.cos(Math.abs(wind.getOrientation()) - Math.abs(ship.getPosition().getOrientation()));
        }
        return (double)value/n;
    }

    public void calcul(){

        double vitesse=((double) 165/n)*(leftSailors.size()+rightSailors.size())/oarArrayList.size();
        vitesse+=calculWind();

        double x =vitesse*Math.cos(ship.getPosition().getOrientation())+ship.getPosition().getX();
        double y =vitesse*Math.sin(ship.getPosition().getOrientation())+ship.getPosition().getY();

        double currentOrientation=ship.getPosition().getOrientation();
        double gap= Math.PI/(oarArrayList.size());
        int balanced= rightSailors.size()-leftSailors.size();
        currentOrientation+=(balanced*gap/n);
        currentOrientation+=rotation/n;

        ship.setPosition(new Position(x,y,currentOrientation));
        checkCheckpoints();
    }

    public void checkCheckpoints(){
        if(ship.isInCheckpoint(checkpoints.get(0))&&checkpoints.size()>1){
            System.out.println("Checkpoint valides :"+checkpoints.get(0).getPosition());
            checkpoints.remove(0);
        }
    }

    public void sortEntities(){
        for (Entity entity : entities){
            if (entity.getType().equals(EntityType.OAR)) {
                this.oarArrayList.add((Oar) entity);
            }
            if (entity.getType().equals(EntityType.RUDDER)) {
                this.rudder=new Rudder(entity.getX(),entity.getY());
            }
            if (entity.getType().equals(EntityType.SAIL)) {
                this.sailArrayList.add((Sail) entity);
            }
        }
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
        private Wind wind;

        EngineSettingsNextRound(Ship s,List<Entity> vE,Wind w){
            this.ship = s;
            this.visibleEntities = vE;
            this.wind=w;
        }

        public Ship getShip() {
            return ship;
        }

        public List<Entity> getVisibleEntities() {
            return visibleEntities;
        }

        public Wind getWind(){
            return wind;
        }
    }

}
