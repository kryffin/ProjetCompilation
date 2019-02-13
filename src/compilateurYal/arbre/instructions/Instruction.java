package compilateurYal.arbre.instructions;

import compilateurYal.arbre.ArbreAbstrait;

public abstract class Instruction extends ArbreAbstrait {

    /**
     * Constructeur par numéro de ligne
     * @param n ligne
     */
    protected Instruction(int n) {
        super(n);
    }

}
