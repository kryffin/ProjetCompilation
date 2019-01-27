package compilateurYal.tds.symboles;

public class SymboleVariable extends Symbole {

    protected int deplacement;

    public SymboleVariable (int deplacement) {
        this.deplacement = deplacement;
    }

    public int getDeplacement () {
        return deplacement;
    }

    @Override
    public String toString() {
        return "" + deplacement;
    }
}
