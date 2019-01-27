package compilateurYal.yal.arbre.instructions;

import compilateurYal.yal.arbre.ArbreAbstrait;
import compilateurYal.yal.arbre.expressions.Expression;
import compilateurYal.yal.arbre.expressions.Idf;
import compilateurYal.yal.exceptions.AnalyseSemantiqueException;
import compilateurYal.yal.tds.Entree;
import compilateurYal.yal.tds.Tds;

public class Affectation extends Instruction {

    protected Expression e;
    protected Idf idf;

    public Affectation(Idf idf, Expression exp, int n) {
        super(n);
        this.idf = idf;
        this.e = exp;
    }

    @Override
    public void verifier() throws AnalyseSemantiqueException {
        e.verifier();
    }

    @Override
    public String toMIPS() {
        int depl = Tds.getInstance().identifier(new Entree(idf)).getDeplacement();
        return      "#affectation " +idf + " = " + e +"\n"
                +   e.toMIPS()
                +   "move $a0, $v0 \n"
                +   "li $v0, $a0\n"
                +   "sw $v0, "+depl+"($s7)\n";

    }

    @Override
    public void ajouter(ArbreAbstrait... a) {
    }
}
