package compilateurYal.yal.tds;

public class SymboleVariable extends Symbole {

    private int deplacement;

    public SymboleVariable(int depla){
        deplacement = depla;
    }

    public int getDeplacement(){
        return deplacement;
    }
}
