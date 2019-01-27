package compilateurYal.arbre.instructions;

import compilateurYal.GestionnaireTailleZoneVariable;
import compilateurYal.arbre.ArbreAbstrait;
import compilateurYal.arbre.expressions.IDF;
import compilateurYal.tds.TableDesSymboles;
import compilateurYal.tds.entrees.EntreeVariable;
import compilateurYal.tds.symboles.Symbole;
import compilateurYal.tds.symboles.SymboleVariable;

public class Declaration extends ArbreAbstrait {

    private IDF idf;

    public Declaration (IDF idf, int n) {
        super(n);
        this.idf = idf;
        TableDesSymboles.getInstance().ajouter(new EntreeVariable(idf.getNom()), new SymboleVariable(TableDesSymboles.getInstance().getTailleZoneVariable()));
        System.out.println("Ajout de " + idf + " à la place " + GestionnaireTailleZoneVariable.getInstance().getTailleZoneVariable());
        GestionnaireTailleZoneVariable.getInstance().incrementerTailleZoneVariable();
    }

    @Override
    public void verifier() {
        idf.verifier();
    }

    @Override
    public String toMIPS() {
        return  "                #entier " + idf + "\n" +
                "    add $sp, $sp, -4\n\n";
    }
}
