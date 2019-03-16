package compilateurYal.arbre.parametres;

import compilateurYal.arbre.ArbreAbstrait;

import java.util.ArrayList;

public abstract class BlocDeParametres extends ArbreAbstrait {

    /**
     * Liste de paramètres
     */
    protected ArrayList<ArbreAbstrait> parametres;

    /**
     * Constructeur par numéro de ligne
     *
     * @param n ligne
     */
    public BlocDeParametres(int n) {
        super(n);
        parametres = new ArrayList<>();
    }

    /**
     * Ajoute un paramètre à la liste
     * @param a paramètre
     */
    public void ajouter (ArbreAbstrait a) {
        parametres.add(a);
    }

    /**
     * @return nombre de paramètres de lka liste
     */
    public int nbParametres () {
        return parametres.size();
    }

    /**
     * @return vrai si la fonction contient des paramètres, faux sinon
     */
    public boolean hasParams () {
        return parametres.size() != 0;
    }

    /**
     * Retourne le paramètre à la position i
     * @param i paramètre à retourner
     * @return paramètre à la position i
     */
    public ArbreAbstrait getParam (int i) {
        return parametres.get(i);
    }

    @Override
    public void verifier() {
        for (ArbreAbstrait a : parametres) {
            a.verifier();
        }
    }

    @Override
    public abstract String toMIPS();
}
