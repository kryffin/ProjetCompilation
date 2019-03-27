package compilateurYal.arbre.instructions;

import compilateurYal.GestionnaireTailleZoneVariable;
import compilateurYal.arbre.expressions.ConstanteEntiere;
import compilateurYal.arbre.expressions.Expression;
import compilateurYal.arbre.expressions.IDF;
import compilateurYal.exceptions.AnalyseSemantiqueException;
import compilateurYal.tds.TableDesSymboles;
import compilateurYal.tds.entrees.EntreeTableau;
import compilateurYal.tds.symboles.SymboleTableau;

public class DeclarationTableau extends Instruction {

    private IDF idf;
    private Expression taille;
    private int nRegion;

    /**
     * Constructeur par numéro de ligne
     * @param n ligne
     */
    public DeclarationTableau(IDF idf, Expression taille, int n) {
        super(n);
        this.idf = idf;
        this.taille = taille;

        int tailleZoneVariable = TableDesSymboles.getInstance().getTailleZoneVariable();
        nRegion = TableDesSymboles.getInstance().getTableCourante().getNRegion();

        TableDesSymboles.getInstance().ajouter(new EntreeTableau(idf.getNom()), new SymboleTableau(tailleZoneVariable, nRegion, taille), n);

        GestionnaireTailleZoneVariable.getInstance().incrementerTailleZoneVariable(); //deplacement des cases du tableau
    }

    /**
     * @return nom du tableau
     */
    public String getNom () {
        return idf.getNom();
    }

    @Override
    public void verifier() {
        idf.verifier();
        taille.verifier();

        //test que le tableau soit bien statique s'il est dans le programme principal
        if (nRegion == 0 && !taille.estConstante()) {
            new AnalyseSemantiqueException(noLigne, "un tableau dans le programme principal ne peut être que statique");
        }
    }

    @Override
    public String toMIPS() {
        StringBuilder sb = new StringBuilder();
        sb.append("                #info sur le déplacement des cases de " + idf + "\n");
        sb.append("    add $sp, $sp, -4\n");

        return sb.toString();
    }

}
