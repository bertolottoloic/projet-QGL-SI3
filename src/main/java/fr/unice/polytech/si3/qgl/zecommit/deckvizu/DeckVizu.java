package fr.unice.polytech.si3.qgl.zecommit.deckvizu;

public class DeckVizu implements Runnable {


    GUI gui = new GUI();

    public static void main(String[] args) {
        new Thread(new DeckVizu()).start();
    }
    @Override
    public void run() {
        while(true) {
            gui.repaint();
        }
    }
}