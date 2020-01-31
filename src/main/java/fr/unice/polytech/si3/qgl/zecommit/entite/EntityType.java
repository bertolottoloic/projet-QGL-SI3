package fr.unice.polytech.si3.qgl.zecommit.entite;

public enum EntityType{
    oar,rudder,sail,watch;

    public String toString(){
        return this.name();
    }
}