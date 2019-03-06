package compilateurYal.arbre.expressions;

import compilateurYal.tds.TableDesSymboles;
import compilateurYal.tds.entrees.EntreeFonction;
import compilateurYal.tds.symboles.Symbole;

public class Fonction extends Expression {

    /**
     * identifiant de la fonction
     */
    private IDF idf;

    /**
     * numéro de région de la fonction
     */
    private int nRegion;

    /**
     * Constructeur par numéro de ligne
     * @param n ligne
     */
    public Fonction(IDF idf, int n) {
        super(n);
        this.idf = idf;
    }

    /**
     * vérifie la fonction et récupère le numéro de région
     */
    @Override
    public void verifier() {
        Symbole s = TableDesSymboles.getInstance().identifier(new EntreeFonction(idf.getNom()), noLigne);
        nRegion = s.getNRegion();
    }

    /**
     * @return instructions MIPS appelant la fonction cible
     */
    @Override
    public String toMIPS() {
        return  "                #appel de la fonction " + idf.getNom() + ", région : " + nRegion + "\n" +
                "                #saut à l'étiquette de la fonction et sauvegarde de ra\n" +
                "    jal " + idf.getNom() + nRegion + "\n";
    }

    @Override
    public String toString() {
        return idf.getNom() + "()";
    }
}
