package fr.unice.polytech.si3.qgl.zecommit.maths;


import fr.unice.polytech.si3.qgl.zecommit.boat.Position;
import fr.unice.polytech.si3.qgl.zecommit.shape.Point;
import fr.unice.polytech.si3.qgl.zecommit.shape.Polygone;
import fr.unice.polytech.si3.qgl.zecommit.shape.Rectangle;
import fr.unice.polytech.si3.qgl.zecommit.shape.Shape;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Nathan
 */
public class Collision {

    private Shape shape1;
    private Position shapePosition;
    private Position shipPosition;

    public Collision(Shape shape1, Position shapePosition, Position shipPosition) {
        this.shape1 = shape1;
        this.shapePosition = shapePosition; //TODO optionel si polygone
        this.shipPosition = shipPosition;
    }

    public boolean collide() {
        //cas avec un checkpoint circulaire
        if (shape1.isCircle() && !shape1.isRectangle() && distanceTo() < shape1.getShapeRadius()) {
            return true;
        }
        //cas avec un checkpoint rectangulaire
        if (!shape1.isCircle() && shape1.isRectangle() && isInRectangle((Rectangle) shape1)) {
            return true;
        }
        //cas avec un checkpoint polygonal
        if (!shape1.isCircle() && !shape1.isRectangle() && isInpolygone((Polygone) shape1))
            return true;

        return false;

    }

    public boolean isInpolygone(Polygone polygone){
        Point shipCenter = new Point(shipPosition.getX(), shipPosition.getY());
        int nbVertices = polygone.getVertices().length;
        boolean res = false;

        //On subdivise le polygone en plusieurs triangles
        for(int i = 0; i<nbVertices-1 ; i++) {
            Point a = polygone.getVertice(i);
            Point b = polygone.getVertice(i + 1);

            int k = nbVertices/2;
            Point c = polygone.getVertice((i + 1 + k)%nbVertices);

            if (isInTriangle(a, b, c, shipCenter))
                res = true;
        }
        return res;
    }


    /**
     * Méthode pour determiner si le bateau est dans un CP rectangulaire
     * Cette méthode coupe le rectangle en 2 triangles
     *
     *  A ___ B
     *   |___|
     *  D     C
     *
     * @param rectangle
     * @return
     */
    public boolean isInRectangle(Rectangle rectangle){
        Point shipCenter = new Point(shipPosition.getX(), shipPosition.getY());
        List<Point> vertexList = determineRectanglePoints(rectangle, shapePosition);

        Point a = vertexList.get(0);
        Point b = vertexList.get(1);
        Point c = vertexList.get(2);
        Point d = vertexList.get(3);

        return isInTriangle(a,b,c, shipCenter) || isInTriangle(c,d,a, shipCenter) || isInTriangle(b,c,d, shipCenter);

    }

    /**
     * Une méthode permettant de déterminer les 4 sommets du rectangle
     * @param rectangle
     * @return
     */
    public static List<Point> determineRectanglePoints(Rectangle rectangle, Position position ){
        ArrayList<Point> res = new ArrayList<>();

        double angle = rectangle.getOrientation(); // l'orientation du rectangle

        double largeur = rectangle.getWidth(); //Largeur
        double l = rectangle.getHeight(); // Longueur
        double r = Math.sqrt(l*l/4 + largeur*largeur/4);


        ArrayList<Double> angleList = new ArrayList<>();
        angleList.add(Math.atan(largeur/l));//angle formé entre l'orientation et le point en haut à droite
        angleList.add(-Math.atan(largeur/l));//angle formé entre l'orientation et le point en bas à droite
        angleList.add(-Math.PI + Math.atan(largeur/l));//angle formé entre l'orientation et le point en bas à gauche
        angleList.add(Math.PI-Math.atan(largeur/l));//angle formé entre l'orientation et le point en haut à gauche


        for (double i : angleList) {
                res.add(new Point(position.getX() + r * Math.cos(angle + i), position.getY() + r * Math.sin(angle + i)));

        }
        return res;

    }

    /**
     * Determine si le point M est dans le triangle ABC
     * @param a
     * @param b
     * @param c
     * @param m
     * @return
     */
    public boolean isInTriangle(Point a, Point b, Point c, Point m) {
        double t;
        double tp;

        double d = b.getX() - a.getX();
        double e = c.getX() - a.getX();
        double f = m.getX() - a.getX();
        double g = b.getY() - a.getY();
        double h = c.getY() - a.getY();
        double i = m.getY() - a.getY();

        if ((e*g) - (h*d) == 0) {
            return false; //Le triangle est plat
        }
        tp = ((f*g) - (d*i)) / ((e*g) - (h*d));

        if(d==0){
            return isInTriangleParticularCase(b, c, m, e, g, i);

        }

        else
            t =(f - (tp * e)) / d;

        return (0 <= t) && (t <= 1) && (0 <= tp) && (tp <= 1) && (t+tp <= 1);
    }

    /**
     * Méthode pour savoir si un point est dans un triangle dans le cas particulier où d=0
     * @param b
     * @param c
     * @param m
     * @param e
     * @param g
     * @param i
     * @return
     */
    public boolean isInTriangleParticularCase(Point b, Point c, Point m, double e, double g, double i) {
        return Math.abs(Math.atan(i/(c.getX()-m.getX()))) < Math.abs(Math.atan(g/e))
                && Math.min(c.getX(), b.getX()) <=m.getX()
                && Math.max(c.getX(), b.getX()) >= m.getX();
    }


    /**
     * Methode qui calcule la distance d'une position par rapport au bateau
     * @return
     */
    //TODO : duplicate method => A refactorer
    public double distanceTo() {
        return Math.sqrt(Math.pow(shapePosition.getX() - shipPosition.getX(),2) + Math.pow(shapePosition.getY() - shipPosition.getY(),2));
    }


}
