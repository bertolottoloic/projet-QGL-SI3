package fr.unice.polytech.si3.qgl.teamid;

/**
 * Model de Forme
 * @author Clement P
 */
public abstract class Forme {
    private String type;

    public Forme(String type){
        this.type=type;
    }


    //------------------------GETTER----------------------//
    public String getType() {
        return type;
    }
}
