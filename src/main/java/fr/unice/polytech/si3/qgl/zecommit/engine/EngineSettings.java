package fr.unice.polytech.si3.qgl.zecommit.engine;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.unice.polytech.si3.qgl.zecommit.action.Action;
import fr.unice.polytech.si3.qgl.zecommit.action.ActionType;
import fr.unice.polytech.si3.qgl.zecommit.action.Moving;
import fr.unice.polytech.si3.qgl.zecommit.action.ToOar;
import fr.unice.polytech.si3.qgl.zecommit.boat.Deck;
import fr.unice.polytech.si3.qgl.zecommit.boat.Position;
import fr.unice.polytech.si3.qgl.zecommit.boat.Ship;
import fr.unice.polytech.si3.qgl.zecommit.crew.Sailor;
import fr.unice.polytech.si3.qgl.zecommit.entite.Entity;
import fr.unice.polytech.si3.qgl.zecommit.entite.Oar;
import fr.unice.polytech.si3.qgl.zecommit.goal.Goal;
import fr.unice.polytech.si3.qgl.zecommit.goal.Regatta;
import fr.unice.polytech.si3.qgl.zecommit.other.Checkpoint;
import fr.unice.polytech.si3.qgl.zecommit.shape.Circle;
import fr.unice.polytech.si3.qgl.zecommit.shape.Rectangle;
import fr.unice.polytech.si3.qgl.zecommit.shape.Shape;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.SerializationFeature;

public class EngineSettings {
    Goal goal;
    ArrayList<Checkpoint> checkpoints;
    Ship ship;
    Deck deck;
    ArrayList<Entity> entities;
    Shape shape;
    ArrayList<Sailor> sailors;
    ArrayList<Entity> visibleEntities;
    private ObjectMapper oM;
    ArrayList<Sailor> leftSailors;
    ArrayList<Sailor> rightSailors;
    int n=100;


    EngineSettings(){
        //Liste de checkpoints
        setCheckpoints();
        //GameMode
        setGoal();
        //Entitees
        setEntities();
        //Marins
        setSailors();
        //Deck
        setDeck();
        //Forme
        setShape();
        //Bateau
        setShip();
        //Entitees visibles
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
        for (Action action: actions) {
            if(action.getType()== ActionType.MOVING){
                engineMoving((Moving) action);
            }
            if(action.getType()== ActionType.OAR){
                engineOar((ToOar) action);
            }
        }
        for(int i=0; i<n;i++) {
            calcul();
            //System.out.println("    STEP :"+i+"\n   "+ship.getPosition());
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

        double vitesse=(double)((double) 165/n)*(leftSailors.size()+rightSailors.size())/ship.getOars().size();
        double x =vitesse*Math.cos(ship.getPosition().getOrientation())+ship.getPosition().getX();
        double y =vitesse*Math.sin(ship.getPosition().getOrientation())+ship.getPosition().getY();


        double currentOrientation=ship.getPosition().getOrientation();
        double gap= Math.PI/(n*(ship.getOars().size()));
        int balanced= rightSailors.size()-leftSailors.size();
        currentOrientation+=(balanced*gap);
        currentOrientation%=Math.PI;
        ship.setPosition(new Position(x,y,currentOrientation));

    }

    //--------------------SETTINGS-------------------//

    public void setShip() {
        this.ship= new Ship("ship", 100,new Position(0,0,0),"ZECOMMIT",deck,entities,shape);
    }

    public void setVisibleEntities() {
        this.visibleEntities=new ArrayList<>();
    }

    public void setSailors() {
        this.sailors= new ArrayList<>();
        this.sailors.add(new Sailor(0,0,0,"jean"));
        this.sailors.add(new Sailor(1,0,0,"paul"));
        this.sailors.add(new Sailor(2,0,0,"jacques"));
    }

    public void setGoal() {
        this.goal= new Regatta(checkpoints);
    }

    public void setCheckpoints() {
        this.checkpoints= new ArrayList<>();
        this.checkpoints.add(new Checkpoint(new Position(100,100,0), new Circle(50)));
    }

    public void setDeck() {
        this.deck=new Deck(4,10);
    }

    public void setEntities() {
        this.entities= new ArrayList<>();
        this.entities.add(new Oar(2,0));
        this.entities.add(new Oar(2,3));
        this.entities.add(new Oar(7,0));
        this.entities.add(new Oar(7,3));
    }

    public void setShape() {
        this.shape=new Rectangle(4,10,0);
    }

    //---------------------Getter----------------------//

    public Goal getGoal(){
        return this.goal;
    }

    public int getN() {
        return n;
    }

    /**
     * @return the checkpoints
     */
    public ArrayList<Checkpoint> getCheckpoints() {
        return checkpoints;
    }

    /**
     * @return the ship
     */
    public Ship getShip() {
        return ship;
    }

    /**
     * @return the deck
     */
    public Deck getDeck() {
        return deck;
    }

    /**
     * @return the entities
     */
    public ArrayList<Entity> getEntities() {
        return entities;
    }

    /**
     * @return the shape
     */
    public Shape getShape() {
        return shape;
    }

    /**
     * @return the sailors
     */
    public ArrayList<Sailor> getSailors() {
        return sailors;
    }

    /**
     * @return the visibleEntities
     */
    public ArrayList<Entity> getVisibleEntities() {
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
