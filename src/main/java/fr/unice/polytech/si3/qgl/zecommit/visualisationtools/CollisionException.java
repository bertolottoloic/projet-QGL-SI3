package fr.unice.polytech.si3.qgl.zecommit.visualisationtools;

/**
 * Erreur de collision
 * @author Nathan
 */
public class CollisionException extends Exception {

    public CollisionException() {
        super("Collision ! (Enlever la création de l'exception dans le moteur pour ne pas en tenir compte)");
    }
}
