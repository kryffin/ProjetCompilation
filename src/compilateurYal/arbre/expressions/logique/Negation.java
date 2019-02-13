package compilateurYal.arbre.expressions.logique;

import compilateurYal.arbre.expressions.Expression;

public class Negation extends Expression {

    private Expression exp;

    /**
     * Constructeur par expression et numéro de ligne
     * @param exp expression
     * @param n ligne
     */
    public Negation(Expression exp, int n) {
        super(n);
        this.exp = exp;
    }

    /**
     * vérifie l'expression
     */
    @Override
    public void verifier() {
        exp.verifier();
    }

    /**
     * @return code MIPS de la négation d'une expression
     */
    @Override
    public String toMIPS() {
        return  exp.toMIPS() +
                "    xori $v0, $v0, 1\n";
    }

    @Override
    public String toString() {
        return "( !" + exp + " )";
    }


}
