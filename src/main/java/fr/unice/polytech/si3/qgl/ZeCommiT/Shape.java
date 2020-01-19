package fr.unice.polytech.si3.qgl.ZeCommiT;

/**
 * Model de Forme
 * @author Clement P
 */
public abstract class Shape {
    private String type;

    public Shape(String type){
        this.type=type;
    }


    //------------------------GETTER----------------------//
    public String getType() {
        return type;
    }
}
