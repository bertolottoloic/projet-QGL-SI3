package fr.unice.polytech.si3.qgl.zecommit.crew;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.AbstractMap.SimpleEntry;

import fr.unice.polytech.si3.qgl.zecommit.Game;
import fr.unice.polytech.si3.qgl.zecommit.boat.Deck;
import fr.unice.polytech.si3.qgl.zecommit.entite.Entity;
import fr.unice.polytech.si3.qgl.zecommit.entite.Oar;

public class CaptainBis implements CaptainInterface {

    private Game game;
    private Deck deck;

    public CaptainBis(Game game) {
        this.game = game;
        this.deck = game.getShip().getDeck();
    }

    @Override
    public void attributeEntitiesToSailors() {
        List<Sailor> sailors = deck.getSailors();
        sailors.forEach(s -> s.reinitializeEntity());
        List<Sailor> sailorTmp = new ArrayList<>(sailors);
        List<Entity> oars = new ArrayList<>(deck.getOars());
        sailorTmp.sort(Comparator.comparingInt(a -> a.distanceToNearestEntity(oars)));
        Sailor sailor;
        if (sailorTmp.size() % 2 != 0 && deck.getRudder() != null) {
            sailor = sailorTmp.remove(sailorTmp.size() - 1);
            sailor.setOnEntity(deck.getRudder());
        }
        for (Sailor tmp : sailorTmp) {
            deck.getOars().sort(Comparator.comparingInt(a -> tmp.distanceToEntity(a)));
            Oar closestOar = deck.getOars().get(0);
            if (!closestOar.hasSailorOn() && tmp.distanceToEntity(closestOar) <= 5 && !tmp.hasEntity()) {
                tmp.setOnEntity(closestOar);
            }
        }
        for (Sailor tmp : sailorTmp) {
            deck.getOars().sort(Comparator.comparingInt(a -> tmp.distanceToEntity(a)));
            for (Oar oar : deck.getOars()) {
                if (!oar.hasSailorOn() && !tmp.hasEntity()) {
                    tmp.setOnEntity(oar);
                }
            }
        }

    }

    @Override
    public List<Sailor> doMoveSailors() {
        if (!deck.sailorsAreOnTheirEntity())
            return deck.getSailors();
        return new ArrayList<Sailor>();
    }

    @Override
    public List<Sailor> doActivateOars() {
        return new ArrayList<Sailor>();
    }

    @Override
    public SimpleEntry<Sailor, Double> doTurn() {
        return null;
    }

    @Override
    public List<Sailor> doLiftSail() {
        return new ArrayList<Sailor>();
    }

    @Override
    public List<Sailor> doLowerSail() {
        return new ArrayList<Sailor>();
    }

    public Deck getDeck(){
        return this.deck;
    }

}