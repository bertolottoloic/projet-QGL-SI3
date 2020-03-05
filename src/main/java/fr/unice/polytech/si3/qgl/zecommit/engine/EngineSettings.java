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
import fr.unice.polytech.si3.qgl.zecommit.maths.Collision;
import fr.unice.polytech.si3.qgl.zecommit.other.*;
import fr.unice.polytech.si3.qgl.zecommit.shape.Circle;
import fr.unice.polytech.si3.qgl.zecommit.shape.Point;
import fr.unice.polytech.si3.qgl.zecommit.shape.Polygone;
import fr.unice.polytech.si3.qgl.zecommit.shape.Rectangle;
import fr.unice.polytech.si3.qgl.zecommit.shape.Shape;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class EngineSettings {

    private Goal goal;
    private ArrayList<Checkpoint> checkpoints;
    private Ship ship;
    private Deck deck;
    private ArrayList<Entity> entities;
    private Shape shape;
    private ArrayList<Sailor> sailors;
    private ArrayList<VisibleEntitie> visibleEntities;
    private ObjectMapper oM;
    ///////////////////////////:
    private ArrayList<Sailor> leftSailors;
    private ArrayList<Sailor> rightSailors;
    @JsonIgnore
    static final int n = 100;
    private int shipCount = 1;
    private double rotation = 0;
    private int nbSailUsed = 0;
    private ArrayList<Oar> oarArrayList;
    private ArrayList<Sail> sailArrayList;
    private Rudder rudder;
    private Wind wind;
    @JsonIgnore
    private ArrayList<Wind> winds;
    @JsonIgnore
    private Random random = new Random();


    @JsonIgnore
    ArrayList<Stream> streams;
    @JsonIgnore
    ArrayList<VisibleEntitie> visibles;

    @JsonIgnore
    EngineSettings() {
        this.entities=new ArrayList<>();
        this.sailors=new ArrayList<>();
        this.oarArrayList = new ArrayList<>();
        this.sailArrayList = new ArrayList<>();
        this.winds = new ArrayList<>();
        this.streams = new ArrayList<>();
        this.visibles = new ArrayList<>();
        this.oM = new ObjectMapper();

        this.rightSailors=new ArrayList<>();
        this.leftSailors=new ArrayList<>();
    }

    public void initiateSettings() {
        setCheckpoints();
        setGoal();
        setEntities();
        setSailors();
        setDeck();
        setShape();
        setShip();
        setVisibleEntities();
        sortVisibleEntities();
        sortEntities();
        setWind();
        changeWind();
    }


    public void createList() {
        this.checkpoints = new ArrayList<>();
        this.entities = new ArrayList<>();
        this.sailors = new ArrayList<>();
        this.visibleEntities = new ArrayList<>();
    }
    public void addCheckpoint(Checkpoint checkpoint) {
        this.checkpoints.add(checkpoint);
    }

    public void addWind(Wind wind){
        this.wind=wind;
    }

    public void addEntities(Entity entity) {
        this.entities.add(entity);
    }

    public void addSailors(Sailor sailor) {
        this.sailors.add(sailor);
    }

   public void addDeck(Deck deck){
        this.deck=deck;
   }

   public void addShip(Ship ship){
        this.ship=ship;
   }

   public void addRightSailors(ArrayList<Sailor> sailors){
        this.rightSailors=sailors;
   }
    public void addLeftSailors(ArrayList<Sailor> sailors){
        this.leftSailors=sailors;
    }

    public void addRotation(double rotation){
        this.rotation=rotation;
    }

    public void addOarList(ArrayList<Oar> oars){
        this.oarArrayList=oars;
    }

    public void addVisibleEntities(ArrayList<VisibleEntitie> visibles){
        this.visibleEntities=visibles;
    }

    /**
     * ################################################ SETTINGS ################################################
     */


    public void setVisibleEntities() {
        this.visibleEntities = new ArrayList<>();
        this.visibleEntities.add(new Stream(new Position(0,100,0),new Rectangle(100,50,0),100));

    }


    public void setWind() {
        this.winds.add(new Wind(0, 50));
        this.winds.add(new Wind(12, 38));
        this.winds.add(new Wind(7, 89));
    }

    public void setSailors() {
        this.sailors = new ArrayList<>();
        this.sailors.add(new Sailor(0, 0, 0, "jean"));
        this.sailors.add(new Sailor(1, 0, 1, "paul"));
        this.sailors.add(new Sailor(2,0,2,"jacques"));
        this.sailors.add(new Sailor(3,0,3,"pierre"));
        this.sailors.add(new Sailor(4,0,4,"Vincent"));
        this.sailors.add(new Sailor(5,1,0,"Joris"));
        this.sailors.add(new Sailor(6,1,1,"jean"));
        this.sailors.add(new Sailor(7,1,2,"paul"));
        this.sailors.add(new Sailor(8,1,3,"jacques"));
        this.sailors.add(new Sailor(9,1,4,"pierre"));
        this.sailors.add(new Sailor(10,2,0,"Vincent"));
        this.sailors.add(new Sailor(11,2,1,"Joris"));
        this.sailors.add(new Sailor(12,2,2,"jean"));
        this.sailors.add(new Sailor(13,2,3,"paul"));
        this.sailors.add(new Sailor(14,2,4,"jacques"));
        this.sailors.add(new Sailor(15,3,0,"pierre"));
        this.sailors.add(new Sailor(16,3,1,"Vincent"));
        this.sailors.add(new Sailor(17,3,2,"Joris"));
        this.sailors.add(new Sailor(18,3,3,"Joris"));
        this.sailors.add(new Sailor(19,3,4,"Joris"));

    }

    public void setGoal() {
        this.goal = new Regatta(checkpoints);
    }

    public void setCheckpoints() {

        this.checkpoints= new ArrayList<>();
        this.checkpoints.add(new Checkpoint(new Position(1600,350,0), new Circle(50)));
        this.checkpoints.add(new Checkpoint(new Position(345,1550,0), new Circle(50)));
        //this.checkpoints.add(new Checkpoint(new Position(-500,700,0), new Polygone(0, new Point[]{new Point(0, 2),new Point(2, 1),new Point(1, -2),new Point(-1, -2),new Point(-2, 1)})));
    }

    public void setDeck() {
        this.deck = new Deck(5, 11);
        this.deck.setSailors(sailors);
    }

    public void setEntities() {

        this.entities = new ArrayList<>();
        this.entities.add(new Oar(3,0));
        this.entities.add(new Oar(4,0));
        this.entities.add(new Oar(5,0));
        this.entities.add(new Oar(6,0));
        this.entities.add(new Oar(7,0));
        this.entities.add(new Oar(8,0));
        this.entities.add(new Oar(9,0));
        this.entities.add(new Oar(1,4));
        this.entities.add(new Oar(2,4));
        this.entities.add(new Oar(3,4));
        this.entities.add(new Oar(4,4));
        this.entities.add(new Oar(5,4));
        this.entities.add(new Oar(6,4));
        this.entities.add(new Oar(7,4));
        this.entities.add(new Oar(8,4));
        this.entities.add(new Oar(9,4));
        this.entities.add(new Rudder(10,4));

        this.entities.add(new Sail(5,2,false));


    }

    public void setShape() {
        this.shape = new Rectangle(5, 11, 0);
    }


    /**
     * ################################################ ENGINE ################################################
     */
    public String thisToJson() {
        try {
            oM.configure(SerializationFeature.INDENT_OUTPUT, true);
            return oM.writeValueAsString(new EngineSettingsInit(goal,ship,sailors));
        } catch (IOException e) {
            System.err.println(e);
            return "{}";
        }
    }

    public String thisToJson2() {
        try {
            oM.configure(SerializationFeature.INDENT_OUTPUT, true);
            return oM.writeValueAsString(new EngineSettingsNextRound(ship, visibleEntities, wind));

        } catch (IOException e) {
            System.err.println(e);
            return "{}";
        }
    }

    public void updateEngine(List<Action> actions) {
        rightSailors = new ArrayList<>();
        leftSailors = new ArrayList<>();
        rotation = 0;
        changeWind();

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
        }
        for (int i = 0; i < n; i++) {
            calcul();
        }
    }


    public void engineTurn(Turn turn) {
        for (Sailor sailor : sailors) {
            if (rudder != null &&
                    turn.getSailorId() == sailor.getId() && rudder.getX() == sailor.getX() && rudder.getY() == sailor.getY()) {
                rotation = turn.getRotation();
            }
        }
    }

    public void engineOar(ToOar toOar) {
        for (Sailor sailor : sailors) {
            if (toOar.getSailorId() == sailor.getId()) {
                engineOarLeftRight(sailor);
            }
        }
    }

    public void engineOarLeftRight(Sailor sailor) {
        for (Oar oar : oarArrayList) {
            if (sailor.getX() == oar.getX() && sailor.getY() == oar.getY()) {
                if (deck.isLeft(oar)) {
                    leftSailors.add(sailor);
                } else {
                    rightSailors.add(sailor);
                }
            }
        }
    }

    public void engineMoving(Moving toMove) {
        if (!(toMove.getYDistance() == 0 && toMove.getXDistance() == 0) && toMove.getXDistance() + toMove.getYDistance() <= 5) {
            for (Sailor sailor : sailors) {
                if (sailor.getId() == toMove.getSailorId()) {
                    sailor.setX(sailor.getX() + toMove.getXDistance());
                    sailor.setY(sailor.getY() + toMove.getYDistance());
                }
            }
        }
    }

    public void engineLiftSail(LiftSail liftSail) {
        for (Sailor sailor : sailors) {
            if (liftSail.getSailorId() == sailor.getId()) {
                engineLiftSailAction(sailor);
            }
        }
    }

    public void engineLiftSailAction(Sailor sailor) {
        for (Sail sail : sailArrayList) {
            if (sail.getX() == sailor.getX() && sail.getY() == sailor.getY() && !sail.isOpenned()) {
                sail.setOpenned(true);
                nbSailUsed++;
            }
        }
    }

    public void engineLowerSail(LowerSail lowerSail) {
        for (Sailor sailor : sailors) {
            if (lowerSail.getSailorId() == sailor.getId()) {
                engineLowerSailAction(sailor);
            }
        }
    }

    public void engineLowerSailAction(Sailor sailor) {
        for (Sail sail : sailArrayList) {
            if (sail.getX() == sailor.getX() && sail.getY() == sailor.getY() && sail.isOpenned()) {
                sail.setOpenned(false);
                nbSailUsed--;
            }
        }
    }

    public double calculWind() {
        double value = 0;
        if (!sailArrayList.isEmpty()) {
            value = ((double) nbSailUsed / sailArrayList.size()) * wind.getStrength() *
                    Math.cos(Math.abs(wind.getOrientation()) - Math.abs(ship.getPosition().getOrientation()));
        }
        return value / n;
    }



    public void calcul() {
        double vitesse = ((double) 165 / n) * (leftSailors.size() + rightSailors.size()) / oarArrayList.size();
        vitesse += calculWind();

        double x = vitesse * Math.cos(ship.getPosition().getOrientation()) + ship.getPosition().getX();
        double y = vitesse * Math.sin(ship.getPosition().getOrientation()) + ship.getPosition().getY();

        Stream stream =getCurrentOn();
        if(stream !=null){
            if(stream.getPosition().getOrientation()==ship.getPosition().getOrientation()){
               x+=((double)stream.getStrength()/n)*Math.cos(Math.abs(ship.getPosition().getOrientation()-stream.getPosition().getOrientation()));
               y+=((double)stream.getStrength()/n)*Math.sin(Math.abs(ship.getPosition().getOrientation()-stream.getPosition().getOrientation()));
            }
        }


        ship.setPosition(new Position(x, y, angleCalcul()));
        //System.out.println(ship.getPosition());
        checkCheckpoints();
    }


    public double angleCalcul() {
        double currentOrientation = ship.getPosition().getOrientation();
        double gap = Math.PI / (oarArrayList.size());
        int balanced = rightSailors.size() - leftSailors.size();
        currentOrientation += (balanced * gap / n);
        currentOrientation += rotation / n;
        if (currentOrientation < -Math.PI) {
            currentOrientation = 2 * Math.PI + currentOrientation;
        }
        if (currentOrientation > Math.PI) {
            currentOrientation = -2 * Math.PI + currentOrientation;
        }
        return currentOrientation;
    }

    public void setShip() {
        this.ship = new Ship("ship", 100, new Position(0, 0, 0), "ZECOMMIT", deck, entities, shape);
    }

    public void checkCheckpoints() {
        if (ship.isInCheckpoint(checkpoints.get(0)) && checkpoints.size() > 1) {
            //System.out.println("Checkpoint valide :"+checkpoints.get(0).getPosition());
            checkpoints.remove(0);
        }
    }

    public void changeWind() {
        wind = winds.get(random.nextInt(winds.size()));
    }

    public void sortEntities() {
        for (Entity entity : entities) {
            if (entity.getType().equals(EntityType.oar)) {
                this.oarArrayList.add((Oar) entity);
            }
            if (entity.getType().equals(EntityType.rudder)) {
                this.rudder = new Rudder(entity.getX(), entity.getY());
            }
            if (entity.getType().equals(EntityType.sail)) {
                this.sailArrayList.add((Sail) entity);
            }
        }
    }


    public void sortVisibleEntities() {
        for (VisibleEntitie entity : visibleEntities) {
            if (entity.getType().equals(VisibleEntityType.stream)) {
                this.streams.add((Stream) entity);
            }
            if (entity.getType().equals(VisibleEntityType.ship)) {
            }
            if (entity.getType().equals(VisibleEntityType.reef)) {
            }
        }
    }


    public void giveVisibleEntities() {
        for (VisibleEntitie visible : visibleEntities) {
            Collision collision = new Collision(visible.getShape(), visible.getPosition(), ship.getPosition());
            if (collision.distanceTo() <= 1000) {
                visibles.add(visible);
            }
        }
    }


    /**
     * ################################################ GETTERS ################################################
     */

    public Goal getGoal() {
        return this.goal;
    }

    @JsonIgnore
    public int getN() {
        return n;
    }

    /**
     * @return the checkpoints
     */
    @JsonIgnore
    public List<Checkpoint> getCheckpoints() {
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
    @JsonIgnore
    public Deck getDeck() {
        return deck;
    }

    /**
     * @return the entities
     */
    @JsonIgnore
    public List<Entity> getEntities() {
        return entities;
    }

    /**
     * @return the shape
     */
    @JsonIgnore
    public Shape getShape() {
        return shape;
    }

    /**
     * @return the sailors
     */
    public List<Sailor> getSailors() {
        return sailors;
    }

    /**
     * @return the visibleEntities
     */
    public List<VisibleEntitie> getVisibleEntities() {
        return visibleEntities;
    }

    public double getRotation() {
        return rotation;
    }

    public Rudder getRudder() {
        return rudder;
    }

    public Wind getWind() {
        return wind;
    }

    public ArrayList<Sailor> getLeftSailors() {
        return leftSailors;
    }

    public ArrayList<Sailor> getRightSailors() {
        return rightSailors;
    }

    public ArrayList<Oar> getOarArrayList() {
        return oarArrayList;
    }

    public ArrayList<Sail> getSailArrayList() {
        return sailArrayList;
    }

    public ArrayList<Stream> getStreams() {
        return streams;
    }

    public ArrayList<VisibleEntitie> getVisibles() {
        return visibles;
    }

    public ArrayList<Wind> getWinds() {
        return winds;
    }

    public int getNbSailUsed() {
        return nbSailUsed;
    }

    /**
     * @return the shipCount
     */
    public int getShipCount() {
        return shipCount;
    }

    public Stream getCurrentOn(){
        for (VisibleEntitie entity: visibleEntities) {
            Collision collision = new Collision(entity.getShape(),entity.getPosition(),ship.getPosition());
            if(entity.getType()==VisibleEntityType.stream &&collision.collide()){
                return (Stream) entity;
            }
        }
        return null;
    }

    /**
     * ############################################## EngineSettingsNextRound ##########################################
     */

    private class EngineSettingsNextRound {
        private Ship ship;
        private ArrayList<VisibleEntitie> visibleEntities;
        private Wind wind;

        EngineSettingsNextRound(Ship s, ArrayList<VisibleEntitie> vE, Wind w) {
            this.ship = s;
            this.visibleEntities = vE;
            this.wind = w;
        }

        public Ship getShip() {
            return ship;
        }

        public ArrayList<VisibleEntitie> getVisibleEntities() {
            return visibleEntities;
        }

        public Wind getWind() {
            return wind;
        }
    }

    private class EngineSettingsInit {
        private Goal goal;
        private int shipCount;
        private Ship ship;
        private List<Sailor> sailors;

        EngineSettingsInit(Goal goal,Ship s,List<Sailor> sailors) {
            this.goal=goal;
            this.shipCount=1;
            this.ship = s;
            this.sailors=sailors;
        }

        public Goal getGoal(){
            return goal;
        }

        public int getShipCount() {
            return shipCount;
        }

        public Ship getShip() {
            return ship;
        }

        public List<Sailor> getSailors(){
            return sailors;
        }

    }

}
