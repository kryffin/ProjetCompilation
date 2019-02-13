package compilateurYal.arbre;

public abstract class ArbreAbstrait {

    /**
     * numéro de ligne du début de l'instruction
     */
    protected int noLigne;

    /**
     * Constructeur par numéro de ligne
     * @param n ligne
     */
    protected ArbreAbstrait(int n) {
        noLigne = n;
    }

    /**
     * @return numéro de ligne
     */
    public int getNoLigne() {
            return noLigne;
    }

    public abstract void verifier();

    public abstract String toMIPS();

}
