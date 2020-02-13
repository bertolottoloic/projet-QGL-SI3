package fr.unice.polytech.si3.qgl.zecommit.boat;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import fr.unice.polytech.si3.qgl.zecommit.crew.Sailor;
import fr.unice.polytech.si3.qgl.zecommit.entite.*;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public String toString() {
        return  "width : "+this.width+
                " | height : "+this.length;
    }

    public boolean isLeft(Oar oar){
        if (oar.getY()<width/2)
            return true;
        return false;
    }
    //------------------------------GETTER-------------------------//

    public int getWidth() {
        return width;
    }

    public int getLength() {
        return length;
    }


    //------------------------------SETTER-------------------------//


    public void setWidth(int width) {
        this.width = width;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
