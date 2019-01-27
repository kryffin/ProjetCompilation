package compilateurYal.arbre;

public class Programme extends ArbreAbstrait {

    protected ArbreAbstrait declarations;
    protected ArbreAbstrait instructions;

    protected static String zoneData =  ".data\n" +
                                        "finLigne:     .asciiz \"\\n\"\n" +
                                        "              .align 2\n" ;

    protected static String debutCode = ".text\n" +
                                        "main :\n" ;

    protected static String finCode =   "end :\n" +
                                        "    li $v0, 10            # retour au système\n" +
                                        "    syscall\n" ;

    public Programme (int n) {
        super(n);
    }

    public void ajouterDeclarations (ArbreAbstrait d) {
        declarations = d;
    }

    public void ajouterInstructions (ArbreAbstrait i) {
        instructions = i;
    }

    @Override
    public void verifier() {
        declarations.verifier();
        instructions.verifier();
    }

    @Override
    public String toMIPS() {
        StringBuilder sb = new StringBuilder("") ;
        sb.append(zoneData) ;
        sb.append(debutCode) ;
        sb.append(declarations.toMIPS()) ;
        sb.append(instructions.toMIPS()) ;
        sb.append(finCode) ;
        return sb.toString() ;
    }
}
