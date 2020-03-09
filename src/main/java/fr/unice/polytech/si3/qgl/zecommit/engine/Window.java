package fr.unice.polytech.si3.qgl.zecommit.engine;

import fr.unice.polytech.si3.qgl.zecommit.boat.Position;
import fr.unice.polytech.si3.qgl.zecommit.other.*;
import fr.unice.polytech.si3.qgl.zecommit.shape.Circle;
import fr.unice.polytech.si3.qgl.zecommit.shape.Polygone;
import fr.unice.polytech.si3.qgl.zecommit.shape.Rectangle;

import javax.swing.*;
import java.awt.*;
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
            this.setBackground(new Color(223, 255, 252));
            this.setVisible(true);

        }

        private class DrawPoints extends JComponent{
            private ArrayList<Position> points;
            private List<Checkpoint> checkPoints;
            private List<Stream> streams;
            private List<Reef> reefs;
            final double cst= (double)w/2;

            public DrawPoints(ArrayList<Position> points,List<Checkpoint> checkpoints,List<VisibleEntitie> visibleEntities){
                 this.points = new ArrayList<>(points);
                 this.checkPoints=new ArrayList<>(checkpoints);
                 this.streams=new ArrayList<>();
                 this.reefs = new ArrayList<>();
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
                        this.reefs.add((Reef) entity);
                    }
                }
            }

            public Shape drawCheckpoint(Checkpoint chkp){
                java.awt.Shape shp=null;
                switch (chkp.getShape().getType()){
                    case "circle":
                        shp=new Ellipse2D.Double((chkp.getPosition().getX()/scale)-(chkp.getCircleRadius()/scale)+cst,(( chkp.getPosition().getY()/scale)-(chkp.getCircleRadius()/scale)+cst),(chkp.getCircleRadius()*2/scale),(chkp.getCircleRadius()*2/scale));
                        break;
                    case "rectangle":
                        shp=new java.awt.Rectangle.Double((chkp.getPosition().getX()/scale)+cst-(((Rectangle)chkp.getShape()).getWidth()/(2*scale)), (chkp.getPosition().getY()/scale)+cst-(((Rectangle)chkp.getShape()).getHeight()/(2*scale)), (((Rectangle)chkp.getShape()).getWidth()/scale),(((Rectangle)chkp.getShape()).getHeight()/scale));
                        break;
                    case "polygon":
                        int[] x=((Polygone)chkp.getShape()).getVerticesIntX();
                        int[] y=((Polygone)chkp.getShape()).getVerticesIntY();

                        for (int i=0; i<x.length;i++) {
                            x[i]=(int)(x[i]/scale);
                            x[i]+=cst+chkp.getPosition().getX()/scale;
                        }
                        for (int i=0; i<y.length;i++) {
                            y[i]=(int)(y[i]/scale);

                            y[i]+=cst+chkp.getPosition().getY()/scale;
                        }
                        shp=new Polygon(x,y,((Polygone)chkp.getShape()).getVertices().length);
                        break;
                }
                return shp;
            }

            public Shape drawVisibles(VisibleEntitie ent){
                java.awt.Shape shp=null;
                switch (ent.getShape().getType()){
                    case "circle":
                        shp=new Ellipse2D.Double((ent.getPosition().getX()/scale)-(((Circle)ent.getShape()).getRadius()/(scale))+cst,(ent.getPosition().getY()/scale)-(((Circle)ent.getShape()).getRadius()/(scale))+cst,((Circle)ent.getShape()).getRadius()*2/(scale),((Circle)ent.getShape()).getRadius()*2/(scale));
                        break;
                    case "rectangle":
                        shp=new java.awt.Rectangle.Double((ent.getPosition().getX()/scale)+cst-(((Rectangle)ent.getShape()).getWidth()/(2*scale)), (ent.getPosition().getY()/scale)+cst-(((Rectangle)ent.getShape()).getHeight()/(2*scale)), (((Rectangle)ent.getShape()).getWidth()/scale),(((Rectangle)ent.getShape()).getHeight()/scale));
                        break;
                    case "polygon":
                        int[] x=((Polygone)ent.getShape()).getVerticesIntX();
                        int[] y=((Polygone)ent.getShape()).getVerticesIntY();

                        for (int i=0; i<x.length;i++) {
                            x[i]=(int)(x[i]/scale);
                            x[i]+=cst+ent.getPosition().getX()/scale;
                        }
                        for (int i=0; i<y.length;i++) {
                            y[i]=(int)(y[i]/scale);

                            y[i]+=cst+ent.getPosition().getY()/scale;
                        }
                        shp=new Polygon(x,y,((Polygone)ent.getShape()).getVertices().length);
                        break;
                }
                return shp;
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
                graph2.setColor(Color.ORANGE);
                for (Checkpoint checkPoint: checkPoints) {
                    java.awt.Shape check = drawCheckpoint(checkPoint);
                    graph2.draw(check);
                }
                graph2.setColor(Color.BLUE);
                for (Stream stream: streams){
                    java.awt.Shape current = drawVisibles(stream);
                    graph2.draw(current);
                }
                graph2.setColor(Color.RED);
                for (Reef reef: reefs){
                    java.awt.Shape rock = drawVisibles(reef);
                    graph2.draw(rock);
                }
                graph2.setColor(Color.BLACK);

            }
        }
    }
}
