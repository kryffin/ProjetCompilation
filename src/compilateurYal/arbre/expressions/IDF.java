package compilateurYal.arbre.expressions;

import compilateurYal.exceptions.AnalyseSemantiqueException;
import compilateurYal.tds.TableDesSymboles;
import compilateurYal.tds.entrees.EntreeVariable;
import compilateurYal.tds.symboles.Symbole;
import compilateurYal.tds.symboles.SymboleVariable;

public class IDF extends Expression {

    /**
     * type de la variable
     */
    private short type;

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
        this.type = -1;
        this.nom = nom;
    }

    /**
     * Construction par type, nom et numéro de ligne
     * @param nom nom de la variable
     * @param n ligne
     */
    public IDF (short type, String nom, int n) {
        super(n);
        this.type = type;
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
     * @return type de la variable
     */
    public short getType () {
        return type;
    }

    /**
     * vérifie si la variable est bien dans la table des symboles et met à jour le déplacement
     */
    @Override
    public void verifier() {
        //récupération du symbole et renseignement du déplacement et type de la variable si elle existe bien
        Symbole s = TableDesSymboles.getInstance().identifier(new EntreeVariable(nom), noLigne);
        deplacement = ((SymboleVariable)s).getDeplacement();
        type = ((SymboleVariable) s).getType();

        //si le type n'est pas reconnu on lance une erreur
        if (type == Expression.NON_RECONNU) {
            new AnalyseSemantiqueException(noLigne, "type de " + nom + " non reconnu").printStackTrace();
        }

        //si la variable est booléenne l'expression est donc logique
        if (type == Expression.BOOLEEN) {
            estLogique = true;
        }
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
        if (type == Expression.ENTIER) {
            return nom + " (entier)";
        }
        if (type == Expression.BOOLEEN) {
            return nom + " (booléen)";
        }
        return nom;
    }

}
