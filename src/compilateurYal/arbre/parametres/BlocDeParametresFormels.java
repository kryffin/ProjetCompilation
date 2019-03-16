package compilateurYal.arbre.parametres;

import compilateurYal.arbre.ArbreAbstrait;

public class BlocDeParametresFormels extends BlocDeParametres {

    //Les paramètres formels sont les paramètres décrit à la déclaration d'une fonction

    /**
     * Constructeur par numéro de ligne
     *
     * @param n ligne
     */
    public BlocDeParametresFormels(int n) {
        super(n);
    }

    @Override
    public String toMIPS() {
        StringBuilder sb = new StringBuilder();
        for (ArbreAbstrait a : parametres) {
            sb.append(a.toMIPS());
        }
        return sb.toString();
    }

}
