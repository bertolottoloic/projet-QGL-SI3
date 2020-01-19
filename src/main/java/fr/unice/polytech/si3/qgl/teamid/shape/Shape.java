package fr.unice.polytech.si3.qgl.teamid.shape;

import fr.unice.polytech.si3.qgl.teamid.Position;

/**
 * Model de Forme
 * @author Clement P
 */
public abstract class Shape {
    private String type;
    private Position centre;

    public Shape(String type, Position centre){
        this.type=type;
        this.centre=centre;
    }


    //------------------------GETTER----------------------//
    public String getType() {
        return type;
    }
}
