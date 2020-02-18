package fr.unice.polytech.si3.qgl.zecommit.boat;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import fr.unice.polytech.si3.qgl.zecommit.crew.Sailor;
import fr.unice.polytech.si3.qgl.zecommit.entite.*;
import fr.unice.polytech.si3.qgl.zecommit.Logs;
import fr.unice.polytech.si3.qgl.zecommit.action.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Loic Bertolotto
 */
public class Deck{
    @JsonProperty("width")private int width;
    @JsonProperty("length")private int length;

    @JsonIgnore
    private List<Oar> oars;
    @JsonIgnore
    private Rudder rudder;
    @JsonIgnore
    private List<Sail> sails;
    @JsonIgnore
    private List<Sailor> sailors;


    @JsonCreator
    public Deck(@JsonProperty("width")int width,@JsonProperty("length") int length){
        this.width = width;
        this.length = length;
        this.oars= new ArrayList<>();
        this.sails= new ArrayList<>();
        this.sailors= new ArrayList<>();
    }

    public void initDeck(List<Entity> entities){
        for (Entity entity : entities){
            if (entity.getType().equals(EntityType.OAR)) {
                this.oars.add((Oar) entity);
            }
            if (entity.getType().equals(EntityType.RUDDER)) {
                this.rudder=new Rudder(entity.getX(),entity.getY());
            }
            if (entity.getType().equals(EntityType.SAIL)) {
                this.sails.add((Sail) entity);
            }
        }
    }

    public boolean sailorsAreOnTheirEntity() {
        for (Sailor sailor : sailors) {
            if (!sailor.isOnEntity() && sailor.hasEntity())
                return false;
        }
        return true;
    }

    /**
     * Déplace le sailor de la distance demandée. Si la distance dépasse 5 l'action
     * est annulée, ceci est pris en charge dans le constructeur de Moving
     * 
     * @param xdistance
     * @param ydistance
     */
    public Moving moveSailor(Sailor sailor, int xdistance, int ydistance) {
        Moving action = new Moving(sailor.getId(), xdistance, ydistance);
        sailor.move(action.getXDistance(), action.getYDistance());
        if (action.getXDistance() != 0 || action.getYDistance() != 0) {
            Logs.add("\nS" + sailor.getId() + " is moving to (" + sailor.getX() + "," + sailor.getY() + ")");
            return action;
        }
        return null;
    }

    @JsonIgnore
    /**
     *
     * @return la liste des rames à gauche du bateau.
     */
    public List<Oar> getLeftOars(){
        ArrayList<Oar> oarsList = new ArrayList<>();
        this.oars.forEach(oar->
        {
            if(isLeft(oar))
                oarsList.add(oar);
        });
        return oarsList;
    }

    @JsonIgnore
    /**
     *
     * @return la liste des rames à droite du bateau.
     */
    public List<Oar> getRightOars(){
        ArrayList<Oar> oarsList = new ArrayList<>();
        this.oars.forEach(oar->
        {
            if(isLeft(oar))
                oarsList.add(oar);
        });
        return oarsList;
    }

    @JsonIgnore
    public List<Oar> getUsedOars(){
        return oars.stream().filter(oar -> oar.hasSailorOn() && oar.getSailorOn().isOnEntity()).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return  "width : "+this.width+
                " | height : "+this.length;
    }

    public boolean isLeft(Oar oar){
        return (oar.getY()<width/2);
    }

    public boolean isLeft(Sailor sailor){
        return (sailor.getY()<width/2);
    }

    public boolean isRight(Sailor sailor){
        return (sailor.getY() >= ((width / 2) + (width % 2)));
    }

    public boolean hasSail(){
        return !sails.isEmpty();
    }
    //------------------------------GETTER-------------------------//

    public int getWidth() {
        return width;
    }

    public int getLength() {
        return length;
    }

    public Rudder getRudder() {
        return rudder;
    }

    public List<Sailor> getSailors() {
        return sailors;
    }

    public List<Oar> getOars() {
        return oars;
    }

    public List<Sail> getSails() {
        return sails;
    }

    //------------------------------SETTER-------------------------//


    public void setWidth(int width) {
        this.width = width;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setSailors(List<Sailor> sailors) {
        this.sailors = sailors;
    }

    public void setOars(List<Oar> oars) {
        this.oars = oars;
    }

    public void setRudder(Rudder rudder) {
        this.rudder = rudder;
    }

    public void setSails(List<Sail> sails) {
        this.sails = sails;
    }
}
