package fr.unice.polytech.si3.qgl.zecommit.boat;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import fr.unice.polytech.si3.qgl.zecommit.Logs;
import fr.unice.polytech.si3.qgl.zecommit.action.Moving;
import fr.unice.polytech.si3.qgl.zecommit.crew.Sailor;
import fr.unice.polytech.si3.qgl.zecommit.deserializer.DeckDeserializer;
import fr.unice.polytech.si3.qgl.zecommit.entite.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


/**
 * @author Loic Bertolotto
 */
@JsonDeserialize(using = DeckDeserializer.class)
public class Deck{
    private int width;
    private int length;
    @JsonIgnore
    private List<Oar> oars;
    @JsonIgnore
    private Rudder rudder;
    @JsonIgnore
    private List<Sail> sails;
    @JsonIgnore
    private List<Sailor> sailors;
    private List<Sailor> rightSailorList;
    private List<Sailor> leftSailorList;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Deck deck = (Deck) o;
        return width == deck.width &&
                length == deck.length;
    }

    @Override
    public int hashCode() {
        return Objects.hash(width, length);
    }

    public Deck(int width, int length){

        this.width = width;
        this.length = length;
        this.oars = new ArrayList<>();
        this.sails = new ArrayList<>();
        this.sailors = new ArrayList<>();
        this.leftSailorList = new ArrayList<>();
        this.rightSailorList = new ArrayList<>();
    }

    @JsonIgnore
    public void initDeck(List<Entity> entities){
        for (Entity entity : entities){
            if (entity.getType().equals(EntityType.OAR)) {
                this.oars.add((Oar) entity);
            }
            if (entity.getType().equals(EntityType.RUDDER)) {
                this.rudder=(Rudder)entity;
            }
            if (entity.getType().equals(EntityType.SAIL)) {
                this.sails.add((Sail) entity);
            }
        }
    }
    @JsonIgnore
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
    @JsonIgnore
    public Moving moveSailor(Sailor sailor, int xdistance, int ydistance) {
        Moving action = new Moving(sailor.getId(), xdistance, ydistance);
        sailor.move(action.getXDistance(), action.getYDistance());
        if (action.getXDistance() != 0 || action.getYDistance() != 0) {
            Logs.add("\nS" + sailor.getId() + " is moving to (" + sailor.getX() + "," + sailor.getY() + ")");
            return action;
        }
        return null;
    }

    /**
     *
     * @return la liste des rames à gauche du bateau.
     */
    @JsonIgnore
    public List<Oar> getLeftOars(){
        ArrayList<Oar> oarsList = new ArrayList<>();
        this.oars.forEach(oar->
        {
            if(isLeft(oar))
                oarsList.add(oar);
        });
        return oarsList;
    }

    /**
     *
     * @return la liste des rames à droite du bateau.
     */
    @JsonIgnore
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

    @JsonIgnore
    public Rudder getRudder() {
        return rudder;
    }

    @JsonIgnore
    public List<Sailor> getSailors() {
        return sailors;
    }

    @JsonIgnore
    public List<Oar> getOars() {
        return oars;
    }

    @JsonIgnore
    public List<Sail> getSails() {
        return sails;
    }

    public List<Sailor> getLeftSailors() {
        return this.leftSailorList;
    }

    public List<Sailor> getRightSailors() {
        return this.rightSailorList;
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
