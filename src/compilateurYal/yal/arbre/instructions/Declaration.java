package compilateurYal.yal.arbre.instructions;

import compilateurYal.yal.GestionnaireTailleVariable;
import compilateurYal.yal.arbre.expressions.Idf;
import compilateurYal.yal.tds.EntreeVariable;
import compilateurYal.yal.tds.SymboleVariable;
import compilateurYal.yal.tds.TDS;

public class Declaration extends Instruction {

    private Idf idf;

    public Declaration(Idf id, int i){
        super(i);
        idf = id;
        TDS.getInstance().ajouter( new EntreeVariable(idf.getNom()), new SymboleVariable( GestionnaireTailleVariable.getInstance().getTailleZone()) );
        GestionnaireTailleVariable.getInstance().incrementerTailleZone();
    }

    @Override
    public void verifier() {

    }

    @Override
    public String toMIPS() {
        return  "           #Déclaration d'une nouvelle variable\n"+
                "   move $s7,$sp\n"+
                "   add $sp, $sp, -4\n";
    }
}