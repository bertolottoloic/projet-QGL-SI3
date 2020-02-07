package fr.unice.polytech.si3.qgl.zecommit.crew;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;


import fr.unice.polytech.si3.qgl.zecommit.entite.Entity;

/**
 * Classe correspondant au marin
 * @author joris Liebgott
 */

public class Sailor {
    private int id;
    private int x;
    private int y;
    private String name;
    private Entity entity;

    public Sailor(int id,int x, int y, String name) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.name = name;
        this.entity = null;
    }
    

    @Override
    public String toString(){
        return "\n[id : "+this.id+
                "\nx : "+this.x+
                "\ny : "+this.y+
                "\nname : "+this.name+"]";
    }


    public void move(int xdistance, int ydistance){
        this.x+=xdistance;
        this.y+=ydistance;
    }

    public int distanceToEntity(Entity e){
        return Math.abs(x-e.getX()) + Math.abs(y-e.getY());
    }

    public boolean isOnEntity(){
        return hasEntity() && this.x==this.entity.getX() && this.y==this.entity.getY();
    }

    public boolean hasEntity(){
        return this.entity!=null;
    }

    public int distanceToNearestEntity(List<Entity> e){
        Stream<Entity> en = e.stream();
        Optional<Entity> min = en.min(Comparator.comparingInt(a->distanceToEntity(a)));
        if(min.isPresent())
            return distanceToEntity(min.get());
        else{
            return this.id;
        }
    }

    //--------------------GETTER -------------------------//

    public Entity getEntity(){
        return this.entity;
    }

    public int getId() {
        return id;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getName() {
        return name;
    }


    //------------------------------SETTER-------------------------//


    public void setId(int id) {
        this.id = id;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOnEntity(Entity e){//TODO à corriger
        if(this.entity != null)
            this.entity.putSailorOn(null);
        this.entity = e;
        this.entity.putSailorOn(this);     
    }
}
