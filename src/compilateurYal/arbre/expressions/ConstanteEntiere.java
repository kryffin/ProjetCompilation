package compilateurYal.arbre.expressions;

public class ConstanteEntiere extends Constante {

    /**
     * Constructeur par texte de la constante et numéro de ligne
     * @param texte texte représentant la constante
     * @param n ligne
     */
    public ConstanteEntiere(String texte, int n) {
        super(texte, n) ;
        estConstante = true;
    }

    /**
     * @return instruction mips du chargement de la constante dans $v0
     */
    @Override
    public String toMIPS() {
        return "    li $v0, " + cste + "\n";
    }

}
