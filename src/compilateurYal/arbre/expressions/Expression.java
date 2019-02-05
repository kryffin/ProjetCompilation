package compilateurYal.arbre.expressions;

import compilateurYal.arbre.ArbreAbstrait;

public abstract class Expression extends ArbreAbstrait {


    protected boolean estConstante = false;

    /**
     * Constructeur par numéro de ligne
     * @param n ligne
     */
    protected Expression(int n) {
        super(n) ;
    }

    public boolean estConstante () {
        return estConstante;
    }

}
