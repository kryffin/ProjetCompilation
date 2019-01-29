package compilateurYal.tds;

import compilateurYal.GestionnaireTailleZoneVariable;
import compilateurYal.exceptions.DoubleDeclarationException;
import compilateurYal.exceptions.VariableNonDeclareException;
import compilateurYal.tds.entrees.Entree;
import compilateurYal.tds.symboles.Symbole;

import java.util.HashMap;

public class TableDesSymboles {

    /**
     * HashMap représentant la table des symboles : Entree -> Symbole
     */
    private HashMap<Entree, Symbole> tds;

    /**
     * instance du singleton
     */
    private static TableDesSymboles instance = new TableDesSymboles();

    /**
     * Constructeur vide
     */
    private TableDesSymboles () {
        tds = new HashMap<>();
    }

    /**
     * @return l'instance du singleton
     */
    public static TableDesSymboles getInstance () {
        return instance;
    }

    /**
     * Méthode ajoutant un élément dans la table de hachage ou lance une erreur si l'élément est déjà dans la table
     * @param e Entrée
     * @param s Symbole
     * @param n numéro de la ligne dans le fichier .yal pour lancer l'erreur
     * @throws DoubleDeclarationException en cas de double déclaration d'une variable
     */
    public void ajouter (Entree e, Symbole s, int n) throws DoubleDeclarationException {
        //si la table contient la clé il y a donc une double déclaration de cette Entrée, on lance donc une erreur, sinon on l'ajoute
        if (tds.containsKey(e)) {
            throw new DoubleDeclarationException(e, n);
        } else {
            tds.put(e, s);
        }
    }

    /**
     * Méthode identifiant un élément de la table de hachage et le renvoie s'il existe, lance une erreur sinon
     * @param e Entrée à retrouver
     * @param n numéro de la ligne dans le fichier .yal pour lancer l'erreur
     * @return le symbole correspondant à l'Entrée dans la table
     * @throws VariableNonDeclareException en case de non déclaration de la variable qu'on veut récupérer
     */
    public Symbole identifier (Entree e, int n) throws VariableNonDeclareException {
        Symbole s = tds.get(e);

        //si le symbole est null on ne l'a donc pas retrouvé dans la table, on lance donc une erreur, sinon on renvoie le symbole
        if (s == null) {
            throw new VariableNonDeclareException(e, n);
        } else {
            return s;
        }
    }

    /**
     * @return taille de la zone variable à allouer pour la prochaine variable (0, -4, -8, etc...)
     */
    public int getTailleZoneVariable () {
        return GestionnaireTailleZoneVariable.getInstance().getTailleZoneVariable();
    }

    @Override
    public String toString() {
        return tds.toString();
    }
}
