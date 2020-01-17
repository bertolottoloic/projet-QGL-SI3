package fr.unice.polytech.si3.qgl.teamid;

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
    private Forme shape;
    
    Bateau(String type, int life, Position position, String name, Deck deck,List<Entite> entities,Forme shape){
        this.type = type;
        this.life = life;
        this.position = position;
        this.name = name;
        this.deck = deck;
        this.entities = entities;
        this.shape = shape;
    }



}