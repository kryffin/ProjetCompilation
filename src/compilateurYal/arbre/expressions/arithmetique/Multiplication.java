package compilateurYal.arbre.expressions.arithmetique;

import compilateurYal.arbre.expressions.Expression;

public class Multiplication extends ExpressionArithmetique {

    /**
     * Constructeur par expression gauche, droite et numéro de ligne
     * @param expGauche expression gauche
     * @param expDroite expression droite
     * @param n ligne
     */
    public Multiplication(Expression expGauche, Expression expDroite, int n) {
        super(n);
        this.expGauche = expGauche;
        this.expDroite = expDroite;
    }

    @Override
    public String toMIPS() {
        return  super.toMIPS() +
                "    mult $t8, $v0\n" +
                "    mflo $v0\n";
    }

    @Override
    public String toString() {
        return "( " + expGauche + " * " + expDroite + " )";
    }

}
