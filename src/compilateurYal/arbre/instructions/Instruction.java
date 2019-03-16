package compilateurYal.arbre.instructions;

import compilateurYal.arbre.ArbreAbstrait;

public abstract class Instruction extends ArbreAbstrait {

    /**
     * vrai si l'instruction est un retour de fonction, faux sinon
     */
    protected boolean retour = false;

    /**
     * Constructeur par numéro de ligne
     * @param n ligne
     */
    protected Instruction(int n) {
        super(n);
    }

    /**
     * @return vrai si l'instruction est un retour de fonction, faux sinon
     */
    public boolean instructionRetour () {
        return retour;
    }

}
