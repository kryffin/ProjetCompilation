package compilateurYal.tds.symboles;

public class SymboleVariable extends Symbole {

    /**
     * déplacement de la variable dans la pile $sp (donc $s7)
     */
    protected int deplacement;

    /**
     * Constructeur par déplacement de la variable dans la pile
     * @param deplacement entier représentant le déplacement dans la pile
     */
    public SymboleVariable (int deplacement) {
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
