package fr.unice.polytech.si3.qgl.zecommit.maths;


import fr.unice.polytech.si3.qgl.zecommit.boat.Position;
import fr.unice.polytech.si3.qgl.zecommit.shape.Point;
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
        this.shapePosition = shapePosition;
        this.shipPosition = shipPosition;
    }

    public boolean collide(){
        //cas avec un cercle
        if (shape1.isCircle() && distanceTo(shapePosition, shipPosition) < shape1.getRadius()) {
            //Si le centre du bateau est dans le CP
            return true;
        }
        else {
            if (!shape1.isCircle() && isInRectangle((Rectangle)shape1)){
                return true;
            }

        }
        return false;


    }


    /**
     * Méthode pour determiner si le bateau est dans le CP
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

        // cas d'un rectangle avec une orientation de 0, PI/2, PI...
        if (isInRectangleWith0Orientation(rectangle, shipCenter, a, b, c)) return true;


        return isInTriangle(a,b,c, shipCenter) && isInTriangle(a,d,c, shipCenter);

    }

    public boolean isInRectangleWith0Orientation(Rectangle rectangle, Point shipCenter, Point a, Point b, Point c) {
        if(rectangle.getOrientation() % Math.PI/2 == 0
                && shipCenter.getX()<=Math.max(a.getX(), Math.max(b.getX(), c.getX()))
                && shipCenter.getX()>=Math.min(a.getX(), Math.min(b.getX(), c.getX()))
                && shipCenter.getY()<=Math.max(a.getY(), Math.max(b.getY(), c.getY()))
                && shipCenter.getY()>=Math.min(a.getY(), Math.min(b.getY(), c.getY()))
            )
                return true;
        return false;
    }

    /**
     * Une méthode permettant de déterminer les 4 sommets du rectangle
     * @param rectangle
     * @return
     */
    public List<Point> determineRectanglePoints(Rectangle rectangle, Position position ){
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

        if(d==0)
            return false;//Cas non pris en compte pour les triangles mais fonctionnel pour les rectangles

        else
            t = (f - (tp * e)) / d;

        double res = 1-t-tp;

        if ( 0 <= res && res <= 1) {
            return true;
        }
        return false;
    }


    /**
     * Methode qui calcule la distance d'une position par rapport au bateau
     * @param position1,
     * @param position2
     * @return
     */
    //TODO : duplicate method => A refactorer
    public double distanceTo(Position position1, Position position2) {
        return Math.sqrt(Math.pow(position1.getX() - position2.getX(),2) + Math.pow(position1.getY() - position2.getY(),2));
    }



}
