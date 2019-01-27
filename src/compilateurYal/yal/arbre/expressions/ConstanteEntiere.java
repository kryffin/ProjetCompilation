package compilateurYal.yal.arbre.expressions;

import compilateurYal.yal.arbre.ArbreAbstrait;

public class ConstanteEntiere extends Constante {
    
    public ConstanteEntiere(String texte, int n) {
        super(texte, n) ;
    }

    @Override
    public String toMIPS() {
        StringBuilder sb = new StringBuilder("") ;
        sb.append("    li $v0, ") ;
        sb.append(cste) ;
        sb.append("\n") ;
        return sb.toString() ;
    }


}
