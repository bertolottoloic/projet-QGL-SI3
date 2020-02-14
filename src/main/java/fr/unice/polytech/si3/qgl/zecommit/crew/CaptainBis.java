package fr.unice.polytech.si3.qgl.zecommit.crew;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.AbstractMap.SimpleEntry;

import fr.unice.polytech.si3.qgl.zecommit.Game;
import fr.unice.polytech.si3.qgl.zecommit.Logs;
import fr.unice.polytech.si3.qgl.zecommit.boat.Deck;
import fr.unice.polytech.si3.qgl.zecommit.boat.Ship;
import fr.unice.polytech.si3.qgl.zecommit.entite.Entity;
import fr.unice.polytech.si3.qgl.zecommit.entite.Oar;
import fr.unice.polytech.si3.qgl.zecommit.entite.Sail;
import fr.unice.polytech.si3.qgl.zecommit.goal.Goal;
import fr.unice.polytech.si3.qgl.zecommit.goal.Regatta;
import fr.unice.polytech.si3.qgl.zecommit.strategy.Compo;
import fr.unice.polytech.si3.qgl.zecommit.strategy.OrientationTable;
import fr.unice.polytech.si3.qgl.zecommit.strategy.Road;

public class CaptainBis implements CaptainInterface {

    private Ship ship;
    private Deck deck;
    private Regatta goal;

    private List<Sailor> rightSailorList;
    private List<Sailor> leftSailorList;

    public CaptainBis(Game game) {
        this.ship = game.getShip();
        this.deck = ship.getDeck();
        this.goal= (Regatta) game.getGoal();

        this.leftSailorList=new ArrayList<>();
        this.rightSailorList=new ArrayList<>();
    }

    @Override
    public void attributeEntitiesToSailors() {
        List<Sailor> sailors = deck.getSailors();
        sailors.forEach(s -> s.reinitializeEntity());
        List<Sailor> sailorTmp = new ArrayList<>(sailors);
        List<Entity> oars = new ArrayList<>(deck.getOars());
        sailorTmp.sort(Comparator.comparingInt(a -> a.distanceToNearestEntity(oars)));
        Sailor sailor;
        if (sailorTmp.size() % 2 != 0 && deck.getRudder() != null) {
            sailor = sailorTmp.remove(sailorTmp.size() - 1);
            sailor.setOnEntity(deck.getRudder());
        }
        for (Sailor tmp : sailorTmp) {
            deck.getOars().sort(Comparator.comparingInt(a -> tmp.distanceToEntity(a)));
            Oar closestOar = deck.getOars().get(0);
            if (!closestOar.hasSailorOn() && tmp.distanceToEntity(closestOar) <= 5 && !tmp.hasEntity()) {
                tmp.setOnEntity(closestOar);
            }
        }
        for (Sailor tmp : sailorTmp) {
            deck.getOars().sort(Comparator.comparingInt(a -> tmp.distanceToEntity(a)));
            for (Oar oar : deck.getOars()) {
                if (!oar.hasSailorOn() && !tmp.hasEntity()) {
                    tmp.setOnEntity(oar);
                }
            }
        }

    }

    @Override
    public List<Sailor> doMoveSailors() {
        if (!deck.sailorsAreOnTheirEntity())
            return deck.getSailors();
        return new ArrayList<Sailor>();
    }

    @Override
    public List<Sailor> doActivateOars() {
        Road road = new Road(ship.getPosition(), goal.getFirstCheckpoint().getPosition());
        int chosenAngle = findClosestPossibleAngle(road.orientationToGoal(),deck.getOars().size());
        return decisionOrientation(road,chosenAngle);
    }

    @Override
    public SimpleEntry<Sailor, Double> doTurn() {
        return null;
    }

    @Override
    public List<Sailor> doLiftSail() {
        return new ArrayList<Sailor>();
    }

    @Override
    public List<Sailor> doLowerSail() {
        return new ArrayList<Sailor>();
    }



    /**
     * Met a jour les informations du capitaine récupérées par le parseurNext
     * @param game
     */
    public void refreshGame(Game game){
        ship=game.getShip();
        this.deck.setOars( ship.getDeck().getOars());
    }


    /**
     * Effectue l'ordre d'activation des marins aux rames et au gouvernail
     *
     * @param compo
     */
    public ArrayList<Sailor> activateSailors(Compo compo, double angle) {
        ArrayList<Sailor> usedSailors= new ArrayList<>();
        // Activation des marins de gauche
        int l = 0;
        while (l < compo.getSailorsLeft()) {
            usedSailors.add(leftSailorList.get(l));
            //toOar(leftSailorList.get(l), (Oar) leftSailorList.get(l).getEntity());
            l++;
        }

        // Activation des marins de droite
        int r = 0;
        while (r < compo.getSailorsRight()) {
            usedSailors.add(rightSailorList.get(l));

            //toOar(rightSailorList.get(r), (Oar) rightSailorList.get(r).getEntity());
            r++;
        }
        return usedSailors;

        // Activation du gouvernail
        //if (deck.getRudder() != null && deck.getRudder().hasSailorOn())
           // toTurn(deck.getRudder().getSailorOn(), deck.getRudder(), angle);
    }

    public ArrayList<Sailor> decisionOrientation(Road road, int chosenAngle){
        OrientationTable orientationTable = new OrientationTable(deck.getOars().size());
        Logs.add(chosenAngle +"");

        boolean isNear = road.yDistanceToGoal() < (165-((Regatta)goal).getFirstCheckpoint().getCircleRadius());
        boolean upSail = upSail();
        int nbSailorsRight = rightSailorList.size();
        int nbSailorsLeft = leftSailorList.size();

        if(road.orientationToGoal()>-Math.PI/4 && road.orientationToGoal()<Math.PI/4){
            chosenAngle = findClosestPossibleAngle(0,deck.getOars().size()); //on donne l'ordre aller tout droit, le gouvernail gère les virages
        }

        //activateSail(upSail, isNear);//Activation de la voile

        if(!isNear){//si le bateau est loin
            return activateSailors(orientationTable.getGoodCompo(orientationTable.getLastCompo(chosenAngle), nbSailorsRight, nbSailorsLeft), road.orientationToGoal());//on choisit la compo permettant d'aller le plus vite
        }
        else
           return activateSailors(orientationTable.getGoodCompo(orientationTable.getCompo(chosenAngle, 0),nbSailorsRight, nbSailorsLeft),road.orientationToGoal());//on choisit la compo permettant d'aller le plus lentement
    }

    /**
     * Méthode indiquant quand activer la voile
     * @return
     */
    public boolean upSail(){
        //TODO condition permettant de lever la voile
        return false;
    }

//////////////////////////////////CALCUL/////////////////////////
    /**
     * Méthode renvoyant la tranche dans laquelle se situe l'angle souhaité
     */
    public int findClosestPossibleAngle(double angleToReach,int oarsNb){
        double step = Math.PI/(2*oarsNb);
        int res = 0;
        for (int k = 0; k<2*oarsNb; k ++){

            if(k*step-Math.PI/2 <= angleToReach && angleToReach <= (k+1)*step-Math.PI/2 )

                res = k;
        }
        if(turnAroundLeft(angleToReach))
            return oarsNb;
        if(turnAroundRight(angleToReach))
            return 0;

        if(res==0)
            return 0;
        if(res==2*oarsNb-1)
            return oarsNb;
        else
            return (res+1)/2;
    }

    /**
     * demi tour gauche ?
     * @return
     */
    public boolean turnAroundLeft(Double angle){
        if(angle > Math.PI/2 && angle <= Math.PI)
            return true;
        return false;
    }


    /**
     * demi tour droite ?
     * @return
     */
    public boolean turnAroundRight(Double angle){
        if(angle < -Math.PI/2 && angle > -Math.PI)
            return true;
        return false;
    }
////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////// GETTER ////////////////////////////////////////////

    public Deck getDeck(){
        return this.deck;
    }

}