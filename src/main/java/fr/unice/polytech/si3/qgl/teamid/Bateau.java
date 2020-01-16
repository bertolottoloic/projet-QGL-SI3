package fr.unice.polytech.si3.qgl.teamid;

/**
 * @author Loic Bertolotto
 */
class Bateau {
    private String type;
    private int life;
    private Position position;
    private String name;
    private Deck deck;
    private ArrayList<Entity> entities;
    private Forme shape;
    
    Bateau(String type, int life, Position position, String name, Deck deck,ArrayList<Entity> entities,Forme shape){
        this.type = type;
        this.life = life;
        this.position = position;
        this.name = name;
        this.deck = deck;
        this.entities = entities;
        this.shape = shape;
    }



}