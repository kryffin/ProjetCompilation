package compilateurYal.arbre;

import java.util.ArrayList;

/**
 * 21 novembre 2018
 *
 * @author brigitte wrobel-dautcourt
 */

public class BlocDInstructions extends ArbreAbstrait {

    /**
     * liste des arbres abstraits représentants des instructions
     */
    protected ArrayList<ArbreAbstrait> instructions ;

    /**
     * Constructeur par numéro de ligne
     * @param n ligne
     */
    public BlocDInstructions(int n) {
        super(n) ;
        instructions = new ArrayList<>() ;
    }

    /**
     * Méthode ajoutant une instruction à la liste
     * @param a arbre abstrait représentant l'instruction à ajouter
     */
    public void ajouter(ArbreAbstrait a) {
        instructions.add(a) ;
    }

    /**
     * Retourne l'instruction à la position i
     * @param i indice de l'instruction
     * @return instruction à la position i
     */
    public ArbreAbstrait getInstruction (int i) {
        return instructions.get(i);
    }

    /**
     * @return nombre d'instructions du bloc
     */
    public int nbInstructions () {
        return instructions.size();
    }
    
    @Override
    public String toString() {
        return instructions.toString() ;
    }

    /**
     * vérifie les instructions une à une
     */
    @Override
    public void verifier() {
        for (ArbreAbstrait a : instructions) {
            a.verifier() ;
        }
    }

    /**
     * Convertit chaque instructions en instructions mips
     * @return chaîne de caractères représentant les instructions en mips
     */
    @Override
    public String toMIPS() {
        StringBuilder sb = new StringBuilder("") ;
        for (ArbreAbstrait a : instructions) {
            sb.append(a.toMIPS()) ;
        }
        return sb.toString() ;
    }

}
