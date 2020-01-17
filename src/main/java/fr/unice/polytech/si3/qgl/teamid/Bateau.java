package fr.unice.polytech.si3.qgl.teamid;

import fr.unice.polytech.si3.qgl.teamid.entite.Entite;
import fr.unice.polytech.si3.qgl.teamid.other.Checkpoint;
import fr.unice.polytech.si3.qgl.teamid.shape.Circle;
import fr.unice.polytech.si3.qgl.teamid.shape.Shape;

import java.util.List;

/**
 * @author Loic Bertolotto
 */
class Bateau {
    private String type;
    private int life;
    private Position position;
    private String name;
    private Deck deck;
    private List<Entite> entities;
    private Shape shape;
    
    Bateau(int life, Position position, String name, Deck deck, List<Entite> entities, Shape shape){
        this.type = "ship";
        this.life = life;
        this.position = position;
        this.name = name;
        this.deck = deck;
        this.entities = entities;
        this.shape = shape;
    }



}