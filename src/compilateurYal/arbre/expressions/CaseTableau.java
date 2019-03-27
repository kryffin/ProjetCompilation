package compilateurYal.arbre.expressions;

import compilateurYal.tds.TableDesSymboles;
import compilateurYal.tds.entrees.EntreeTableau;
import compilateurYal.tds.symboles.SymboleTableau;

public class CaseTableau extends Expression {

    /**
     * nom du tableau
     */
    private String nom;

    /**
     * indice de la case du tableau
     */
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

    /**
     * @return nom du tableau
     */
    public String getNom() {
        return nom;
    }

    /**
     * @return indice du tableau
     */
    public Expression getIndice () {
        return indice;
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
                "    lw $t8, " + s.getDeplacement() + "($s7)\n" +
                "    add $v0, $v0, $t8\n" +
                "    add $v0, $v0, $s7\n" +
                "    lw $v0, ($v0)\n";
    }

    @Override
    public String toString() {
        return nom + "[" + indice + "]";
    }
}