package fr.unice.polytech.si3.qgl.zecommit.visible;

public enum VisibleEntityType{
    CURRENT,OTHERSHIP,REEF;


    @Override
    public String toString(){
        return this.name();
    }
}