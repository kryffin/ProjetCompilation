package compilateurYal.arbre.expressions;

public abstract class Constante extends Expression {

    /**
     * texte représentant la constante
     */
    protected String cste ;

    /**
     * Constructeur par texte de la constante et numéro de ligne et stipulant que cette expression est donc constante
     * @param texte texte représentant la constante
     * @param n ligne
     */
    protected Constante(String texte, int n) {
        super(n) ;
        cste = texte ;
        estConstante = true;
    }

    /**
     * @return texte représentant la constante
     */
    public String getCste () {
        return cste;
    }

    /**
     * vérifie la constante
     */
    @Override
    public void verifier() {
    }

    @Override
    public String toString() {
        return cste ;
    }

}
