package compilateurYal.yal.arbre.expressions;

import compilateurYal.yal.tds.EntreeVariable;
import compilateurYal.yal.tds.Symbole;
import compilateurYal.yal.tds.SymboleVariable;
import compilateurYal.yal.tds.TDS;

public class Idf extends Expression {

    private String nom;
    private int deplacement;

    public Idf(String n, int i){
        super (i);
        nom = n;
    }

    public String getNom(){
        return nom;
    }

    public int getDeplacement(){
        return deplacement;
    }

    @Override
    public void verifier() {
        Symbole s = TDS.getInstance().identifier(new EntreeVariable(nom));
        deplacement = ((SymboleVariable)s).getDeplacement();
    }

    @Override
    public String toMIPS() {
        return "    li $v0," + deplacement + "($s7)";
    }
}
