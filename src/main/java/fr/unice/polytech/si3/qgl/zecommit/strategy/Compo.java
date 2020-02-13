package fr.unice.polytech.si3.qgl.zecommit.strategy;

public class Compo {
    private int sailorsRight;
    private int sailorsLeft;

    public Compo(int sailorsLeft, int sailorsRight) {
        this.sailorsRight = sailorsRight;
        this.sailorsLeft = sailorsLeft;
    }

    public int getSailorsRight() {
        return sailorsRight;
    }

    public int getSailorsLeft() {
        return sailorsLeft;
    }

    @Override
    public String toString() {
        return "C{" +
                "R=" + sailorsRight +
                ", L=" + sailorsLeft +
                '}';
    }
}