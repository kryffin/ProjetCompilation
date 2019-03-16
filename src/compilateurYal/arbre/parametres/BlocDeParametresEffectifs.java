package compilateurYal.arbre.parametres;

import compilateurYal.arbre.ArbreAbstrait;

public class BlocDeParametresEffectifs extends BlocDeParametres {

    //Les paramètres effectifs sont les paramètres décrit à l'appel d'une fonction

    /**
     * Constructeur par numéro de ligne
     *
     * @param n ligne
     */
    public BlocDeParametresEffectifs(int n) {
        super(n);
    }

    @Override
    public String toMIPS() {
        StringBuilder sb = new StringBuilder();
        for (ArbreAbstrait a : parametres) {
            sb.append(a.toMIPS());
            sb.append("    sw $v0, ($sp)\n");
            sb.append("    addi $sp, $sp, -4\n");
        }
        return sb.toString();
    }
}
