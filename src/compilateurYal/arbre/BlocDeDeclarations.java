package compilateurYal.arbre;

import java.util.ArrayList;

public class BlocDeDeclarations extends ArbreAbstrait {

    /**
     * liste des arbres abstraits représentants des déclarations
     */
    private ArrayList<ArbreAbstrait> declarations;

    /**
     * Constructeur par numéro de ligne
     * @param n ligne
     */
    public BlocDeDeclarations(int n) {
        super(n);
        declarations = new ArrayList<>();
    }

    /**
     * Méthdoe ajoutant une déclaration à la liste
     * @param a arbre abstrait représentant la déclaration à ajouter
     */
    public void ajouter (ArbreAbstrait a) {
        declarations.add(a);
    }

    /**
     * vérifie les déclarations une à une
     */
    @Override
    public void verifier() {
        for (ArbreAbstrait a : declarations) {
            a.verifier();
        }
    }

    /**
     * Convertit chaque déclarations en instructions mips
     * @return chaîne de caractères représentant les instructions en mips
     */
    @Override
    public String toMIPS() {
        StringBuilder sb = new StringBuilder("") ;
        for (ArbreAbstrait a : declarations) {
            sb.append(a.toMIPS()) ;
        }
        return sb.toString() ;
    }
}
