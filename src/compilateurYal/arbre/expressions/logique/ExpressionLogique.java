package compilateurYal.arbre.expressions.logique;

import compilateurYal.arbre.expressions.ExpressionBinaire;

public abstract class ExpressionLogique extends ExpressionBinaire {


    /**
     * Constructeur par numéro de ligne
     * @param n ligne
     */
    protected ExpressionLogique(int n) {
        super(n);
    }

}
