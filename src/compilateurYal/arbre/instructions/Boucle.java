package compilateurYal.arbre.instructions;

import compilateurYal.CompteurBoucles;
import compilateurYal.CompteurConditions;
import compilateurYal.Yal;
import compilateurYal.arbre.ArbreAbstrait;
import compilateurYal.arbre.expressions.Expression;
import compilateurYal.exceptions.AnalyseSemantiqueException;

public class Boucle extends Instruction {

    /**
     * expression à évaluer
     */
    private Expression exp;

    /**
     * liste d'instructions de la boucle
     */
    private ArbreAbstrait tantque;

    /**
     * numéro de la boucle
     */
    private int cpt;

    /**
     * Constructeur par expression, liste d'instruction de la boucle et numéro de ligne
     * @param exp expression à évaluer
     * @param tantque liste d'instructions de la boucle
     * @param n ligne
     */
    public Boucle (Expression exp, ArbreAbstrait tantque, int n) {
        super(n);
        this.exp = exp;
        this.tantque = tantque;
        cpt = CompteurBoucles.getInstance().getCompteur();
        CompteurBoucles.getInstance().incrementerCompteur();
    }

    /**
     * vérifie l'expression et la liste d'instructions
     */
    @Override
    public void verifier() {
        exp.verifier();
        tantque.verifier();

        //vérification que l'expression testée est bien logique
        if (!exp.estLogique()) {
            new AnalyseSemantiqueException(noLigne, "expression évaluée dans la boucle " + cpt + " n'est pas booléenne");
        }
    }

    /**
     * @return code MIPS représentant la boucle tantque
     */
    @Override
    public String toMIPS() {
        return  "        #boucle " + cpt + " sur " + exp.toString() + "\n" +
                "tantque" + cpt + ":\n" +
                exp.toMIPS() +
                "    beq $v0, $zero, fintantque" + cpt + "\n" +
                tantque.toMIPS() +
                "    j tantque" + cpt + "\n\n" +
                "fintantque" + cpt + ":\n\n";
    }
}
