package fr.unice.polytech.si3.qgl.ZeCommiT;

import fr.unice.polytech.si3.qgl.ZeCommiT.entite.Entite;
import fr.unice.polytech.si3.qgl.ZeCommiT.shape.Shape;

import java.util.List;

/**
 * @author Loic Bertolotto
 */
class Ship {
    private String type;
    private int life;
    private Position position;
    private String name;
    private Deck deck;
    private List<Entite> entities;
    private Shape shape;

    Ship(int life, Position position, String name, Deck deck, List<Entite> entities, Shape shape){
        this.type = "ship";
        this.type = type;
        this.life = life;
        this.position = position;
        this.name = name;
        this.deck = deck;
        this.entities = entities;
        this.shape = shape;
    }



}