package fr.unice.polytech.si3.qgl.zecommit.maths;

import fr.unice.polytech.si3.qgl.zecommit.Logs;
import fr.unice.polytech.si3.qgl.zecommit.boat.Position;
import fr.unice.polytech.si3.qgl.zecommit.boat.Ship;
import fr.unice.polytech.si3.qgl.zecommit.entite.Sail;
import fr.unice.polytech.si3.qgl.zecommit.other.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Classe permettant de prévoir la prochaine position du bateau
 * @author Nathan
 */
public class Predictions {
    private int leftSailorsSize;
    private int rightSailorsSize;
    private int oarsNb;
    private Ship ship;
    private List<VisibleEntitie> visibleEntities;
    private double rotation;
    private List<Sail> sailArrayList;
    private Wind wind;
    private int nbSailUsed;


    public Predictions(int leftSailorsSize, int rightSailorsSize, Ship ship, List<VisibleEntitie> visibleEntities, double rotation, Wind wind, boolean upsail) {
        this.leftSailorsSize = leftSailorsSize;
        this.rightSailorsSize = rightSailorsSize;
        this.oarsNb = ship.getDeckOars().size();
        this.ship = ship;
        this.visibleEntities = visibleEntities;
        this.rotation = rotation;
        this.sailArrayList = ship.getDeckSails();
        this.wind = wind;
        this.nbSailUsed = upsail ? 1 : 0;
    }

    /**
     * Méthode vérifiant les collisions avec tous les récifs présents
     *
     * @return true en cas de collision
     */
    public boolean checkCollision() {
        boolean res = false;
        List<Reef> reefs = getReefs();
        List<Position> intermediatePositions = new ArrayList<Position>();
        Logs.add(ship.getPosition() + "\n");
        Logs.add("");

        intermediatePositions.add(ship.getPosition());

        for (int i = 1; i < 100; i++) {
            intermediatePositions.add(predictFinalPosition(intermediatePositions.get(intermediatePositions.size()-1),i));
        }

        for (Reef reef : reefs) {
            for (Position nextPosition : intermediatePositions) {

                Collision collision = new Collision(reef.getShape(), reef.getPosition(), nextPosition);
                if (collision.collide()) {
                    res = true;
                }
            }
        }
        return res;
    }

    public Reef  getFirstReef(){
        return getSortedReef().get(0);
    }

    public List<Reef>  getSortedReef(){
        List<Reef> res = getReefs();
        res.sort(Comparator.comparingDouble(reef->ship.distanceToforReef(reef)));
        return res;
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
        if (orientation > Math.PI / 2 && orientation <= Math.PI)
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


    public double getAngleToEndOfReef(Reef reef, OrientationTable orientationTable) { //TODO supprimer ..get()
        if(reef.getShape().getShapeRadius() < ship.distanceTo(reef.getPosition()))
            return Math.asin(reef.getShape().getShapeRadius() / ship.distanceTo(reef.getPosition()));
        else {
            return (orientationTable.getAngleTable().size()-1 )/(double)2;
        }

    }

    /**
     * Renvoie l'angle entre l'orientation du bateau et le centre du récif
     * @return
     */
    public double getAngleToCenterOfReef(Reef reef) {
        double angle = ship.getPosition().getOrientation();
        Position shipPosition = ship.getPosition();
        Position reefPosition = reef.getPosition();
        double x = (reefPosition.getX() - shipPosition.getX());
        double y = (reefPosition.getY() - shipPosition.getY());
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
        return shortestAngle(adjustAngle(angle, shipPosition, reefPosition));

    }


    public double adjustAngle(double angle, Position startPosition, Position finishPosition) {
        Position shipPosition = ship.getPosition();
        if (finishPosition.getX() < startPosition.getX() && finishPosition.getY() <= startPosition.getY()) {
            angle -= Math.PI;
            angle -= shipPosition.getOrientation();
        }
        if (finishPosition.getX() < startPosition.getX() && finishPosition.getY() > startPosition.getY()) {
            angle += Math.PI - shipPosition.getOrientation();
        }
        if (finishPosition.getX() >= startPosition.getX() && finishPosition.getY() < startPosition.getY()) {
            angle -= shipPosition.getOrientation();
        }
        if (finishPosition.getX() >= startPosition.getX() && finishPosition.getY() >= startPosition.getY()) {
            angle -= shipPosition.getOrientation();
        }
        return angle;
    }

    public double shortestAngle(double angle) {
        if (angle > Math.PI)
            return angle - (2 * Math.PI);
        if (angle < -Math.PI)
            return angle + (2 * Math.PI);
        return angle;
    }


    public List<Reef> getReefs() {
        List<Reef> reefs = new ArrayList<>();
        for (VisibleEntitie visibleEntitie : visibleEntities)
            if (visibleEntitie.getType().equals(VisibleEntityType.reef))
                reefs.add((Reef) visibleEntitie);
        return reefs;
    }

    /**
     * Méthode donnant la prochaine position du bateau
     *
     * @return la nouvelle position
     */
    public Position predictFinalPosition(Position position, int i) {

        double vitesse = ((double) 165 / i)* (double) (leftSailorsSize + rightSailorsSize) / oarsNb;
        vitesse += calculWind(position);

        double x = vitesse * Math.cos(position.getOrientation()) + position.getX();
        double y = vitesse * Math.sin(position.getOrientation()) + position.getY();

        Stream stream = getCurrentOn(position);
        if (stream != null) {
            x += ((double) stream.getStrength() / i) * Math.cos(Math.abs(position.getOrientation() - (stream.getPosition().getOrientation() + stream.getShape().getShapeOrientation())));
            y += ((double) stream.getStrength() / i) * Math.sin(Math.abs(position.getOrientation() - (stream.getPosition().getOrientation() + stream.getShape().getShapeOrientation())));

        }

        return new Position(x, y, angleCalcul(i));
    }


    /**
     * Méthode calculant la prochaine orientation du bateau
     *
     * @return la nouvelle orientation
     */
    public double angleCalcul(int i) {
        double currentOrientation = ship.getPosition().getOrientation();
        double gap = Math.PI / (oarsNb);
        int balanced = rightSailorsSize - leftSailorsSize;
        currentOrientation += (balanced * gap / i);
        currentOrientation += rotation / i;
        if (currentOrientation < -Math.PI) {
            currentOrientation = 2 * Math.PI + currentOrientation;
        }
        if (currentOrientation > Math.PI) {
            currentOrientation = -2 * Math.PI + currentOrientation;
        }
        return currentOrientation;
    }


    public double calculWind(Position position) {
        double value = 0;
        if (!sailArrayList.isEmpty()) {
            value = ((double) nbSailUsed / sailArrayList.size()) * wind.getStrength() *
                    Math.cos(Math.abs(wind.getOrientation()) - Math.abs(position.getOrientation()));
        }
        return value;
    }


    public Stream getCurrentOn(Position position) {
        for (VisibleEntitie entity : visibleEntities) {
            Collision collision = new Collision(entity.getShape(), entity.getPosition(), position);
            if (entity.getType() == VisibleEntityType.stream && collision.collide()) {
                return (Stream) entity;
            }
        }
        return null;
    }

}
