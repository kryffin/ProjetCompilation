package compilateurYal.arbre.instructions;

import compilateurYal.arbre.expressions.Expression;
import compilateurYal.arbre.expressions.IDF;

public class Affectation extends Instruction {

    private IDF idf;
    private Expression exp;

    public Affectation(IDF idf, Expression exp, int n) {
        super(n);
        this.idf = idf;
        this.exp = exp;
    }

    @Override
    public void verifier() {
        idf.verifier();
        exp.verifier();
    }

    @Override
    public String toMIPS() {
        return  "                #affectation de " + exp + " dans " + idf + "\n" +
                exp.toMIPS() +
                "    sw $v0, " + idf.getDeplacement() + "($s7)\n\n";
    }
}
