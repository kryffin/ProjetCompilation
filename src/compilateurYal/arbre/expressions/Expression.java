package compilateurYal.arbre.expressions;

import compilateurYal.arbre.ArbreAbstrait;

public abstract class Expression extends ArbreAbstrait {

    /**
     * constante statique identifiant les type non reconnus
     */
    public final static short NON_RECONNU = -1;

    /**
     * constante statique identifiant les entiers
     */
    public final static short ENTIER = 0;

    /**
     * constante statique identifiant les booléens
     */
    public final static short BOOLEEN = 1;

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
