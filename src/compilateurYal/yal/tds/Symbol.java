package compilateurYal.yal.tds;

public class Symbol {

    private int deplacement;

    public Symbol(int depl){
        deplacement = depl;
    }

    public int getDeplacement(){
        return deplacement;
    }

    public String toString(){
        return ""+deplacement;
    }
}
