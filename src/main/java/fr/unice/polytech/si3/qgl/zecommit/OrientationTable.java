package fr.unice.polytech.si3.qgl.zecommit;


import java.util.ArrayList;
import java.util.List;


/**
 * @author Vincent
 */

public class OrientationTable {

    private ArrayList<Double> angleTable;
    private ArrayList<ArrayList<Compo>> formationTable;
    private int min;


    public OrientationTable(int oars, int sailors) {
        this.angleTable = generateAngleTable(oars, sailors);
        this.formationTable = generateCompo(min);
    }


    /**
     * Génére les orientations possibles en fonction du nombre de rames renseignées
     * @param oarsNb
     * @return
     */
    ArrayList<Double> generateAngleTable(int oarsNb, int sailorsNb) {
        ArrayList<Double> myAngleTable = new ArrayList();

        double borneSup =  Math.PI/2;
        double borneInf = -borneSup;
        int efficentOars;
        int efficientSailors;

        // On prend un nombre pair de rame
        if (oarsNb % 2 != 0) {
            efficentOars = oarsNb-1;
        }
        else {
            efficentOars = oarsNb;
        }

        // On prend un nombre pair de marin
        if (oarsNb % 2 != 0) {
            efficientSailors = sailorsNb-1;
        }
        else {
            efficientSailors = sailorsNb;
        }

        this.min = Math.min(efficentOars, efficientSailors);

        // Pas entre chaque angle
        double step = Math.PI/min;

        // borne inf de l'interval
        myAngleTable.add(borneInf);

        // On remplit les valeurs inf à 0
        for (int i = 1; i < (min/2) ; i++) {
            myAngleTable.add(borneInf + i*step);
        }
        // 0 au milieu de l'interval
        myAngleTable.add(0.0);

        // On remplit les valeurs sup à 0
        for (int i = 1; i < (min/2) ; i++) {
            myAngleTable.add(i*step);
        }

        // borne supp de l'interval
        myAngleTable.add(borneSup);

        return myAngleTable;
    }

    ArrayList<ArrayList<Compo>> generateCompo(int min) {
        ArrayList myCompoTable = new ArrayList();

        int oars;

        // On prend un nombre pair de rame
        if (min % 2 != 0) {
            oars = min-1;
        }
        else {
            oars = min;
        }

        // Compo pour Orientation negative
        for (int i = 0; i < (oars/2); i++) {
            myCompoTable.add(compoSpeBeforeZ(i, oars));
        }

        // Compo pour avancer tout droit
        myCompoTable.add(compoSpeForZ(oars));

        //Compo pour Orientation positive
        for (int i = (oars/2)-1; i >= 0; i--) {
            myCompoTable.add(compoSpeAfterZ(i, oars));
        }

        return myCompoTable;
    }

    /**
     * Fonction utilitaire pour generateFormation liste des compos pour un certain angle negatif
     * @param i
     * @param oars
     * @return
     */
    ArrayList<Compo> compoSpeBeforeZ(int i, int oars) {
        ArrayList tempoTable = new ArrayList();
        int ecart = (oars/2)-i;

        for (int k = 0; k+ecart <= (oars/2); k++) {
            tempoTable.add(new Compo(k+ecart, k));
        }

        return tempoTable;
    }

    /**
     * Fonction utilitaire pour generateFormation liste des compos pour un certain angle positif
     * @param i
     * @param oars
     * @return
     */
    ArrayList<Compo> compoSpeAfterZ(int i, int oars) {
        ArrayList<Compo> tempoTable = new ArrayList();
        int ecart = (oars/2)-i;

        for (int k = 0; k+ecart <= (oars/2); k++) {
            tempoTable.add(new Compo(k, k+ecart));
        }

        return tempoTable;
    }

    /**
     * Fonction utilitaire pour generateFormation liste des compos pour aller tout droit
     * @param oars
     * @return
     */
    ArrayList<Compo>  compoSpeForZ(int oars) {
        ArrayList tempoTable = new ArrayList();
        for (int i = 1; i <= (oars/2); i++) {
            tempoTable.add(new Compo(i,i));
        }
        return tempoTable;
    }

    public List<Double> getAngleTable() {
        return this.angleTable;
    }

    public List<ArrayList<Compo>> getFormationTable() {
        return formationTable;
    }

    public Compo getCompo(int indexAngle, int indexCompo) {
        return formationTable.get(indexAngle).get(indexCompo);
    }

    public Compo getLastCompo(int indexAngle) {
        return formationTable.get(indexAngle).get(formationTable.get(indexAngle).size()-1);
    }

    @Override
    public String toString() {
        return "OrientationTable{" +
                "angleTable=" + angleTable +
                ", formationTable=" + formationTable +
                '}';
    }
}
