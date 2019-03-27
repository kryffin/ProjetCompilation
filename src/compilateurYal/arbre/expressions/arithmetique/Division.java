package compilateurYal.arbre.expressions.arithmetique;

import compilateurYal.arbre.expressions.Expression;

public class Division extends ExpressionArithmetique {

    /**
     * Constructeur par expression gauche, droite et numéro de ligne
     * @param expGauche expression gauche
     * @param expDroite expression droite
     * @param n ligne
     */
    public Division(Expression expGauche, Expression expDroite, int n) {
        super(n);
        this.expGauche = expGauche;
        this.expDroite = expDroite;
    }

    @Override
    public String toMIPS() {
        return  super.toMIPS() +
                "    beq $v0, $zero, affErreur\n" +
                "    div $t8, $v0\n" +
                "    mflo $v0\n";
    }

    @Override
    public String toString() {
        return "( " + expGauche + " / " + expDroite + " )";
    }

}
