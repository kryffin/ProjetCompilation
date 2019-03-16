package compilateurYal.arbre.expressions;

import compilateurYal.arbre.ArbreAbstrait;
import compilateurYal.arbre.parametres.BlocDeParametresEffectifs;
import compilateurYal.exceptions.AnalyseSemantiqueException;
import compilateurYal.tds.TableDesSymboles;
import compilateurYal.tds.entrees.EntreeFonction;
import compilateurYal.tds.symboles.Symbole;
import compilateurYal.tds.symboles.SymboleFonction;

public class Fonction extends Expression {

    /**
     * identifiant de la fonction
     */
    private IDF idf;

    /**
     * paramètres de la fonction à l'appel
     */
    private ArbreAbstrait parametres;

    /**
     * nombre de paramètres de la fonction à son appel
     */
    private int nbParametres;

    /**
     * Constructeur par numéro de ligne
     * @param n ligne
     */
    public Fonction(IDF idf, ArbreAbstrait parametres, int n) {
        super(n);
        this.idf = idf;
        this.parametres = parametres;

        this.nbParametres = ((BlocDeParametresEffectifs)parametres).nbParametres();
    }

    /**
     * vérifie la fonction et récupère le numéro de région
     */
    @Override
    public void verifier() {
        Symbole s = TableDesSymboles.getInstance().identifier(new EntreeFonction(idf.getNom(), nbParametres), noLigne);
        if (nbParametres != ((SymboleFonction)s).getNbParametres()) {
            new AnalyseSemantiqueException(noLigne, "nombre de paramètres incorrect");
        }
        idf.setNbParametres(nbParametres);
        idf.verifier();
        parametres.verifier();
    }

    /**
     * @return instructions MIPS appelant la fonction cible
     */
    @Override
    public String toMIPS() {
        return  "                #appel de la fonction " + idf.getNom() + "\n" +
                "                #empiler les paramètres\n" +
                parametres.toMIPS() +
                "                #saut à l'étiquette de la fonction et sauvegarde de ra\n" +
                "    jal " + idf.getNom() + nbParametres + "\n";
    }

    @Override
    public String toString() {
        return idf.getNom() + "()";
    }
}
