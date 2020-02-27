package fr.unice.polytech.si3.qgl.zecommit.entite;

public enum EntityType{
    oar,rudder,sail,watch;


    @Override
    public String toString(){
        return this.name().toLowerCase();
    }
}
