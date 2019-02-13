package compilateurYal.tds.symboles;

public class SymboleVariable extends Symbole {

    /**
     * type de la variable
     */
    protected short type;

    /**
     * déplacement de la variable dans la pile $sp (donc $s7)
     */
    protected int deplacement;

    /**
     * Constructeur par déplacement de la variable dans la pile
     * @param deplacement entier représentant le déplacement dans la pile
     */
    public SymboleVariable (short type, int deplacement) {
        this.type = type;
        this.deplacement = deplacement;
    }

    /**
     * @return type de la variable
     */
    public short getType () {
        return type;
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
