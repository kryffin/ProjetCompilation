package compilateurYal.yal.arbre;

import compilateurYal.yal.arbre.instructions.Instruction;

import java.util.ArrayList;

public class Programme extends ArbreAbstrait {

    private ArbreAbstrait instructions;
    private ArbreAbstrait declarations;

    protected static String zoneData = ".data\n" +
                                        "finLigne:     .asciiz \"\\n\"\n" +
                                        "              .align 2\n" ;

    protected static String debutCode = ".text\n" +
                                        "main :\n" ;

    protected static String finCode = "end :\n" +
                                        "    li $v0, 10            # retour au système\n" +
                                        "    syscall\n" ;


    public Programme(int i){
        super(i);
    }

    public void ajouterBlocDInstruction(ArbreAbstrait bi){
        instructions = bi;
    }

    public void ajouterBlocDeDeclarations(ArbreAbstrait bd){
        declarations = bd;
    }

    @Override
    public void verifier() {
        instructions.verifier();
        declarations.verifier();
    }

    @Override
    public String toMIPS() {
        StringBuilder sb = new StringBuilder("") ;
        sb.append(zoneData) ;
        sb.append(debutCode) ;
        sb.append(declarations.toMIPS());
        sb.append(instructions.toMIPS());
        sb.append(finCode) ;
        return sb.toString() ;
    }
}
