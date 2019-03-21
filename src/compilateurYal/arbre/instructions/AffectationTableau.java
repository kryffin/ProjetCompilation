package compilateurYal.arbre.instructions;

import compilateurYal.arbre.expressions.CaseTableau;
import compilateurYal.arbre.expressions.Expression;
import compilateurYal.exceptions.AnalyseSemantiqueException;
import compilateurYal.tds.TableDesSymboles;
import compilateurYal.tds.entrees.EntreeTableau;
import compilateurYal.tds.symboles.SymboleTableau;

public class AffectationTableau extends Instruction {

    private CaseTableau caseTableau;

    /**
     * expression à affecter à la variable
     */
    private Expression exp;

    /**
     * Constructeur par identifiant, expression et numéro de ligne
     * @param caseTableau case du tableau
     * @param exp expression
     * @param n ligne
     */
    public AffectationTableau(CaseTableau caseTableau, Expression exp, int n) {
        super(n);
        this.caseTableau = caseTableau;
        this.exp = exp;
    }

    /**
     * vérifie l'identifiant et l'expression
     */
    @Override
    public void verifier() {
        caseTableau.verifier();
        exp.verifier();

        //on ne peut affecter une expression logique à un entier
        if (exp.estLogique()) {
            new AnalyseSemantiqueException(noLigne, "affectation d'une expression logique " + exp + " dans un entier " + caseTableau);
        }
    }

    /**
     * @return instruction mips de l'affectation d'une expression à une variable
     */
    @Override
    public String toMIPS() {
        SymboleTableau s = (SymboleTableau) TableDesSymboles.getInstance().identifier(new EntreeTableau(caseTableau.getNom()), noLigne);
        return  "                #affectation de " + exp + " dans " + caseTableau + "\n" +
                exp.toMIPS() +
                "    sw $v0, ($sp)\n" +
                "    add $sp, $sp, -4\n" +
                caseTableau.toMIPS() +
                "    add $sp, $sp, 4\n" +
                "    lw $v0, ($sp)\n" +
                "    sw $v0, ($a0)\n\n";
    }
}
