package compilateurYal.yal.arbre;

import java.util.ArrayList;

public class BlocDeDeclarations extends ArbreAbstrait {

    private ArrayList<ArbreAbstrait> programme;

    public BlocDeDeclarations(int i){
        super(i);
        programme = new ArrayList<>();
    }

    public void ajouter(ArbreAbstrait a) {
        programme.add(a) ;
    }

    @Override
    public void verifier() {
        for (ArbreAbstrait a : programme) {
            a.verifier() ;
        }
    }

    @Override
    public String toMIPS() {
        StringBuilder sb = new StringBuilder("") ;
        for (ArbreAbstrait a : programme) {
            sb.append(a.toMIPS()) ;
        }
        return sb.toString() ;    }
}
