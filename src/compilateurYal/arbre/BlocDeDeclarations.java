package compilateurYal.arbre;

import java.util.ArrayList;

public class BlocDeDeclarations extends ArbreAbstrait {

    private ArrayList<ArbreAbstrait> declarations;

    public BlocDeDeclarations(int n) {
        super(n);
        declarations = new ArrayList<>();
    }

    public void ajouter (ArbreAbstrait a) {
        declarations.add(a);
    }

    @Override
    public void verifier() {
        for (ArbreAbstrait a : declarations) {
            a.verifier();
        }
    }

    @Override
    public String toMIPS() {
        StringBuilder sb = new StringBuilder("") ;
        sb.append("                #allocation de la place mémoire pour les variables\n");
        sb.append("    move $s7, $sp\n\n");
        for (ArbreAbstrait a : declarations) {
            sb.append(a.toMIPS()) ;
        }
        return sb.toString() ;
    }
}
