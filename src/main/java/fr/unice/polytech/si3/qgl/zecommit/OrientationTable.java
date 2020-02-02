package fr.unice.polytech.si3.qgl.zecommit;


import fr.unice.polytech.si3.qgl.zecommit.shape.Compo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Vincent
 */

public class OrientationTable {

    private ArrayList<Double> angleTable;
    private ArrayList<ArrayList<Compo>> formationTable;


    public OrientationTable(int oars) {
        this.angleTable = generateAngleTable(oars);
        this.formationTable = generateFormation(oars);
    }


    /**
     * Génére les orientations possibles en fonction du nombre de rames renseignées
     * @param oarsNb
     * @return
     */
    ArrayList<Double> generateAngleTable(int oarsNb) {
        ArrayList<Double> angleTable = new ArrayList();

        double borneSup =  Math.PI/2;
        double borneInf = -borneSup;
        int efficentOars;

        // On prend un nombre pair de rame
        if (oarsNb % 2 != 0) {
            efficentOars = oarsNb-1;
        }
        else {
            efficentOars = oarsNb;
        }

        // Pas entre chaque angle
        double pas = Math.PI/efficentOars;

        // borne inf de l'interval
        angleTable.add(borneInf);

        // On remplit les valeurs inf à 0
        for (int i = 1; i < (efficentOars/2) ; i++) {
            angleTable.add(borneInf + i*pas);
        }
        // 0 au milieu de l'interval
        angleTable.add(0.0);

        // On remplit les valeurs sup à 0
        for (int i = 1; i < (efficentOars/2) ; i++) {
            angleTable.add(i*pas);
        }

        // borne supp de l'interval
        angleTable.add(borneSup);

        return angleTable;
    }

    ArrayList<ArrayList<Compo>> generateFormation(int oarsNb) {
        ArrayList formationTable = new ArrayList();

        int oars;

        // On prend un nombre pair de rame
        if (oarsNb % 2 != 0) {
            oars = oarsNb-1;
        }
        else {
            oars = oarsNb;
        }

        // Compo pour Orientation negative
        for (int i = 0; i < (oars/2); i++) {
            formationTable.add(compoSpeBeforeZ(i, oars));
        }

        // Compo pour avancer tout droit
        formationTable.add(compoSpeForZ(oars));

        //Compo pour Orientation positive
        for (int i = (oars/2)-1; i >= 0; i--) {
            formationTable.add(compoSpeAfterZ(i, oars));
        }

        return formationTable;
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
        for (int i = 0; i <= (oars/2); i++) {
            tempoTable.add(new Compo(i,i));
        }
        return tempoTable;
    }

    public ArrayList<Double> getAngleTable() {
        return this.angleTable;
    }

    public ArrayList<ArrayList<Compo>> getFormationTable() {
        return formationTable;
    }

    public Compo getCompo(int indexAngle, int indexCompo) {
        return formationTable.get(indexAngle).get(indexCompo);
    }

}
