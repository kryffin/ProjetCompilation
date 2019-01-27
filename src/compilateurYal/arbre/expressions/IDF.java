package compilateurYal.arbre.expressions;

import compilateurYal.tds.TableDesSymboles;
import compilateurYal.tds.entrees.EntreeVariable;
import compilateurYal.tds.symboles.Symbole;
import compilateurYal.tds.symboles.SymboleVariable;

public class IDF extends Expression {

    private String nom;
    private int deplacement;

    public IDF (String nom, int n) {
        super(n);
        this.nom = nom;
    }

    public String getNom () {
        return nom;
    }

    public int getDeplacement () {
        return deplacement;
    }

    @Override
    public void verifier() {
        Symbole s = TableDesSymboles.getInstance().identifier(new EntreeVariable(nom));
        deplacement = ((SymboleVariable)s).getDeplacement();
    }

    @Override
    public String toMIPS() {
        return  "    lw $v0, " + deplacement + "($s7)\n";
    }

    @Override
    public String toString() {
        return nom;
    }

}
