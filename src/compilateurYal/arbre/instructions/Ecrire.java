package compilateurYal.arbre.instructions;

import compilateurYal.arbre.expressions.Expression;

public class Ecrire extends Instruction {

    /**
     * expression à écrire
     */
    protected Expression exp ;

    /**
     * Constructeur par expression et numéro de ligne
     * @param e expression
     * @param n ligne
     */
    public Ecrire (Expression e, int n) {
        super(n) ;
        exp = e ;
    }

    /**
     * vérifie l'expression
     */
    @Override
    public void verifier() {
        exp.verifier();
    }

    /**
     * @return instruction mips de l'écriture de l'expression sur la sortie standard
     */
    @Override
    public String toMIPS() {
        return  "                # affichage de l'expression\n" +
                exp.toMIPS() +
                "    move $a0, $v0\n" +
                "    li $v0, 1\n" +
                "    syscall\n" +
                "    li $v0, 4      # retour à la ligne\n" +
                "    la $a0, finLigne\n" +
                "    syscall\n" ;
    }

}
