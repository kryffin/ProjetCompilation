package compilateurYal.arbre.expressions;

import compilateurYal.Yal;
import compilateurYal.tds.TableDesSymboles;
import compilateurYal.tds.entrees.EntreeVariable;
import compilateurYal.tds.symboles.Symbole;
import compilateurYal.tds.symboles.SymboleVariable;

public class IDF extends Expression {

    /**
     * nom de la variable
     */
    private String nom;

    /**
     * déplacement dans la pile de la variable
     */
    private int deplacement;

    /**
     * Construction par nom et numéro de ligne
     * @param nom nom de la variable
     * @param n ligne
     */
    public IDF (String nom, int n) {
        super(n);
        this.nom = nom;
    }

    /**
     * @return nom de la variable
     */
    public String getNom () {
        return nom;
    }

    /**
     * @return déplacement dans la pile de la variable
     */
    public int getDeplacement () {
        return deplacement;
    }

    /**
     * vérifie si la variable est bien dans la table des symboles et met à jour le déplacement
     */
    @Override
    public void verifier() {
        //récupération du symbole et renseignement du déplacement et type de la variable si elle existe bien
        Symbole s = TableDesSymboles.getInstance().identifier(new EntreeVariable(nom), noLigne);
        if (Yal.exception) {
            //en cas d'erreur précédente on ne vérifie pas plus loin la variable
            return;
        }
        deplacement = ((SymboleVariable)s).getDeplacement();
    }

    /**
     * @return l'instruction mips de chargement de la variable dans $v0
     */
    @Override
    public String toMIPS() {
        return  "    lw $v0, " + deplacement + "($s7)\n";
    }

    @Override
    public String toString() {
        return nom;
    }

}
