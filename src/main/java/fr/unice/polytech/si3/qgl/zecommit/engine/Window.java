package fr.unice.polytech.si3.qgl.zecommit.engine;

import javax.swing.*;
import java.awt.*;
import fr.unice.polytech.si3.qgl.zecommit.boat.Position;
import fr.unice.polytech.si3.qgl.zecommit.other.Checkpoint;
import fr.unice.polytech.si3.qgl.zecommit.other.Stream;
import fr.unice.polytech.si3.qgl.zecommit.other.VisibleEntitie;
import fr.unice.polytech.si3.qgl.zecommit.other.VisibleEntityType;
import fr.unice.polytech.si3.qgl.zecommit.shape.Rectangle;

import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.List;

public class Window extends JFrame{

    public int w;
    public int h;
    public double scale = 10;

    public Window(ArrayList<Position> points, List<Checkpoint> checkpoints,List<VisibleEntitie> visibles){

        this.setSize(700,700);
        this.setPreferredSize(new Dimension(700,700));
        this.setTitle("ZE COMMIT");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension size = getSize();
        this.w= size.width;
        this.h = size.height;
        this.add(new TestingPanelGraphics(points,checkpoints,visibles), BorderLayout.CENTER);
        this.setVisible(true);


    }

    public class TestingPanelGraphics extends JPanel {

        public TestingPanelGraphics(ArrayList<Position> points, List<Checkpoint> checkpoints, List<VisibleEntitie> visibleEntities){
            setLayout(new BorderLayout());
            this.setPreferredSize(new Dimension(700,700));
            this.add(new DrawPoints(points,checkpoints,visibleEntities), BorderLayout.CENTER);

            revalidate();
            repaint();
            this.setVisible(true);

        }

        private class DrawPoints extends JComponent{
            private ArrayList<Position> points;
            private List<Checkpoint> checkPoints;
            private List<Stream> streams;
            final double cst= (double)w/2;

            public DrawPoints(ArrayList<Position> points,List<Checkpoint> checkpoints,List<VisibleEntitie> visibleEntities){
                 this.points = new ArrayList<>(points);
                 this.checkPoints=new ArrayList<>(checkpoints);
                 this.streams=new ArrayList<>();
                 sortVisibleEntities(visibleEntities);
            }

            public void sortVisibleEntities(List <VisibleEntitie> visibleEntities) {
                for (VisibleEntitie entity : visibleEntities) {
                    if (entity.getType().equals(VisibleEntityType.stream)) {
                        this.streams.add((Stream) entity);
                    }
                    if (entity.getType().equals(VisibleEntityType.ship)) {
                    }
                    if (entity.getType().equals(VisibleEntityType.reef)) {
                    }
                }
            }


            @Override
            protected void paintComponent(Graphics g){
                super.paintComponent(g);

                Graphics2D graph2 = (Graphics2D) g;
                graph2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                graph2.setColor(Color.BLACK);
                for (Position point: points) {
                    graph2.drawLine((int)Math.floor((point.getX()/scale)+cst),(int)Math.floor((point.getY()/scale)+cst),(int)(Math.floor((point.getX()/scale)+cst)),(int)Math.floor((point.getY()/scale)+cst));
                }
                for (Checkpoint checkPoint: checkPoints) {
                    Shape circle = new Ellipse2D.Double((checkPoint.getPosition().getX()/scale)-(checkPoint.getCircleRadius()/scale)+cst,(( checkPoint.getPosition().getY()/scale)-(checkPoint.getCircleRadius()/scale)+cst),(checkPoint.getCircleRadius()*2/scale),(checkPoint.getCircleRadius()*2/scale));
                    graph2.draw(circle);
                }
                for (Stream stream: streams){
                    Shape rect = new java.awt.Rectangle.Double((stream.getPosition().getX()/scale)+cst-(((Rectangle)stream.getShape()).getWidth()/(2*scale)), (stream.getPosition().getY()/scale)+cst-(((Rectangle)stream.getShape()).getHeight()/(2*scale)), (((Rectangle)stream.getShape()).getWidth()/scale),(((Rectangle)stream.getShape()).getHeight()/scale));
                    graph2.draw(rect);

                }
            }
        }
    }
}
