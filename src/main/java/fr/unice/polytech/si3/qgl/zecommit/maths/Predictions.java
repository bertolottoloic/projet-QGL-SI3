package fr.unice.polytech.si3.qgl.zecommit.maths;

import fr.unice.polytech.si3.qgl.zecommit.boat.Position;
import fr.unice.polytech.si3.qgl.zecommit.boat.Ship;
import fr.unice.polytech.si3.qgl.zecommit.crew.Sailor;
import fr.unice.polytech.si3.qgl.zecommit.entite.Sail;
import fr.unice.polytech.si3.qgl.zecommit.other.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe permettant de prévoir la prochaine position du bateau
 * @author Nathan
 */
public class Predictions {
    private List<Sailor> leftSailors;
    private List<Sailor> rightSailors;
    private int oarsNb;
    private Ship ship;
    private List<VisibleEntitie> visibleEntities;
    private double rotation;
    private List<Sail> sailArrayList;
    private Wind wind;
    private int nbSailUsed;
    private List<Reef> problematicReefs;//Liste des récifs pouvant engendrer une collision


    public Predictions(List<Sailor> leftSailors, List<Sailor> rightSailors,Ship ship, List<VisibleEntitie> visibleEntities, double rotation, Wind wind) {
        this.leftSailors = leftSailors;
        this.rightSailors = rightSailors;
        this.oarsNb = leftSailors.size() + rightSailors.size();
        this.ship = ship;
        this.visibleEntities = visibleEntities;
        this.rotation = rotation;
        this.sailArrayList = ship.getDeckSails();
        this.wind = wind;
        this.problematicReefs = new ArrayList<>();
    }

    /**
     * Méthode vérifiant les collisions avec tous les récifs présents
     * @return true en cas de collision
     */
    public boolean checkCollision() {
        boolean res = false;
        problematicReefs.clear();

        List<Reef> reefs = getReefs();
        List<Position> intermediatePositions = subdiviseRoute(ship.getPosition(), predictFinalPosition());

        for (Reef reef : reefs) {
            for (Position nextPosition : intermediatePositions) {

                Collision collision = new Collision(reef.getShape(), reef.getPosition(), nextPosition);
                if (collision.collideWithReef()) {
                    res = true;
                    problematicReefs.add(reef);
                }
            }
        }
        return res;
    }

    public boolean checkFutureCollision(){
        boolean res = false;

        List<Reef> reefs = getReefs();
        List<Position> intermediatePositions = subdiviseRoute(predictFinalPosition(), predictFinalNextPosition(predictFinalPosition()));

        for (Reef reef : reefs) {
            for (Position nextPosition : intermediatePositions) {

                Collision collision = new Collision(reef.getShape(), reef.getPosition(), nextPosition);
                if (collision.collideWithReef()) {
                    res = true;
                }
            }
        }
        return res;
    }

    public boolean checkFutureFutureCollision(){
        boolean res = false;

        List<Reef> reefs = getReefs();
        List<Position> intermediatePositions = subdiviseRoute(predictFinalNextPosition(predictFinalPosition()), predictFinalNextPosition(predictFinalNextPosition(predictFinalPosition()))  );

        for (Reef reef : reefs) {
            for (Position nextPosition : intermediatePositions) {

                Collision collision = new Collision(reef.getShape(), reef.getPosition(), nextPosition);
                if (collision.collideWithReef()) {
                    res = true;
                }
            }
        }
        return res;
    }

    public Reef getFirstReef(){
        if(problematicReefs.isEmpty())
            return null;
        return problematicReefs.get(0);
    }


    /**
     * Méthode renvoyant la tranche dans laquelle se situe l'angle souhaité
     */
    public int findClosestPossibleAngle(int oarsNb, boolean canUseRuddder, double orientation) {
        double angle;
        angle = orientation;

        double step = Math.PI / (2 * oarsNb);
        int res = 0;
        for (int k = 0; k < 2 * oarsNb; k++) {

            if (k * step - Math.PI / 2 <= angle && angle <= (k + 1) * step - Math.PI / 2)

                res = k;
        }
        if (orientation> Math.PI / 2 && orientation <= Math.PI)
            return oarsNb;
        if (orientation < -Math.PI / 2 && orientation > -Math.PI)
            return 0;

        if (res == 0)
            return 0;
        if (res == 2 * oarsNb - 1)
            return oarsNb;
        else
            return (res + 1) / 2;
    }


    public double getAngleToEndOfReef(Reef reef) { //TODO supprimer ..get()
        return Math.asin(reef.getShape().getShapeRadius() / ship.distanceTo(reef.getPosition()));

    }

    /**
     * Renvoie l'angle entre l'orientation du bateau et le centre du récif
-     * @return
     */
    public double getAngleToCenterOfReef(Reef reef) { //TODO supprimer les .get().get()
        double angle = ship.getPosition().getOrientation();


        double x = (reef.getPosition().getX() - ship.getPosition().getX());
        double y = (reef.getPosition().getY() - ship.getPosition().getY());
        if (x == 0 && y == 0) {
            return 0;

        }
        if (x == 0) {
            if (y > 0)
                angle = Math.PI / 2;
            if (y < 0)
                angle = -Math.PI / 2;
        } else {
            angle = shortestAngle(Math.atan(y / x));
        }
        return adjustAngle(angle, ship.getPosition(), reef.getPosition());

    }



    public double adjustAngle(double angle, Position startPosition, Position finishPosition) { //TODO supprimer ..get()
        if (finishPosition.getX() < startPosition.getX() && finishPosition.getY() <= startPosition.getY()) {
            angle -= Math.PI;
            angle -= ship.getPosition().getOrientation();
        }
        if (finishPosition.getX() < startPosition.getX() && finishPosition.getY() > startPosition.getY()) {
            angle += Math.PI - ship.getPosition().getOrientation();
        }
        if (finishPosition.getX() >= startPosition.getX() && finishPosition.getY() < startPosition.getY()) {
            angle -= ship.getPosition().getOrientation();
        }
        if (finishPosition.getX() >= startPosition.getX() && finishPosition.getY() >= startPosition.getY()) {
            angle -= ship.getPosition().getOrientation();
        }
        return angle;
    }

    public double shortestAngle(double angle){
        if(angle>Math.PI)
            return angle - (2*Math.PI);
        if(angle< -Math.PI)
            return angle + (2*Math.PI);
        return angle;
    }



    /**
     * Subdivise le trajet en une liste de Positions
     * @param position du bateau
     * @param predictFinalPosition la position prévue
     * @return liste de positions
     */
    public List<Position> subdiviseRoute(Position position, Position predictFinalPosition) { //TODO à refactorer
        List<Position> resPositions = new ArrayList<>();
        double xStart = position.getX();
        double yStart = position.getY();
        double xFinish = predictFinalPosition.getX();
        double yFinish = predictFinalPosition.getY();

        double xDiff = Math.abs(xFinish - xStart);
        double yDiff = Math.abs(yFinish - yStart);

        resPositions.add(position);
        int step = 200;

        if (xStart < xFinish && yStart < yFinish) {
            for (int k = 1; k < step; k++) {
                resPositions.add(new Position(xStart + (double)1 / step * k * xDiff, yStart + (double)1 / step * k * yDiff, position.getOrientation()));
            }
        }

        if (xStart > xFinish && yStart < yFinish) {
            for (int k = 1; k < 200; k++) {
                resPositions.add(new Position(xStart - (double)1 / step * k * xDiff, yStart + (double)1 / step * k * yDiff, position.getOrientation()));
            }
        }

        if (xStart > xFinish && yStart > yFinish) {
            for (int k = 1; k < 200; k++) {
                resPositions.add(new Position(xStart - (double)1 / step * k * xDiff, yStart - (double)1 / step * k * yDiff, position.getOrientation()));
            }
        }

        if (xStart < xFinish && yStart > yFinish) {
            for (int k = 1; k < 200; k++) {
                resPositions.add(new Position(xStart + (double)1 / step * k * xDiff, yStart - (double)1 / step * k * yDiff, position.getOrientation()));
            }
        }

        if (xStart == xFinish && yStart > yFinish) {
            for (int k = 1; k < 200; k++) {
                resPositions.add(new Position(xStart, yStart - (double)1 / step * k * yDiff, position.getOrientation()));
            }
        }
        if (xStart == xFinish && yStart < yFinish) {
            for (int k = 1; k < 200; k++) {
                resPositions.add(new Position(xStart, yStart + (double)1 / step * k * yDiff, position.getOrientation()));
            }
        }

        if (xStart > xFinish && yStart == yFinish) {
            for (int k = 1; k < 200; k++) {
                resPositions.add(new Position(xStart - (double)1 / step * k * xDiff, yStart, position.getOrientation()));
            }
        }
        if (xStart < xFinish && yStart == yFinish) {
            for (int k = 1; k < 200; k++) {
                resPositions.add(new Position(xStart + (double)1 / step * k * xDiff, yStart, position.getOrientation()));
            }
        }
        resPositions.add(predictFinalPosition);
        return resPositions;
    }


    public List<Reef> getReefs() {
        List<Reef> reefs = new ArrayList<>();
        for (VisibleEntitie visibleEntitie : visibleEntities)
            if(visibleEntitie.getType().equals(VisibleEntityType.reef))
                reefs.add((Reef)visibleEntitie);
        return reefs;
    }

    /**
     * Méthode donnant la prochaine position du bateau
     * @return la nouvelle position
     */
    public Position predictFinalPosition() {

        double vitesse = 165 * (double)(leftSailors.size() + rightSailors.size()) / oarsNb;
        vitesse += calculWind();

        double x = vitesse * Math.cos(ship.getPosition().getOrientation()) + ship.getPosition().getX();
        double y = vitesse * Math.sin(ship.getPosition().getOrientation()) + ship.getPosition().getY();

        Stream stream =getCurrentOn();
        if(stream !=null){
            if(stream.getPosition().getOrientation()==ship.getPosition().getOrientation()){
                x+= stream.getStrength()*Math.cos(Math.abs(ship.getPosition().getOrientation()-stream.getPosition().getOrientation()));
                y+= stream.getStrength()*Math.sin(Math.abs(ship.getPosition().getOrientation()-stream.getPosition().getOrientation()));
            }
        }
        return new Position(x, y, angleCalcul());
    }

    public Position predictFinalNextPosition(Position position) {

        double vitesse = 165 * (double)(leftSailors.size() + rightSailors.size()) / oarsNb;
        vitesse += calculWind();

        double x = vitesse * Math.cos(position.getOrientation()) + position.getX();
        double y = vitesse * Math.sin(position.getOrientation()) + position.getY();

        Stream stream =getCurrentOn();
        if(stream !=null){
            if(stream.getPosition().getOrientation()==position.getOrientation()){
                x+= stream.getStrength()*Math.cos(Math.abs(position.getOrientation()-stream.getPosition().getOrientation()));
                y+= stream.getStrength()*Math.sin(Math.abs(position.getOrientation()-stream.getPosition().getOrientation()));
            }
        }
        return new Position(x, y, angleCalcul());
    }



    /**
     * Méthode calculant la prochaine orientation du bateau
     * @return la nouvelle orientation
     */
    public double angleCalcul() {
        double currentOrientation = ship.getPosition().getOrientation();
        double gap = Math.PI / (oarsNb);
        int balanced = rightSailors.size() - leftSailors.size();
        currentOrientation += (balanced * gap);
        currentOrientation += rotation;
        if (currentOrientation < -Math.PI) {
            currentOrientation = 2 * Math.PI + currentOrientation;
        }
        if (currentOrientation > Math.PI) {
            currentOrientation = -2 * Math.PI + currentOrientation;
        }
        return currentOrientation;
    }


    public double calculWind() {
        double value = 0;
        if (!sailArrayList.isEmpty()) {
            value = ((double) nbSailUsed / sailArrayList.size()) * wind.getStrength() *
                    Math.cos(Math.abs(wind.getOrientation()) - Math.abs(ship.getPosition().getOrientation()));
        }
        return value;
    }


    public Stream getCurrentOn(){
        for (VisibleEntitie entity: visibleEntities) {
            Collision collision = new Collision(entity.getShape(),entity.getPosition(),ship.getPosition());
            if(entity.getType()== VisibleEntityType.stream &&collision.collide()){
                return (Stream) entity;
            }
        }
        return null;
    }


}
