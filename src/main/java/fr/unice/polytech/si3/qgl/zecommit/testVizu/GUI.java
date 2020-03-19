package fr.unice.polytech.si3.qgl.zecommit.testVizu;

import fr.unice.polytech.si3.qgl.zecommit.crew.Sailor;
import fr.unice.polytech.si3.qgl.zecommit.engine.Engine;

import javax.swing.*;
import java.awt.List;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.*;
import java.awt.*;

public class GUI extends JFrame {

    int spacing = 5;
    int size = 80;
    int column = 8;
    int row = 8;
    String[][] tab;

    ArrayList sailorsDeckVizu = Engine.sailorsDeckVizu;
    int sailorsDeckVizuSize = sailorsDeckVizu.size();

    public GUI() {
        this.setTitle("BoatDeck");
        this.setSize(1280,720);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        Board board = new Board();
        this.setContentPane(board);

        Move move = new Move();
        this.addMouseMotionListener(move);

        Click click = new Click();
        this.addMouseListener(click);

        tab = new String[column][row];
        for (int i = 0; i < column; i++) {
            for (int j = 0; j < row; j++) {
                tab[i][j] = "";
            }
        }

        tab[1][1] = "ID : 1";
        tab[2][2] = "ID : 2";

    }



    public class Board extends JPanel {
        public void paintComponent(Graphics g) {
            g.setColor(Color.DARK_GRAY);
            g.fillRect(0,0,1280,720);
            g.setColor(Color.gray);
            for (int i = 0; i < column; i++) {
                for (int j = 0; j < row; j++) {
                    if (tab[i][j].equals("")) {
                        g.setColor(Color.GRAY);
                        g.fillRect(spacing + i * size, spacing + j * size, size - spacing, size - spacing);
                    }
                    else {
                        g.fillRect(spacing + i * size, spacing + j * size, size - spacing, size - spacing);
                        g.setColor(Color.LIGHT_GRAY);
                        g.setFont(new Font("Serial", Font.PLAIN, 12));
                        g.drawString(tab[i][j], spacing + i * size + (size/3), spacing + j * size + (size/2));
                    }

                }
            }
        }
    }

    public class Move implements MouseMotionListener {

        @Override
        public void mouseDragged(MouseEvent e) {

        }

        @Override
        public void mouseMoved(MouseEvent e) {
            System.out.println("move");

        }
    }

    public class Click implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            System.out.println("Click");
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

}
