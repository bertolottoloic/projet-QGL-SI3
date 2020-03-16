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
import fr.unice.polytech.si3.qgl.zecommit.shape.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class EngineSettings {

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
    ArrayList<Reef> reefs;
    @JsonIgnore
    ArrayList<VisibleEntitie> visibles;

    @JsonIgnore
    EngineSettings() {
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

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public void setEntities(Entity entity) {
        this.entities.add(entity);
    }

    public void setSailors(Sailor sailor) {
        this.sailors.add(sailor);
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public void setRightSailors(ArrayList<Sailor> sailors) {
        this.rightSailors = sailors;
    }

    public void setLeftSailors(ArrayList<Sailor> sailors) {
        this.leftSailors = sailors;
    }

    public void setRotation(double rotation) {
        this.rotation = rotation;
    }

    public void setOarList(ArrayList<Oar> oars) {
        this.oarArrayList = oars;
    }

    public void setVisibleEntities(ArrayList<VisibleEntitie> visibles) {
        this.visibleEntities = visibles;
    }

    public void setSailors(ArrayList<Sailor> sailors) {
        this.sailors = sailors;
    }



    /**
     * ################################################ SETTINGS ################################################
     */


    public void setVisibleEntities() {
        this.visibleEntities = new ArrayList<>();
        //this.visibleEntities.add(new Stream(new Position(1000,1000,Math.PI/4),new Rectangle(100,1400,0),150));
        //this.visibleEntities.add(new Reef(new Position(100, 100, 0), new Rectangle(40, 200, -Math.PI/4)));

        //this.visibleEntities.add(new Reef(new Position(400, -20, 0), new Circle(50)));
        //this.visibleEntities.add(new Reef(new Position(1100, 700, 0), new Circle(50)));
        //this.visibleEntities.add(new Reef(new Position(1200, 200, 0), new Circle(50)));
        // this.visibleEntities.add(new Reef(new Position(1400, 100, 0), new Polygone(0, new Point[]{new Point(0, 300),new Point(300, 100),new Point(100, -300),new Point(-100, -300),new Point(-300, 100)})));
        //this.visibleEntities.add(new Reef(new Position(1400, 100, 0), new Circle(324)));


        //this.visibleEntities.add(new Stream(new Position(1000, 1000, -Math.PI / 4), new Rectangle(100, 1400, 0), 50));
        //this.visibleEntities.add(new Reef(new Position(1500, 500, 0.78539816339), new Rectangle(1250.0, 1300, 0)));

        //this.visibleEntities.add(new Reef(new Position(500, 1500, 0.78539816339), new Rectangle(1250.0, 1300.0, 0.0)));




        //TOUR DE RE
        Polygone polygon = new Polygone(0, new Point[]{new Point(-135.99999999999997, -596.0), new Point(163.99999999999997, -336.0), new Point(324.0, 64.0), new Point(284.00000000000006, 704.0), new Point(-636.0, 164.00000000000006)});
        Position position = new Position(1876.0, 2096.0, 0.0);
        Polygone polygon0 = new Polygone(0, new Point[]{new Point(285.00000000000006, -435.00000000000006), new Point(185.0, -35.0), new Point(-255.00000000000006, 464.9999999999999), new Point(-215.0, 5.00000000000002)});
        Position position0 = new Position(1015.0, 835.0, 0.0);
        Polygone polygon1 = new Polygone(0, new Point[]{new Point(-454.99999999999994, -345.0), new Point(145.0, -105.0), new Point(225.0, 95.0), new Point(85.00000000000003, 355.0)});
        Position position1 = new Position(1655.0, 1145.0, 0.0);
        Polygone polygon2 = new Polygone(0, new Point[]{new Point(27.9999999999996, -748.0000000000001), new Point(568.0, -48.0), new Point(67.99999999999994, 711.9999999999999), new Point(-251.99999999999991, 332.00000000000006), new Point(-411.99999999999994, -248.00000000000006)});
        Position position2 = new Position(1172.0, 1548.0, 0.0);
        Polygone polygon3 = new Polygone(0, new Point[]{new Point(63.99999999999998, -224.0), new Point(564.0, 576.0), new Point(-96.0, 276.00000000000006), new Point(-295.99999999999994, -224.00000000000006), new Point(-236.00000000000014, -403.9999999999999)});
        Position position3 = new Position(2196.0, 4684.0, 0.0);
        Polygone polygon4 = new Polygone(0, new Point[]{new Point(-283.99999999999994, -440.00000000000006), new Point(336.0, 140.0), new Point(376.00000000000006, 460.0), new Point(36.0, 320.0), new Point(-463.9999999999999, -480.00000000000017)});
        Position position4 = new Position(2724.0, 4940.0, 0.0);
        Polygone polygon5 = new Polygone(0, new Point[]{new Point(-583.3333333333335, 96.66666666666704), new Point(-223.33333333333348, -643.333333333333), new Point(16.66666666666649, -283.33333333333303), new Point(376.6666666666665, -43.33333333333303), new Point(376.6666666666665, 196.66666666666697), new Point(36.666666666666565, 676.666666666667)});
        Position position5 = new Position(3023.3333333333335, 4403.333333333333, 0.0);
        Polygone polygon6 = new Polygone(0, new Point[]{new Point(210.0, -265.0), new Point(330.00000000000006, 335.0), new Point(170.00000000000006, 734.9999999999999), new Point(-710.0000000000001, -805.0)});
        Position position6 = new Position(1950.0, 3065.0, 0.0);
        Polygone polygon7 = new Polygone(0, new Point[]{new Point(-40.00000000000003, -375.0), new Point(140.0, -15.0), new Point(100.00000000000001, 285.0), new Point(-200.00000000000003, 104.99999999999999)});
        Position position7 = new Position(2160.0, 4175.0, 0.0);
        Polygone polygon8 = new Polygone(0, new Point[]{new Point(15.000000000000028, -390.0), new Point(95.0, 10.0), new Point(34.99999999999999, 369.99999999999994), new Point(-144.99999999999997, 9.999999999999986)});
        Position position8 = new Position(2265.0, 3790.0, 0.0);
        Polygone polygon9 = new Polygone(0, new Point[]{new Point(-112.00000000000004, -207.99999999999997), new Point(168.0, -408.0), new Point(148.0, 352.0), new Point(-52.0, 272.0), new Point(-152.0, -7.999999999999985)});
        Position position9 = new Position(2592.0, 3408.0, 0.0);

        this.visibleEntities.add(new Reef(position, polygon));
        this.visibleEntities.add(new Reef(position0, polygon0));
        this.visibleEntities.add(new Reef(position1, polygon1));
        this.visibleEntities.add(new Reef(position2, polygon2));
        this.visibleEntities.add(new Reef(position3, polygon3));
        this.visibleEntities.add(new Reef(position4, polygon4));
        this.visibleEntities.add(new Reef(position5, polygon5));
        this.visibleEntities.add(new Reef(position6, polygon6));
        this.visibleEntities.add(new Reef(position7, polygon7));
        this.visibleEntities.add(new Reef(position8, polygon8));
        this.visibleEntities.add(new Reef(position9, polygon9));

        //STREAM
        Polygone polygon10 = new Polygone(0, new Point[]{new Point(700.0, -49.99999999999999), new Point(700.0, 49.99999999999999), new Point(-700.0, 50.00000000000004), new Point(-700.0, -50.00000000000004)});
        Position position10 = new Position(1000.0, 1000.0, 0.78539816339);
        this.visibleEntities.add(new Stream(position10, polygon10, 150));



    }


    public void setShip() {
        this.ship = new Ship("ship", 100, new Position(0, 0, 0), "ZECOMMIT", deck, entities, shape);
    }

    public void setWind() {
        this.winds.add(new Wind(0, 150));
        // this.winds.add(new Wind(12, 38));
        //this.winds.add(new Wind(7, 89));
    }

    public void setSailors() {
        this.sailors = new ArrayList<>();
/*
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

 */


        this.sailors.add(new Sailor(0, 0, 0, "jean"));
        this.sailors.add(new Sailor(1, 0, 1, "paul"));
        this.sailors.add(new Sailor(2, 0, 2, "jacques"));
        this.sailors.add(new Sailor(3, 1, 0, "pierre"));
        this.sailors.add(new Sailor(4, 1, 1, "Vincent"));
        this.sailors.add(new Sailor(5, 1, 2, "Joris"));
        this.sailors.add(new Sailor(6, 2, 0, "jean"));





    }

    public void setGoal() {
        this.goal = new Regatta(new ArrayList<>(checkpoints));
    }

    public void setCheckpoints() {

        this.checkpoints = new ArrayList<>();
        // M7 à ne pas supprimer
        //this.checkpoints.add(new Checkpoint(new Position(500, 500, 0), new Circle(50)));
        //this.checkpoints.add(new Checkpoint(new Position(1000, 1000, 0), new Circle(50)));
        //this.checkpoints.add(new Checkpoint(new Position(1500, 1500, 0), new Circle(60)));





        this.checkpoints.add(new Checkpoint(new Position(300.0,1800.0,0), new Circle(150)));
        this.checkpoints.add(new Checkpoint(new Position(1900.0,3800.0,0), new Circle(150)));
        this.checkpoints.add(new Checkpoint(new Position(2000.0,5240.0,0), new Circle(150)));
        this.checkpoints.add(new Checkpoint(new Position(3600.0,5160.0,0), new Circle(150)));
        this.checkpoints.add(new Checkpoint(new Position(3300.0,3500.0,0), new Circle(150)));
        this.checkpoints.add(new Checkpoint(new Position(2400.0,2000.0,0), new Circle(150)));
        this.checkpoints.add(new Checkpoint(new Position(1800.0,360.0,0), new Circle(150)));
        this.checkpoints.add(new Checkpoint(new Position(360.0,320.0,0), new Circle(150)));



        this.allCheckpoints = new ArrayList<>(checkpoints);
    }

    public void setDeck() {
        this.deck = new Deck(3, 4);
        this.deck.setSailors(sailors);
    }

    public void setEntities() {
/*
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

 */






        this.entities = new ArrayList<>();
        this.entities.add(new Oar(1, 0));
        this.entities.add(new Oar(2, 0));
        this.entities.add(new Oar(1, 2));
        this.entities.add(new Oar(2, 2));
        this.entities.add(new Rudder(3, 1));
        this.entities.add(new Watch(0, 1));
        this.entities.add(new Sail(1, 1, false));






    }

    public void setShape() {
        this.shape = new Rectangle(5, 11, 0);
    }

    public void setNbSailUsed(int nbSailUsed) {
        this.nbSailUsed = nbSailUsed;
    }

    public void setRudder(Rudder rudder) {
        this.rudder = rudder;
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
                this.reefs.add((Reef) entity);
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
    public ArrayList<VisibleEntitie> getVisibleEntities() {
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

    public ObjectMapper getoM() {
        return oM;
    }

    public ArrayList<Reef> getReefs() {
        return reefs;
    }

    public Random getRandom() {
        return random;
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

    public ArrayList<Checkpoint> getAllCheckpoints() {
        return allCheckpoints;
    }

    /**
     * @return the shipCount
     */
    public int getShipCount() {
        return shipCount;
    }

    public Stream getCurrentOn() {
        for (VisibleEntitie entity : visibleEntities) {
            Collision collision = new Collision(entity.getShape(), entity.getPosition(), ship.getPosition());
            if (entity.getType() == VisibleEntityType.stream && collision.collide()) {
                return (Stream) entity;
            }
        }
        return null;
    }




}
