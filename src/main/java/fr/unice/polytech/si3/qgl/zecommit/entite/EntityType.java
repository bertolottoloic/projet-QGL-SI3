package fr.unice.polytech.si3.qgl.zecommit.entite;

public enum EntityType{
    OAR,RUDDER,SAIL,WATCH;


    @Override
    public String toString(){
        return this.name();
    }
}
