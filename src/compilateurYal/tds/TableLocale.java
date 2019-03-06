package compilateurYal.tds;

import compilateurYal.exceptions.AnalyseSemantiqueException;
import compilateurYal.tds.entrees.Entree;
import compilateurYal.tds.symboles.Symbole;
import java.util.ArrayList;
import java.util.HashMap;

public class TableLocale {

    /**
     * numéro de région de la table
     */
    private int nRegion;

    /**
     * table parente
     */
    private TableLocale tableMere;

    /**
     * liste des tables enfants
     */
    private ArrayList<TableLocale> tablesFilles;

    /**
     * nombre de variables de la table
     */
    private int nVariables;

    /**
     * HashMap représentant la table des symboles : Entree -> Symbole
     */
    private HashMap<Entree, Symbole> tds;

    /**
     * Constructeur par numéro de région
     * @param nRegion numéro de région
     */
    public TableLocale (int nRegion) {
        this.nRegion = nRegion;
        tableMere = null;
        tablesFilles = new ArrayList<>();
        tds = new HashMap<>();
        nVariables = 0;
    }

    /**
     * Méthode ajoutant un élément dans la table de hachage ou lance une erreur si l'élément est déjà dans la table
     * @param e Entrée
     * @param s Symbole
     * @param n numéro de la ligne dans le fichier .yal pour lancer l'erreur
     * @throws AnalyseSemantiqueException en cas de double déclaration d'une variable
     */
    public void ajouter (Entree e, Symbole s, int n) throws AnalyseSemantiqueException {
        //si la table contient la clé il y a donc une double déclaration de cette Entrée, on lance donc une erreur, sinon on l'ajoute
        if (tds.containsKey(e)) {
            new AnalyseSemantiqueException(n, "double déclaration de la variable " + e.getNom());
        } else {
            tds.put(e, s);
            nVariables++;
        }
    }

    /**
     * Méthode identifiant un élément de la table de hachage ou celle parent et le renvoie s'il existe, lance une erreur sinon
     * @param e Entrée à retrouver
     * @param n numéro de la ligne dans le fichier .yal pour lancer l'erreur
     * @return le symbole correspondant à l'Entrée dans la table
     * @throws AnalyseSemantiqueException en cas de non déclaration de la variable qu'on veut récupérer
     */
    public Symbole identifier (Entree e, int n) throws AnalyseSemantiqueException {
        Symbole s = tds.get(e);

        //si le symbole est null on ne l'a donc pas retrouvé dans la table, on lance donc une erreur, sinon on renvoie le symbole
        if (s == null) {
            if (tableMere == null) {
                new AnalyseSemantiqueException(n, "variable " + e.getNom() + " non déclarée" + nRegion);
            }
            return tableMere.identifier(e, n);
        } else {
            return s;
        }
    }

    /**
     * @return numéro de région
     */
    public int getNRegion () {
        return nRegion;
    }

    /**
     * Setteur sur la table parente
     * @param tl table parente
     */
    public void setTablePere (TableLocale tl) {
        tableMere = tl;
    }

    /**
     * @return table parente
     */
    public TableLocale getTablePere () {
        return tableMere;
    }

    /**
     * Ajoute une table enfant
     * @param tl table fille
     */
    public void addFille (TableLocale tl) {
        tablesFilles.add(tl);
    }

    /**
     * @return liste des tables enfants
     */
    public ArrayList<TableLocale> getTablesFilles () {
        return tablesFilles;
    }

    /**
     * @return nombre de variables de la table
     */
    public int getNVariables () {
        return nVariables;
    }

}
