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

        if (taille.estConstante()) {
            for (int i = 0; i < Integer.valueOf(((ConstanteEntiere)taille).getCste()); i++) {
                //incrémentation de la taille de la zone variable pour instancier chaque éléments du tableau
                GestionnaireTailleZoneVariable.getInstance().incrementerTailleZoneVariable();
            }
        }
    }

    @Override
    public void verifier() {
        idf.verifier();
        taille.verifier();

        if (nRegion == 0 && !taille.estConstante()) {
            new AnalyseSemantiqueException(noLigne, "un tableau dans le programme principal ne peut être que statique");
        }
    }

    @Override
    public String toMIPS() {
        StringBuilder sb = new StringBuilder();
        if (taille.estConstante()) {
            for (int i = 0; i < Integer.valueOf(((ConstanteEntiere)taille).getCste()); i++) {
                //allouer chaque case du tableau
                sb.append("                #tableau " + idf + ", case " + i + "\n");
                sb.append("    add $sp, $sp, -4\n");
            }
        }

        return sb.toString();
    }

}
