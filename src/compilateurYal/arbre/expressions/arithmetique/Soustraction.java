package compilateurYal.arbre.expressions.arithmetique;

import compilateurYal.arbre.expressions.Expression;

public class Soustraction extends ExpressionArithmetique {

    /**
     * Constructeur par expression gauche, droite et numéro de ligne
     * @param expGauche expression gauche
     * @param expDroite expression droite
     * @param n ligne
     */
    public Soustraction(Expression expGauche, Expression expDroite, int n) {
        super(n);
        this.expGauche = expGauche;
        this.expDroite = expDroite;
    }

    @Override
    public String toMIPS() {
        return  super.toMIPS() +
                "    sub $v0, $t8, $v0\n";
    }

    @Override
    public String toString() {
        return "( " + expGauche + " - " + expDroite + " )";
    }

}
