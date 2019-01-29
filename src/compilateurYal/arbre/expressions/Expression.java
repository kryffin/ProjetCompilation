package compilateurYal.arbre.expressions;

import compilateurYal.arbre.ArbreAbstrait;

public abstract class Expression extends ArbreAbstrait {

    /**
     * Constructeur par numéro de ligne
     * @param n ligne
     */
    protected Expression(int n) {
        super(n) ;
    }


}
