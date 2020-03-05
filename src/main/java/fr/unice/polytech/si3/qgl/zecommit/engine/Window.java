package fr.unice.polytech.si3.qgl.zecommit.engine;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.util.Random;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Window extends JFrame{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        new Window();

    }

    public Window(){

        this.setSize(700,700);
        this.setPreferredSize(new Dimension(700,700));
        this.setTitle("Drawing tings");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(new TestingPanelGraphics(), BorderLayout.CENTER);
        this.setVisible(true);

    }

    public class TestingPanelGraphics extends JPanel {

        public TestingPanelGraphics(){
            setLayout(new BorderLayout());
            this.setPreferredSize(new Dimension(700,700));
            this.add(new DrawStuff(), BorderLayout.CENTER);
            revalidate();
            repaint();
            this.setVisible(true); //probably not necessary

        }

        private class DrawStuff extends JComponent{

            @Override
            protected void paintComponent(Graphics g){
                super.paintComponent(g);

                Graphics2D graph2 = (Graphics2D) g;

                graph2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                graph2.setColor(Color.BLACK);

                Dimension size = getSize();
                int w = size.width ;
                int h = size.height;
                int v =500;
                graph2.drawLine(v, v, v, v);

                for (int i = 0; i <= 100; i++) {
                    Random r = new Random();
                    int x = Math.abs(r.nextInt()) % w;
                    int y = Math.abs(r.nextInt()) % h;
                    //graph2.drawLine(x, y, x, y);
                }
            }
        }
    }
}