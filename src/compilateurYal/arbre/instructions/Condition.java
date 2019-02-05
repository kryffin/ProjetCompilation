package compilateurYal.arbre.instructions;

import compilateurYal.CompteurConditions;
import compilateurYal.arbre.ArbreAbstrait;
import compilateurYal.arbre.BlocDInstructions;
import compilateurYal.arbre.expressions.Expression;

public class Condition extends Instruction {

    /**
     * expression à évaluer
     */
    private Expression exp;

    /**
     * liste d'instructions du si
     */
    private ArbreAbstrait si;

    /**
     * liste d'instructions du sinon
     */
    private ArbreAbstrait sinon;

    /**
     * numéro de la condition
     */
    private int cpt;

    /**
     * Constructeur par expression, liste d'instruction du si et du sinon et numéro de ligne
     * @param exp expression à évaluer
     * @param si liste d'instructions du si
     * @param sinon liste d'instructions du sinon (vide si ==null)
     * @param n ligne
     */
    public Condition (Expression exp, ArbreAbstrait si, ArbreAbstrait sinon, int n) {
        super(n);
        this.exp = exp;
        this.si = si;
        if (sinon == null) {
            this.sinon = new BlocDInstructions(n);
        } else {
            this.sinon = sinon;
        }
        cpt = CompteurConditions.getInstance().getCompteur();
        CompteurConditions.getInstance().incrementerCompteur();
    }

    /**
     * vérification de l'expression et des instructions
     */
    @Override
    public void verifier() {
        exp.verifier();
        si.verifier();
        sinon.verifier();
    }

    @Override
    public String toMIPS() {
        return  "        #conditionnelle " + cpt + " sur " + exp.toString() + "\n" +
                "si" + cpt + ":\n" +
                exp.toMIPS() +
                "    beq $v0, $zero, sinon" + cpt + "\n\n" +
                "alors" + cpt + ":\n" +
                si.toMIPS() +
                "    j finsi" + cpt + "\n\n" +
                "sinon" + cpt + ":\n" +
                sinon.toMIPS() +
                "finsi" + cpt + ":\n\n";
    }
}
