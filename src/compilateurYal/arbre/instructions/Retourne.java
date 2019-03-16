package compilateurYal.arbre.instructions;

import compilateurYal.arbre.expressions.Expression;

public class Retourne extends Instruction {

    /**
     * expression à retourner
     */
    private Expression exp;

    /**
     * nom de la fonction à quitter
     */
    private String nomFonction;

    /**
     * Constructeur par numéro de ligne
     * @param n ligne
     */
    public Retourne(Expression exp, int n) {
        super(n);
        this.exp = exp;
        this.retour = true;
    }

    /**
     * Setteur sur le nom de la fonction que l'on doit quitter
     * @param nomFonction
     */
    public void setNomFonction (String nomFonction) {
        this.nomFonction = nomFonction;
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
                exp.toMIPS() +
                "                #saut vers la sortie de la fonction\n" +
                "    j sortie" + nomFonction + "\n";
    }

}
