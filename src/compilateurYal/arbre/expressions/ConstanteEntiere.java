package compilateurYal.arbre.expressions;

public class ConstanteEntiere extends Constante {

    /**
     * Constructeur par texte de la constante et numéro de ligne
     * @param texte texte représentant la constante
     * @param n ligne
     */
    public ConstanteEntiere(String texte, int n) {
        super(texte, n) ;
    }

    /**
     * @return instruction mips du chargement de la constante dans $v0
     */
    @Override
    public String toMIPS() {
        StringBuilder sb = new StringBuilder("") ;
        sb.append("    li $v0, ") ;
        sb.append(cste) ;
        sb.append("\n") ;
        return sb.toString() ;
    }

}
