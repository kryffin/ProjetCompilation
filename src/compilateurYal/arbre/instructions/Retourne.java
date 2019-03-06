package compilateurYal.arbre.instructions;

import compilateurYal.arbre.expressions.Expression;

public class Retourne extends Instruction {

    /**
     * expression à retourner
     */
    private Expression exp;

    /**
     * Constructeur par numéro de ligne
     * @param n ligne
     */
    public Retourne(Expression exp, int n) {
        super(n);
        this.exp = exp;
    }

    /**
     * vérifie l'expression à retourner
     */
    @Override
    public void verifier() {
        exp.verifier();
    }

    /**
     * @return instructions MIPS de la sauvegarde dans v0 de la valeur de retour
     */
    @Override
    public String toMIPS() {
        return  "                #sauvegarde de " + exp + " dans v0\n" +
                exp.toMIPS();
    }

}
