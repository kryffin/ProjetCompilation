package compilateurYal.arbre.instructions;

import compilateurYal.arbre.expressions.CaseTableau;
import compilateurYal.arbre.expressions.ConstanteEntiere;
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

        //on ne peut accéder à une case de tableau en donnant un indice de type booléen
        if (caseTableau.getIndice().estLogique()) {
            new AnalyseSemantiqueException(noLigne, "indice de " + caseTableau + " n'est pas entier");
        }
    }

    /**
     * @return instruction mips de l'affectation d'une expression à une variable
     */
    @Override
    public String toMIPS() {
        SymboleTableau s = (SymboleTableau) TableDesSymboles.getInstance().identifier(new EntreeTableau(caseTableau.getNom()), noLigne);
        return  "                #affectation de " + exp + " dans " + caseTableau + "\n" +
                caseTableau.getIndice().toMIPS() +
                "    add $t8, $v0, $zero\n" +
                s.getTaille().toMIPS() +
                "    bltz $t8, affErreur\n" +
                "    sub $v0, $v0, $t8\n" +
                "    bltz $v0, affErreur\n" +
                "    beqz $v0, affErreur\n" +
                exp.toMIPS() +
                "    sw $v0, ($sp)\n" +
                "    add $sp, $sp, -4\n" +
                caseTableau.getIndice().toMIPS() +
                "    add $t8, $zero, " + s.getEnjambee() + "\n" +
                "    mult $v0, $t8\n" +
                "    mflo $v0\n" +
                "    lw $t8, " + s.getDeplacement() + "($s7)\n" +
                "    add $v0, $v0, $t8\n" +
                "    add $t8, $v0, $s7\n" +
                "    add $sp, $sp, 4\n" +
                "    lw $v0, ($sp)\n" +
                "    sw $v0, ($t8)\n\n";
    }
}
