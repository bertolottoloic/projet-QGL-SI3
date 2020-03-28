package fr.unice.polytech.si3.qgl.zecommit.engine.settings;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.unice.polytech.si3.qgl.zecommit.boat.Deck;
import fr.unice.polytech.si3.qgl.zecommit.boat.Position;
import fr.unice.polytech.si3.qgl.zecommit.boat.Ship;
import fr.unice.polytech.si3.qgl.zecommit.crew.Sailor;
import fr.unice.polytech.si3.qgl.zecommit.entite.*;
import fr.unice.polytech.si3.qgl.zecommit.goal.Goal;
import fr.unice.polytech.si3.qgl.zecommit.goal.Regatta;
import fr.unice.polytech.si3.qgl.zecommit.other.*;
import fr.unice.polytech.si3.qgl.zecommit.shape.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EngineSettingsWeek9Snake implements EngineSettings {
    private Goal goal;
    private ArrayList<Checkpoint> allCheckpoints;
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
    private int visibleDistance = 1000;
    private int shipCount = 1;
    private double rotation = 0;
    private int nbSailUsed = 0;
    private ArrayList<Oar> oarArrayList;
    private ArrayList<Sail> sailArrayList;
    private Rudder rudder;
    private Watch watch;
    private Wind wind;
    @JsonIgnore
    private ArrayList<Wind> winds;
    @JsonIgnore
    private Random random = new Random();


    @JsonIgnore
    ArrayList<Stream> streams;
    @JsonIgnore
    ArrayList<Reef> reefs;
    @JsonIgnore
    ArrayList<VisibleEntitie> visibles;

    @JsonIgnore
    public EngineSettingsWeek9Snake() {
        this.entities = new ArrayList<>();
        this.sailors = new ArrayList<>();
        this.oarArrayList = new ArrayList<>();
        this.sailArrayList = new ArrayList<>();
        this.winds = new ArrayList<>();
        this.streams = new ArrayList<>();
        this.reefs = new ArrayList<>();
        this.visibles = new ArrayList<>();
        this.oM = new ObjectMapper();

        this.rightSailors = new ArrayList<>();
        this.leftSailors = new ArrayList<>();
    }

    @Override
    public void resetSettings() {
        setCheckpoints(new ArrayList<>());
        setGoal(new Regatta(new ArrayList<>()));
        setEntities(new ArrayList<>());
        setSailors(new ArrayList<>());
        setDeck(new Deck(0, 0));
        setShape(new Rectangle(0, 0, 0));
        setShip(new Ship("ship", 100, new Position(0, 0, 0), "ZECOMMIT", deck, entities, shape));
        setVisibleEntities(new ArrayList<>());
        setWind(new Wind(0, 0));
    }

    @Override
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

    @Override
    public void setWind(Wind wind) {
        this.wind = wind;
    }

    @Override
    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    @Override
    public void setShip(Ship ship) {
        this.ship = ship;
    }

    @Override
    public void setWatch(Watch watch) {
        this.watch = watch;
    }

    @Override
    public void setRightSailors(ArrayList<Sailor> sailors) {
        this.rightSailors = sailors;
    }

    @Override
    public void setLeftSailors(ArrayList<Sailor> sailors) {
        this.leftSailors = sailors;
    }

    @Override
    public void setRotation(double rotation) {
        this.rotation = rotation;
    }

    @Override
    public void setOarList(ArrayList<Oar> oars) {
        this.oarArrayList = oars;
    }

    @Override
    public void setVisibleEntities(ArrayList<VisibleEntitie> visibles) {
        this.visibleEntities = visibles;
    }

    @Override
    public void setSailors(ArrayList<Sailor> sailors) {
        this.sailors = sailors;
    }

    @Override
    public void setVisibleDistance(int distance) {
        this.visibleDistance = distance;
    }

    @Override
    public void setCheckpoints(ArrayList<Checkpoint> checkpoints) {
        this.checkpoints = checkpoints;
    }

    @Override
    public void setGoal(Goal goal) {
        this.goal = goal;
    }

    @Override
    public void setEntities(ArrayList<Entity> entities) {
        this.entities = entities;
    }

    @Override
    public void setShape(Shape shape) {
        this.shape = shape;
    }


    /**
     * ################################################ SETTINGS ################################################
     */

    @Override
    public void setVisibleEntities() {
        this.visibleEntities = new ArrayList<>();



        Circle circle1 = new Circle(90.18);
        Position position1 = new Position(67.75, 298.82, 0);
        Circle circle2 = new Circle(116.49);
        Position position2 = new Position(93.3, 414.95, 0);
        Circle circle3 = new Circle(78.82);
        Position position3 = new Position(209.42, 519.46, 0);
        Circle circle4 = new Circle(84.18);
        Position position4 = new Position(332.51, 143.22, 0);
        Circle circle5 = new Circle(62.48);
        Position position5 = new Position(332.51, 203.6, 0);
        Circle circle6 = new Circle(45);
        Position position6 = new Position(332.51, 287.21, 0);
        Circle circle7 = new Circle(79.44);
        Position position7 = new Position(330.9, 554.3, 0);
        Circle circle8 = new Circle(45);
        Position position8 = new Position(443.99, 507.85, 0);
        Circle circle9 = new Circle(45.09);
        Position position9 = new Position(478.83, 431.2, 0);


        this.visibleEntities.add(new Reef(position1, circle1));
        this.visibleEntities.add(new Reef(position2, circle2));
        this.visibleEntities.add(new Reef(position3, circle3));
        this.visibleEntities.add(new Reef(position4, circle4));
        this.visibleEntities.add(new Reef(position5, circle5));
        this.visibleEntities.add(new Reef(position6, circle6));
        this.visibleEntities.add(new Reef(position7, circle7));
        this.visibleEntities.add(new Reef(position8, circle8));
        this.visibleEntities.add(new Reef(position9, circle9));


    }

    @Override
    public void setShip() {
        this.ship = new Ship("ship", 100, new Position(0, 0, 0), "ZECOMMIT", deck, entities, shape);
    }

    @Override
    public void setWind() {
        this.winds.add(new Wind(0, 150));

    }

    @Override
    public void setSailors() {
        this.sailors = new ArrayList<>();

        this.sailors.add(new Sailor(0, 0, 0, "jean"));
        this.sailors.add(new Sailor(1, 0, 1, "paul"));
        this.sailors.add(new Sailor(2, 0, 2, "jacques"));
        this.sailors.add(new Sailor(3, 1, 0, "pierre"));
        this.sailors.add(new Sailor(4, 1, 1, "Vincent"));
        this.sailors.add(new Sailor(5, 1, 2, "Joris"));
        this.sailors.add(new Sailor(6, 2, 0, "jean"));

    }

    @Override
    public void setGoal() {
        this.goal = new Regatta(new ArrayList<>(checkpoints));
    }

    @Override
    public void setCheckpoints() {

        this.checkpoints = new ArrayList<>();

        this.checkpoints.add(new Checkpoint(new Position(200, 200, 0), new Circle(50)));
        this.checkpoints.add(new Checkpoint(new Position(295.35, 410.3, 0), new Circle(50)));
        this.checkpoints.add(new Checkpoint(new Position(460.25, 217.54, 0), new Circle(50)));
        this.checkpoints.add(new Checkpoint(new Position(601.92, 433.53, 0), new Circle(50)));

        this.allCheckpoints = new ArrayList<>(checkpoints);
    }

    @Override
    public void setDeck() {
        this.deck = new Deck(3, 4);
        this.deck.setSailors(sailors);
    }

    @Override
    public void setEntities() {

        this.entities = new ArrayList<>();
        this.entities.add(new Oar(1, 0));
        this.entities.add(new Oar(2, 0));
        this.entities.add(new Oar(1, 2));
        this.entities.add(new Oar(2, 2));
        this.entities.add(new Rudder(3, 1));
        this.entities.add(new Watch(0, 1));
        this.entities.add(new Sail(1, 1, false));

    }

    @Override
    public void setShape() {
        this.shape = new Rectangle(5, 11, 0);
    }

    @Override
    public void setNbSailUsed(int nbSailUsed) {
        this.nbSailUsed = nbSailUsed;
    }

    @Override
    public void setRudder(Rudder rudder) {
        this.rudder = rudder;
    }

    @Override
    public void changeWind() {
        wind = winds.get(random.nextInt(winds.size()));
    }

    @Override
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
            if (entity.getType().equals(EntityType.watch)) {
                this.watch = (Watch) entity;
            }
        }
    }


    @Override
    public void sortVisibleEntities() {
        for (VisibleEntitie entity : visibleEntities) {
            if (entity.getType().equals(VisibleEntityType.stream)) {
                this.streams.add((Stream) entity);
            }
            if (entity.getType().equals(VisibleEntityType.ship)) {
            }
            if (entity.getType().equals(VisibleEntityType.reef)) {
                this.reefs.add((Reef) entity);
            }
        }
    }


    /**
     * ################################################ GETTERS ################################################
     */

    @Override
    public Goal getGoal() {
        return this.goal;
    }

    @Override
    @JsonIgnore
    public int getN() {
        return n;
    }

    /**
     * @return the checkpoints
     */
    @Override
    @JsonIgnore
    public List<Checkpoint> getCheckpoints() {
        return checkpoints;
    }

    /**
     * @return the ship
     */
    @Override
    public Ship getShip() {
        return ship;
    }

    /**
     * @return the deck
     */
    @Override
    @JsonIgnore
    public Deck getDeck() {
        return deck;
    }

    /**
     * @return the entities
     */
    @Override
    @JsonIgnore
    public List<Entity> getEntities() {
        return entities;
    }

    /**
     * @return the shape
     */
    @Override
    @JsonIgnore
    public Shape getShape() {
        return shape;
    }

    /**
     * @return the sailors
     */
    @Override
    public List<Sailor> getSailors() {
        return sailors;
    }

    @Override
    public int getVisibleDistance() {
        return this.visibleDistance;
    }

    @Override
    public Watch getWatch() {
        return this.watch;
    }

    /**
     * @return the visibleEntities
     */
    @Override
    public ArrayList<VisibleEntitie> getVisibleEntities() {
        return visibleEntities;
    }

    @Override
    public double getRotation() {
        return rotation;
    }

    @Override
    public Rudder getRudder() {
        return rudder;
    }

    @Override
    public Wind getWind() {
        return wind;
    }

    @Override
    public ObjectMapper getoM() {
        return oM;
    }

    @Override
    public ArrayList<Reef> getReefs() {
        return reefs;
    }

    @Override
    public Random getRandom() {
        return random;
    }

    @Override
    public ArrayList<Sailor> getLeftSailors() {
        return leftSailors;
    }

    @Override
    public ArrayList<Sailor> getRightSailors() {
        return rightSailors;
    }

    @Override
    public ArrayList<Oar> getOarArrayList() {
        return oarArrayList;
    }

    @Override
    public ArrayList<Sail> getSailArrayList() {
        return sailArrayList;
    }

    @Override
    public ArrayList<Stream> getStreams() {
        return streams;
    }

    @Override
    public ArrayList<VisibleEntitie> getVisibles() {
        return visibles;
    }

    @Override
    public ArrayList<Wind> getWinds() {
        return winds;
    }

    @Override
    public int getNbSailUsed() {
        return nbSailUsed;
    }

    @Override
    public ArrayList<Checkpoint> getAllCheckpoints() {
        return allCheckpoints;
    }

    /**
     * @return the shipCount
     */
    @Override
    public int getShipCount() {
        return shipCount;
    }

}
