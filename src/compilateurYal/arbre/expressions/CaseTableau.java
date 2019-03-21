package compilateurYal.arbre.expressions;

import compilateurYal.tds.TableDesSymboles;
import compilateurYal.tds.entrees.EntreeTableau;
import compilateurYal.tds.symboles.Symbole;
import compilateurYal.tds.symboles.SymboleTableau;

public class CaseTableau extends Expression {

    private String nom;
    private Expression indice;

    /**
     * Constructeur par numéro de ligne
     * @param nom nom du tableau
     * @param indice indice de la case du tableau
     * @param n ligne
     */
    public CaseTableau(String nom, Expression indice, int n) {
        super(n);
        this.nom = nom;
        this.indice = indice;
    }

    public String getNom() {
        return nom;
    }

    @Override
    public void verifier() {
        indice.verifier();
        //récupération du symbole et renseignement du déplacement et type de la variable si elle existe bien
        TableDesSymboles.getInstance().identifier(new EntreeTableau(nom), noLigne);
    }

    @Override
    public String toMIPS() {
        SymboleTableau s = (SymboleTableau)TableDesSymboles.getInstance().identifier(new EntreeTableau(nom), noLigne);
        return  indice.toMIPS() +
                "    add $t8, $zero, " + s.getEnjambee() + "\n" +
                "    mult $v0, $t8\n" +
                "    mflo $v0\n" +
                "    add $a0, $v0, " + s.getAdrImplementation() + "\n";
    }

    @Override
    public String toString() {
        return nom + "[" + indice + "]";
    }
}
