package compilateurYal.tds.symboles;

public class SymboleVariable extends Symbole {

    /**
     * déplacement de la variable dans la pile $sp (donc $s7)
     */
    protected int deplacement;

    /**
     * Constructeur par déplacement de la variable dans la pile
     * @param deplacement entier représentant le déplacement dans la pile
     * @param nRegion entier représentant le numéro de région de la variable
     */
    public SymboleVariable (int deplacement, int nRegion) {
        super(nRegion);
        this.deplacement = deplacement;
    }

    /**
     * @return déplacement de la varible dans la pile
     */
    public int getDeplacement () {
        return deplacement;
    }

    @Override
    public String toString() {
        return "" + deplacement;
    }
}
