package compilateurYal.arbre.instructions;

import compilateurYal.arbre.expressions.IDF;

public class Lire extends Instruction {

    private IDF idf;

    public Lire (IDF idf, int n) {
        super(n);
        this.idf = idf;
    }

    @Override
    public void verifier() {
        idf.verifier();
    }

    @Override
    public String toMIPS() {
        return  "                #lecture de la sortie standard dans la variable " + idf.getNom() + "\n" +
                "    li $v0, 5\n" +
                "    syscall\n" +
                "    sw $v0, " + idf.getDeplacement() + "($s7)\n";
    }

}
