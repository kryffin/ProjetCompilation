package compilateurYal.yal.arbre.instructions;

import compilateurYal.yal.arbre.ArbreAbstrait;
import compilateurYal.yal.exceptions.AnalyseSemantiqueException;

import java.util.ArrayList;

/**
 * 21 novembre 2018
 *
 * @author brigitte wrobel-dautcourt
 */

public class BlocDInstructions extends ArbreAbstrait {
    
    protected ArrayList<ArbreAbstrait> programme ;

    protected static String finCode = "end :\n" +
                                      "    li $v0, 10            # retour au système\n" +
                                      "    syscall\n" ;

    public BlocDInstructions(int n) {
        super(n) ;
        programme = new ArrayList<>() ;
    }
    
    public void ajouter(ArbreAbstrait... a) {

        for(ArbreAbstrait arb : a){
            programme.add(arb) ;
        }
    }
    
    @Override
    public String toString() {
        return programme.toString() ;
    }

    @Override
    public void verifier() throws AnalyseSemantiqueException {
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
        sb.append(finCode) ;
        return sb.toString() ;
    }

}
