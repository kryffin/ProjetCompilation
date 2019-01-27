package compilateurYal.yal.arbre.declaration;

import compilateurYal.yal.arbre.ArbreAbstrait;
import compilateurYal.yal.arbre.expressions.Idf;
import compilateurYal.yal.tds.Entree;
import compilateurYal.yal.tds.GenerateurDeplacement;
import compilateurYal.yal.tds.Symbol;
import compilateurYal.yal.tds.Tds;

public class Declaration extends ArbreAbstrait {

    protected Idf idf;
    public Declaration(Idf idf, int n) {
        super(n);
        this.idf = idf;
        Tds tds = Tds.getInstance();
        tds.ajouter(new Entree(idf), new Symbol(GenerateurDeplacement.getInstance().getNextDeplacement()));
    }

    @Override
    public void verifier() {

    }

    @Override
    public String toMIPS() {
        return "\t#reservation de la place memoire pour "+ idf +
                "\n\tadd $sp, $sp, -4\n";
    }

}
