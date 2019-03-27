package compilateurYal.arbre.expressions;

public abstract class ExpressionBinaire extends Expression {

    /**
     * partie gauche de l'expression
     */
    protected Expression expGauche;

    /**
     * partie droite de l'expression
     */
    protected Expression expDroite;

    /**
     * Constructeur par numéro de ligne
     * @param n ligne
     */
    protected ExpressionBinaire(int n) {
        super(n);
    }

    /**
     * Setteur du numéro de ligne
     * @param n ligne
     */
    public void setLine (int n) {
        noLigne = n;
    }

    /**
     * Setteur sur la partie gauche de l'expression
     * @param exp partie gauche de l'expression
     */
    public void setExpressionGauche (Expression exp) {
        expGauche = exp;
    }

    /**
     * Setteur sur la partie droite de l'expression
     * @param exp partie droite de l'expression
     */
    public void setExpressionDroite (Expression exp) {
        expDroite = exp;
    }

    /**
     * vérifie la partie gauche et droite de l'expression
     */
    @Override
    public void verifier() {
        expGauche.verifier();
        expDroite.verifier();
    }

    @Override
    public boolean estConstante() {
        return expGauche.estConstante && expDroite.estConstante;
    }

    @Override
    public String toMIPS() {

        if (expDroite.estConstante()) {
            return  expGauche.toMIPS() +
                    "    move $t8, $v0\n" +
                    expDroite.toMIPS();
        }

        return  expGauche.toMIPS() +
                "    sw $v0, ($sp)\n" +
                "    add $sp, $sp, -4\n" +
                expDroite.toMIPS() +
                "    add $sp, $sp, 4\n" +
                "    lw $t8, ($sp)\n";
    }

}
