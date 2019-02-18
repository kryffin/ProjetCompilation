package compilateurYal.tds;

import compilateurYal.CompteurRegions;
import compilateurYal.GestionnaireTailleZoneVariable;
import compilateurYal.exceptions.AnalyseSemantiqueException;
import compilateurYal.tds.entrees.Entree;
import compilateurYal.tds.symboles.Symbole;

public class TableDesSymboles {

    private TableLocale tableCourante;
    private TableLocale tableRacine;

    /**
     * instance du singleton
     */
    private static TableDesSymboles instance = new TableDesSymboles();

    /**
     * Constructeur vide
     */
    private TableDesSymboles () {
        tableRacine = new TableLocale(CompteurRegions.getInstance().getCompteur());
        CompteurRegions.getInstance().incrementerCompteur();
        tableCourante = tableRacine;
    }

    /**
     * @return l'instance du singleton
     */
    public static TableDesSymboles getInstance () {
        return instance;
    }

    /**
     * Méthode ajoutant un élément dans la table courante
     * @param e Entrée
     * @param s Symbole
     * @param n numéro de la ligne dans le fichier .yal pour lancer l'erreur
     */
    public void ajouter (Entree e, Symbole s, int n) {
        //ajout de l'Entree dans la table courante
        tableCourante.ajouter(e, s, n);
    }

    /**
     * Méthode identifiant un élément dans la table courante
     * @param e Entrée à retrouver
     * @param n numéro de la ligne dans le fichier .yal pour lancer l'erreur
     * @return le symbole correspondant à l'Entrée dans la table courante
     */
    public Symbole identifier (Entree e, int n) throws AnalyseSemantiqueException {
        //identification dans la table courante
        return tableCourante.identifier(e, n);
    }

    public void entrerRegion () {
        TableLocale nouvelleTable = new TableLocale(CompteurRegions.getInstance().getCompteur());
        CompteurRegions.getInstance().incrementerCompteur();
        nouvelleTable.setTablePere(tableCourante);
        tableCourante.addFille(nouvelleTable);
        tableCourante = nouvelleTable;
    }

    public void entrerRegionVerifier () {
        CompteurRegions.getInstance().incrementerCompteur();
        for (TableLocale tl : getTableCourante().getTablesFilles()) {
            if (tl.getNRegion() == CompteurRegions.getInstance().getCompteur()) {
                tableCourante = tl;
                break;
            }
        }
    }

    public void sortieRegion () {
        tableCourante = tableCourante.getTablePere();
    }

    public void sortieRegionVerifier () {
        sortieRegion();
    }

    public TableLocale getTableCourante () {
        return tableCourante;
    }

    /**
     * @return taille de la zone variable à allouer pour la prochaine variable (0, -4, -8, etc...)
     */
    public int getTailleZoneVariable () {
        return GestionnaireTailleZoneVariable.getInstance().getTailleZoneVariable();
    }

}
