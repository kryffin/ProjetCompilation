package compilateurYal.arbre;

public class Programme extends ArbreAbstrait {

    /**
     * arbre abstrait représentant les déclarations du programme
     */
    protected ArbreAbstrait declarations;

    /**
     * arbre abstrait représentant les instructions du programme
     */
    protected ArbreAbstrait instructions;

    /**
     * zone mips représentant la data
     */
    protected static String zoneData =  ".data\n" +
                                        "finLigne:     .asciiz \"\\n\"\n" +
                                        "vrai:         .asciiz \"vrai\"\n" +
                                        "faux:         .asciiz \"faux\"\n" +
                                        "              .align 2\n";

    /**
     * début du code en mips
     */
    protected static String debutCode = ".text\n" +
                                        "main :\n" ;

    /**
     * fin du code en mips
     */
    protected static String finCode =   "end :\n" +
                                        "    li $v0, 10            # retour au système\n" +
                                        "    syscall\n" ;

    /**
     * Constructeur par numéro de ligne
     * @param n ligne
     */
    public Programme (ArbreAbstrait declarations, ArbreAbstrait instructions, int n) {
        super(n);
        this.declarations = declarations;
        this.instructions = instructions;
    }

    /**
     * vérifie le bloc de déclarations et d'instructions
     */
    @Override
    public void verifier() {
        declarations.verifier();
        instructions.verifier();
    }

    /**
     * Convertit les blocs de déclarations et d'instructions en instructions mips et les concatènes aux zone mips pour en faire un code valide et complet
     * @return chaîne de caractères représentant le programme mips
     */
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
