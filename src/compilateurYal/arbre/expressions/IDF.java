package compilateurYal.arbre.expressions;

import compilateurYal.Yal;
import compilateurYal.tds.TableDesSymboles;
import compilateurYal.tds.TableLocale;
import compilateurYal.tds.entrees.EntreeFonction;
import compilateurYal.tds.entrees.EntreeVariable;
import compilateurYal.tds.symboles.Symbole;
import compilateurYal.tds.symboles.SymboleTableau;
import compilateurYal.tds.symboles.SymboleVariable;

public class IDF extends Expression {

    /**
     * nom de la variable
     */
    private String nom;

    /**
     * déplacement dans la pile de la variable
     */
    private int deplacement;

    /**
     * numéro de région de la variable
     */
    private int nRegion;

    /**
     * vrai si l'identifiant est une variable, faux si c'est une variable représentant une fonction
     */
    private boolean variable;

    private boolean tableau;

    /**
     * nombre de paramètre si l'idf défini une fonction (pour la surcharge de fonctions)
     */
    private int nbParametres;

    /**
     * Construction par nom et numéro de ligne
     * @param nom nom de la variable
     * @param n ligne
     */
    public IDF (String nom, int n) {
        super(n);
        this.nom = nom;
        nRegion = TableDesSymboles.getInstance().getTableCourante().getNRegion();
        variable = true;
    }

    /**
     * Construction par nom, type d'identifiant et numéro de ligne
     * @param nom nom de la variable
     * @param variable vrai si variable, faux si fonction
     * @param n ligne
     */
    public IDF (String nom, boolean variable, int n) {
        super(n);
        this.nom = nom;
        nRegion = TableDesSymboles.getInstance().getTableCourante().getNRegion();
        this.variable = variable;
    }

    /**
     * Construction par nom, type d'identifiant, tableau ou non et numéro de ligne
     * @param nom nom de la variable
     * @param variable vrai si variable, faux si fonction
     * @param tableau vrai si tableau, faux sinon
     * @param n ligne
     */
    public IDF (String nom, boolean variable, boolean tableau, int n) {
        super(n);
        this.nom = nom;
        nRegion = TableDesSymboles.getInstance().getTableCourante().getNRegion();
        this.variable = variable;
        this.tableau = tableau;
    }

    /**
     * @return nom de la variable
     */
    public String getNom () {
        return nom;
    }

    /**
     * @return déplacement dans la pile de la variable
     */
    public int getDeplacement () {
        return deplacement;
    }

    /**
     * Setteur sur ne nombre de paramètres
     * @param nb nombre de paramètres
     */
    public void setNbParametres (int nb) {
        nbParametres = nb;
    }

    /**
     * vérifie si la variable est bien dans la table des symboles et met à jour le déplacement ainsi que le numéro de région
     */
    @Override
    public void verifier() {
        //récupération du symbole et renseignement du déplacement et type de la variable si elle existe bien
        Symbole s;

        if (variable) {
            s = TableDesSymboles.getInstance().identifier(new EntreeVariable(nom), noLigne);
        } else {
            s = TableDesSymboles.getInstance().identifier(new EntreeFonction(nom, nbParametres), noLigne);
        }

        if (Yal.exception) {
            //en cas d'erreur précédente on ne vérifie pas plus loin la variable
            return;
        }

        if (variable && !tableau) {
            deplacement = ((SymboleVariable)s).getDeplacement();
        } else if (variable && tableau) {
            deplacement = ((SymboleTableau)s).getDeplacement();
        }
        nRegion = s.getNRegion();
    }

    /**
     * @return l'instruction mips de chargement de la variable dans $v0
     */
    @Override
    public String toMIPS() {
        TableLocale tl = TableDesSymboles.getInstance().getTableCourante();
        if (tl.getNRegion() == nRegion) {
            return  "    lw $v0, " + deplacement + "($s7)\n";
        }

        StringBuilder sb = new StringBuilder();
        sb.append(  "                #récupération de la variable " + getNom() + tl.getNRegion() + "\n" +
                    "    move $t8, $s7\n");
        while (tl.getNRegion() != nRegion) {
            sb.append("    lw $t8, 8($t8)\n");
            tl = tl.getTablePere();
        }
        sb.append("    lw $v0, " + deplacement + "($t8)\n");
        return sb.toString();
    }

    @Override
    public String toString() {
        return nom;
    }

}
