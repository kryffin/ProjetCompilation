package compilateurYal.arbre.instructions;

import compilateurYal.arbre.expressions.IDF;

public class Lire extends Instruction {

    /**
     * identifiant de la variable à lire
     */
    private IDF idf;

    /**
     * Constructeur par identifiant et numéro de ligne
     * @param idf identifiant
     * @param n ligne
     */
    public Lire (IDF idf, int n) {
        super(n);
        this.idf = idf;
    }

    /**
     * vérifie l'identifiant
     */
    @Override
    public void verifier() {
        idf.verifier();
    }

    /**
     * @return instruction mips de la lecture et du stockage du résultat dans l'identifiant
     */
    @Override
    public String toMIPS() {
        return  "                #lecture de la sortie standard dans la variable " + idf.getNom() + "\n" +
                "    li $v0, 5\n" +
                "    syscall\n" +
                "    sw $v0, " + idf.getDeplacement() + "($s7)\n";
    }

}
