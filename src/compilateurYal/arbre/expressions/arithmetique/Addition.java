package compilateurYal.arbre.expressions.arithmetique;

import compilateurYal.arbre.expressions.Expression;

public class Addition extends ExpressionArithmetique {

    /**
     * Constructeur par expression gauche, droite et numéro de ligne
     * @param expGauche expression gauche
     * @param expDroite expression droite
     * @param n ligne
     */
    public Addition(Expression expGauche, Expression expDroite, int n) {
        super(n);
        this.expGauche = expGauche;
        this.expDroite = expDroite;
    }

    @Override
    public String toMIPS() {
        return null;
    }

}
