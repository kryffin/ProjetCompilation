package compilateurYal.arbre.expressions.logique;

import compilateurYal.arbre.expressions.Expression;

public class SuperieurEgal extends ExpressionLogique {

    /**
     * Constructeur par expression gauche, droite et numéro de ligne
     * @param expGauche expression gauche
     * @param expDroite expression droite
     * @param n ligne
     */
    public SuperieurEgal(Expression expGauche, Expression expDroite, int n) {
        super(n);
        this.expGauche = expGauche;
        this.expDroite = expDroite;
    }

    /**
     * @return code MIPS du test de supériorié et égalité de deux expressions
     */
    @Override
    public String toMIPS() {
        return  super.toMIPS() +
                "    sge $v0, $t8, $v0\n";
    }

    @Override
    public String toString() {
        return "( " + expGauche + " >= " + expDroite + " )";
    }

}
