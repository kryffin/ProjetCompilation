package compilateurYal.arbre.instructions;

import compilateurYal.Yal;
import compilateurYal.arbre.expressions.Expression;
import compilateurYal.arbre.expressions.IDF;
import compilateurYal.exceptions.AnalyseSemantiqueException;

public class Affectation extends Instruction {

    /**
     * identifiant de la variable à modifier
     */
    private IDF idf;

    /**
     * expression à affecter à la variable
     */
    private Expression exp;

    /**
     * Constructeur par identifiant, expression et numéro de ligne
     * @param idf identifiant
     * @param exp expression
     * @param n ligne
     */
    public Affectation(IDF idf, Expression exp, int n) {
        super(n);
        this.idf = idf;
        this.exp = exp;
    }

    /**
     * vérifie l'identifiant et l'expression
     */
    @Override
    public void verifier() {
        idf.verifier();
        exp.verifier();

        //on ne peut affecter une expression logique à un entier
        if (exp.estLogique()) {
            new AnalyseSemantiqueException(noLigne, "affectation d'une expression logique " + exp + " dans un entier " + idf);
        }
    }

    /**
     * @return instruction mips de l'affectation d'une expression à une variable
     */
    @Override
    public String toMIPS() {
        return  "                #affectation de " + exp + " dans " + idf + "\n" +
                exp.toMIPS() +
                "    sw $v0, " + idf.getDeplacement() + "($s7)\n\n";
    }
}
