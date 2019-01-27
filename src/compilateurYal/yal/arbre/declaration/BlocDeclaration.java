package compilateurYal.yal.arbre.declaration;

import compilateurYal.yal.arbre.ArbreAbstrait;

import java.util.ArrayList;

public class BlocDeclaration extends ArbreAbstrait {

    private ArrayList<ArbreAbstrait> declarations;

    protected static String zoneData = ".data\n" +
            "finLigne:     .asciiz \"\\n\"\n" +
            "              .align 2\n" ;

    protected static String debutCode = ".text\n" +
            "main :\n" ;


    protected BlocDeclaration(int n) {
        super(n);
        declarations = new ArrayList<>();
    }

    public void ajouter(ArbreAbstrait... a) {

        for(ArbreAbstrait arbreAbstrait : a){
            declarations.add(arbreAbstrait) ;
        }
    }

    @Override
    public void verifier() {

    }

    @Override
    public String toMIPS() {
        StringBuilder sb = new StringBuilder("") ;
        sb.append(zoneData) ;
        sb.append(debutCode) ;
        for (ArbreAbstrait a : declarations) {
            sb.append(a.toMIPS()) ;
    }
        return sb.toString() ;
    }
}
