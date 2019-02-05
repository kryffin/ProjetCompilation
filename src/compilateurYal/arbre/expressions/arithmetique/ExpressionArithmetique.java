package compilateurYal.arbre.expressions.arithmetique;

import compilateurYal.arbre.expressions.ExpressionBinaire;

public abstract class ExpressionArithmetique extends ExpressionBinaire {

    /**
     * Constructeur par numéro de ligne
     * @param n ligne
     */
    protected ExpressionArithmetique(int n) {
        super(n);
    }

    @Override
    public String toMIPS() {

        return super.toMIPS();

    }
}
