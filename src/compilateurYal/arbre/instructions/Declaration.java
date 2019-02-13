package compilateurYal.arbre.instructions;

import compilateurYal.GestionnaireTailleZoneVariable;
import compilateurYal.arbre.ArbreAbstrait;
import compilateurYal.arbre.expressions.Expression;
import compilateurYal.arbre.expressions.IDF;
import compilateurYal.tds.TableDesSymboles;
import compilateurYal.tds.entrees.EntreeVariable;
import compilateurYal.tds.symboles.SymboleVariable;

public class Declaration extends ArbreAbstrait {

    /**
     * identifiant de la variable à déclarer
     */
    private IDF idf;

    /**
     * Constructeur par identifiant et numéro de ligne
     * @param idf identifiant
     * @param n ligne
     */
    public Declaration (IDF idf, int n) {
        super(n);
        this.idf = idf;

        //ajout de la variable dans la table des symboles
        TableDesSymboles.getInstance().ajouter(new EntreeVariable(idf.getNom()), new SymboleVariable(TableDesSymboles.getInstance().getTailleZoneVariable()), n);

        //décrémentation de la pile des variables
        GestionnaireTailleZoneVariable.getInstance().incrementerTailleZoneVariable();
    }

    /**
     * vérifie l'identifiant
     */
    @Override
    public void verifier() {
        idf.verifier();
    }

    /**
     * @return instruction d'allocation dans la pile
     */
    @Override
    public String toMIPS() {
        return  "                #variable " + idf + "\n" +
                "    add $sp, $sp, -4\n\n";
    }
}
