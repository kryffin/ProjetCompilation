package compilateurYal.arbre.instructions;

import compilateurYal.CompteurEcrireLogique;
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
        StringBuilder sb = new StringBuilder();

        sb.append(  "                #affichage de " + exp + "\n" +
                    exp.toMIPS());

        //si l'expression est logique on écrire vrai/faux plutôt que sa valeur entière (1/0)
        if (exp.estLogique()) {

            //si l'expression est logique, on récupère le compteur de l'instruction écrire et on l'incrémente
            int cpt = CompteurEcrireLogique.getInstance().getCompteur();
            CompteurEcrireLogique.getInstance().incrementerCompteur();

            //simple branchement sur ecrireFaux quand l'expression est fausse, vrai sinon
            sb.append(  "\necrire" + cpt + ":\n" +
                        "    beq $v0, $zero, ecrireFaux" + cpt + "\n\n" +
                        "                #si " + exp + " = 1, on écrit donc vrai\n" +
                        "ecrireVrai" + cpt + ":\n" +
                        "    li $v0, 4\n" +
                        "    la $a0, vrai\n" +
                        "    syscall\n" +
                        "    j finEcrire" + cpt + "\n\n" +
                        "                #si " + exp + " = 0, on écrit donc faux\n" +
                        "ecrireFaux" + cpt + ":\n" +
                        "    li $v0, 4\n" +
                        "    la $a0, faux\n" +
                        "    syscall\n\n" +
                        "finEcrire" + cpt + ":\n");

        } else {

            sb.append(  "    move $a0, $v0\n" +
                        "    li $v0, 1\n" +
                        "    syscall\n");

        }

        //saute une ligne
        sb.append(  "                #retour à la ligne\n" +
                    "    li $v0, 4\n" +
                    "    la $a0, finLigne\n" +
                    "    syscall\n\n");

        return sb.toString();
    }

}
