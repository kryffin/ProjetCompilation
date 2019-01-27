package compilateurYal.arbre.expressions;

public abstract class ExpressionBinaire extends Expression {

    protected Expression expGauche;
    protected Expression expDroite;

    protected ExpressionBinaire(int n) {
        super(n);
    }

    public void setLine (int n) {
        noLigne = n;
    }

    public void setExpressionGauche (Expression exp) {
        expGauche = exp;
    }

    public void setExpressionDroite (Expression exp) {
        expDroite = exp;
    }

    @Override
    public void verifier() {
        expGauche.verifier();
        expDroite.verifier();
    }
}
