package compilateurYal.arbre.expressions;

import compilateurYal.arbre.ArbreAbstrait;

public abstract class Expression extends ArbreAbstrait {

    /**
     * défini si une expression est constante ou non
     */
    protected boolean estConstante = false;

    /**
     * défini si une expression est logique ou non
     */
    protected boolean estLogique = false;

    /**
     * Constructeur par numéro de ligne
     * @param n ligne
     */
    protected Expression(int n) {
        super(n) ;
    }

    /**
     * @return vrai si l'expression est constante, faux sinon
     */
    public boolean estConstante () {
        return estConstante;
    }

    /**
     * @return vrai si l'expression est logique, faux sinon
     */
    public boolean estLogique () {
        return estLogique;
    }

}
