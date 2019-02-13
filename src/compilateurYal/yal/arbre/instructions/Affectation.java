package compilateurYal.yal.arbre.instructions;

import compilateurYal.yal.arbre.expressions.Expression;
import compilateurYal.yal.arbre.expressions.Idf;

public class Affectation extends Instruction {

    protected Expression exp ;
    private Idf idf;

    public Affectation(Idf id, Expression e, int n){
        super(n);
        idf = id;
        exp = e;
    }

    @Override
    public void verifier() {

    }

    @Override
    public String toMIPS() {
        return  "           #Affectation\n"+
                exp.toMIPS()+
                "   sw $v0, "+idf.getDeplacement()+"($s7)\n";
    }
}
