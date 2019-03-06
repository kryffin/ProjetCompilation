package compilateurYal.arbre.instructions;

import com.sun.tools.javac.util.Name;
import compilateurYal.CompteurRegions;
import compilateurYal.arbre.ArbreAbstrait;
import compilateurYal.arbre.expressions.IDF;
import compilateurYal.tds.TableDesSymboles;
import compilateurYal.tds.entrees.EntreeFonction;
import compilateurYal.tds.symboles.SymboleFonction;

public class DeclarationFonction extends Instruction {

    /**
     * identifiant de la fonction
     */
    private IDF idf;

    /**
     * liste de déclarations dans la fonction
     */
    private ArbreAbstrait declarations;

    /**
     * liste des instructions dans la fonction
     */
    private ArbreAbstrait instructions;

    /**
     * numéro de région de la fonction
     */
    private int nRegion;

    /**
     * nombre de variables dans la région de la fonction
     */
    private int nVariablesRegion;

    /**
     * Constructeur par numéro de ligne
     * @param n ligne
     */
    public DeclarationFonction(IDF idf, ArbreAbstrait declarations, ArbreAbstrait instructions, int n) {
        super(n);
        this.idf = idf;
        this.declarations = declarations;
        this.instructions = instructions;

        nRegion = TableDesSymboles.getInstance().getTableCourante().getNRegion();

        TableDesSymboles.getInstance().ajouter(new EntreeFonction(idf.getNom()), new SymboleFonction(nRegion), n);
    }

    /**
     * vérifie la fonction ainsi que ses déclarations et instructions et récupère le nombre de variables de la région
     */
    @Override
    public void verifier() {
        TableDesSymboles.getInstance().entrerRegionVerifier();
        idf.verifier();
        declarations.verifier();
        instructions.verifier();
        nVariablesRegion = TableDesSymboles.getInstance().getTableCourante().getNVariables();
        TableDesSymboles.getInstance().sortieRegionVerifier();
    }

    /**
     * @return instructions MIPS pour l'entrée et la sortie de la fonction ainsi que son contenu
     */
    @Override
    public String toMIPS() {
        TableDesSymboles.getInstance().entrerRegionVerifier();
        String s =  "                #ignorer cette fonction au lancement du mips\n" +
                "    j " + idf.getNom() + nRegion + "fin\n" +
                "                #fonction " + idf.getNom() + ", région : " + nRegion + "\n" +
                idf.getNom() + nRegion + ":\n" +
                "                #entrer dans la fonction\n" +
                "                #empiler la valeur de retour\n" +
                "    sw $ra, ($sp)\n" +
                "    addi $sp, $sp, -4\n" +
                "                #sauvegarde de la base locale\n" +
                "    sw $s7, ($sp)\n" +
                "    addi $sp, $sp, -4\n" +
                "                #empiler le numéro de région\n" +
                "    li $v0, " + nRegion + "\n" +
                "    sw $v0, ($sp)\n" +
                "    addi $sp, $sp, -4\n" +
                "                #initialisation de la base locale\n" +
                "    move $s7, $sp\n" +
                "                #réservation de l'espace pour les variables locales\n" +
                declarations.toMIPS() +
                instructions.toMIPS() +
                "                #sortie de la fonction\n" +
                "                #restaurer le pointeur de pile\n" +
                "    addi $sp, $sp, " + (12 + (nVariablesRegion * 4)) + "\n" +
                "                #restaurer la base locale\n" +
                "    lw $s7, -4($sp)\n" +
                "                #restaurer le compteur ordinal\n" +
                "    lw $ra, ($sp)\n" +
                "    jr $ra\n" +
                idf.getNom() + nRegion + "fin:\n";
        TableDesSymboles.getInstance().sortieRegionVerifier();
        return s;
    }

}
