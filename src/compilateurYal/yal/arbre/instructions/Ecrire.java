package compilateurYal.yal.arbre.instructions;

import compilateurYal.yal.arbre.ArbreAbstrait;
import compilateurYal.yal.arbre.expressions.Expression;
import compilateurYal.yal.exceptions.AnalyseSemantiqueException;

public class Ecrire extends Instruction {

    protected Expression exp ;

    public Ecrire (Expression e, int n) {
        super(n) ;
        exp = e ;
    }

    @Override
    public void verifier() throws AnalyseSemantiqueException {
        exp.verifier();
    }

    @Override
    public String toMIPS() {
        return  "                   # affichage de l'expression\n" +
                exp.toMIPS() +
                "    move $a0, $v0\n" +
                "    li $v0, 1\n" +
                "    syscall\n" +
                "    li $v0, 4      # retour à la ligne\n" +
                "    la $a0, finLigne\n" +
                "    syscall\n" ;
    }

    @Override
    public void ajouter(ArbreAbstrait... a) {
    }

}
