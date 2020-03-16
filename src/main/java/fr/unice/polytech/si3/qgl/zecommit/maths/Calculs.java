package fr.unice.polytech.si3.qgl.zecommit.maths;


import fr.unice.polytech.si3.qgl.zecommit.boat.Position;
import fr.unice.polytech.si3.qgl.zecommit.other.Reef;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Nathan
 */
public class Calculs {


    /**
     * Subdivise le trajet en une liste de Positions
     *
     * @param position      du bateau
     * @param position      du bateau
     * @param finalPosition la position prévue
     * @return liste de positions
     */
    public static List<Position> subdiviseRoute(Position position, Position finalPosition) { //TODO à refactorer
        List<Position> resPositions = new ArrayList<>();
        double xStart = position.getX();
        double yStart = position.getY();
        double xFinish = finalPosition.getX();
        double yFinish = finalPosition.getY();
        double xDiff = Math.abs(xFinish - xStart);
        double yDiff = Math.abs(yFinish - yStart);
        resPositions.add(position);
        int step = 200;

        if (xStart < xFinish && yStart < yFinish) {
            for (int k = 1; k < step; k++) {
                resPositions.add(new Position(xStart + (double) 1 / step * k * xDiff, yStart + (double) 1 / step * k * yDiff, position.getOrientation()));
                resPositions.add(new Position(xStart + (double) 1 / step * k * xDiff, yStart + (double) 1 / step * k * yDiff, position.getOrientation()));
            }
        }

        if (xStart > xFinish && yStart < yFinish) {
            for (int k = 1; k < step; k++) {
                resPositions.add(new Position(xStart - (double) 1 / step * k * xDiff, yStart + (double) 1 / step * k * yDiff, position.getOrientation()));
                resPositions.add(new Position(xStart - (double) 1 / step * k * xDiff, yStart + (double) 1 / step * k * yDiff, position.getOrientation()));
            }
        }

        if (xStart > xFinish && yStart > yFinish) {
            for (int k = 1; k < step; k++) {
                resPositions.add(new Position(xStart - (double) 1 / step * k * xDiff, yStart - (double) 1 / step * k * yDiff, position.getOrientation()));
                resPositions.add(new Position(xStart - (double) 1 / step * k * xDiff, yStart - (double) 1 / step * k * yDiff, position.getOrientation()));
            }
        }

        if (xStart < xFinish && yStart > yFinish) {
            for (int k = 1; k < step; k++) {
                resPositions.add(new Position(xStart + (double) 1 / step * k * xDiff, yStart - (double) 1 / step * k * yDiff, position.getOrientation()));
                resPositions.add(new Position(xStart + (double) 1 / step * k * xDiff, yStart - (double) 1 / step * k * yDiff, position.getOrientation()));
            }
        }

        if (xStart == xFinish && yStart > yFinish) {
            for (int k = 1; k < step; k++) {
                resPositions.add(new Position(xStart, yStart - (double) 1 / step * k * yDiff, position.getOrientation()));
                resPositions.add(new Position(xStart, yStart - (double) 1 / step * k * yDiff, position.getOrientation()));
            }
        }
        if (xStart == xFinish && yStart < yFinish) {
            for (int k = 1; k < step; k++) {
                resPositions.add(new Position(xStart, yStart + (double) 1 / step * k * yDiff, position.getOrientation()));
                resPositions.add(new Position(xStart, yStart + (double) 1 / step * k * yDiff, position.getOrientation()));
            }
        }

        if (xStart > xFinish && yStart == yFinish) {
            for (int k = 1; k < step; k++) {
                resPositions.add(new Position(xStart - (double) 1 / step * k * xDiff, yStart, position.getOrientation()));
                resPositions.add(new Position(xStart - (double) 1 / step * k * xDiff, yStart, position.getOrientation()));
            }
        }
        if (xStart < xFinish && yStart == yFinish) {
            for (int k = 1; k < step; k++) {
                resPositions.add(new Position(xStart + (double) 1 / step * k * xDiff, yStart, position.getOrientation()));
                resPositions.add(new Position(xStart + (double) 1 / step * k * xDiff, yStart, position.getOrientation()));
            }
        }
        resPositions.add(finalPosition);
        return resPositions;
    }


    /**
     * Méthode vérifiant les collisions avec tous les récifs présents
     *
     * @return true en cas de collision
     */
    public static boolean checkCollision(List<Reef> reefs, List<Position> route) {
        boolean res = false;

        for (Reef reef : reefs) {
            for (Position nextPosition : route) {

                Collision collision = new Collision(reef.getShape(), reef.getPosition(), nextPosition);
                if (collision.collide()) {
                    res = true;
                }
            }
        }
        return res;
    }

    public static List<Position> findIntersectionsCercle(Position position1, Position position2) {
        double x1 = position1.getX();
        double y1 = position1.getY();
        double x2 = position2.getX();
        double y2 = position2.getY();

        double xCenter = (x1 + x2) / 2;
        double yCenter = (y1 + y2) / 2;
        Position center = new Position(xCenter, yCenter, 0);

        double rayon = distanceTo(position1, center);
        double r1 = Math.sqrt(rayon * rayon + rayon * rayon);

        double xc1 = position1.getX(); // abscisse du centre du premier cercle
        double yc1 = position1.getY(); // ordonnée du centre du premier cercle
        double rc1 = r1; // rayon du premier cercle
        double xc2 = position2.getX(); // abscisse du centre du second cercle
        double yc2 = position2.getY(); // ordonnée du centre du second cercle
        double rc2 = r1; // rayon du second cercle
        double xia = 0;
        double xib = 0;
        double yia = 0;
        double yib = 0;

        if (yc1 == yc2) { // si les deux cercles sont sur la même abscisse, on utilise Pythagore...

            double a = Math.abs(xc1 - xc2);
            xia = (Math.pow(rc2, 2) - Math.pow(a, 2) - Math.pow(rc1, 2)) / (-2 * a);
            xib = (Math.pow(rc2, 2) - Math.pow(a, 2) - Math.pow(rc1, 2)) / (-2 * a);
            yia = Math.sqrt(Math.pow(rc2, 2) - Math.pow(a - xia, 2));
            yib = -yia;
        } else // ...sinon, cas général
        {
            double a = (-Math.pow(xc1, 2) - Math.pow(yc1, 2) + Math.pow(xc2, 2) + Math.pow(yc2, 2) + Math.pow(rc1, 2) - Math.pow(rc2, 2)) / (2 * (yc2 - yc1));
            double d = (xc2 - xc1) / (yc2 - yc1);
            double A = Math.pow(d, 2) + 1;
            double B = -2 * xc1 + 2 * yc1 * d - 2 * a * d;
            double C = Math.pow(xc1, 2) + Math.pow(yc1, 2) - 2 * yc1 * a + Math.pow(a, 2) - Math.pow(rc1, 2);
            double delta = Math.pow(B, 2) - 4 * A * C;
            xia = (-B + Math.sqrt(delta)) / (2 * A);
            xib = (-B - Math.sqrt(delta)) / (2 * A);
            yia = a - ((-B + Math.sqrt(delta)) / (2 * A)) * d;
            yib = a - ((-B - Math.sqrt(delta)) / (2 * A)) * d;
        }

        if (Double.isNaN(xia) || Double.isNaN(yia) || Double.isNaN(xib) || Double.isNaN(yib))
            return null; // si les cercles ne se touchent pas ou bien sont identiques

        List<Position> res = new ArrayList<>();
        res.add(new Position(xia, yia, 0));
        res.add(new Position(xib, yib, 0));
        return res; // coordonnées des deux points d'intersection (nb : seront identiques si les cercles ne se touchent qu'en un seul point)
    }



    public static double distanceTo(Position position1, Position position2) {
        return Math.sqrt(Math.pow(position2.getX() - position1.getX(),2) + Math.pow(position2.getY() - position1.getY(),2));
    }

}
