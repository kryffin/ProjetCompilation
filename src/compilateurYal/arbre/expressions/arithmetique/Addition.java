package compilateurYal.arbre.expressions.arithmetique;

import compilateurYal.arbre.expressions.Expression;

public class Addition extends ExpressionArithmetique {

    public Addition(Expression expGauche, Expression expDroite, int n) {
        super(n);
        this.expGauche = expGauche;
        this.expDroite = expDroite;
    }

    @Override
    public String toMIPS() {
        return  "                #addition\n" +
                expGauche.toMIPS() +
                "";
    }

}
