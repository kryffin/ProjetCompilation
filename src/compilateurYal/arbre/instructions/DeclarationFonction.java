package compilateurYal.arbre.instructions;

import compilateurYal.arbre.ArbreAbstrait;
import compilateurYal.arbre.BlocDInstructions;
import compilateurYal.arbre.parametres.BlocDeParametres;
import compilateurYal.arbre.parametres.BlocDeParametresFormels;
import compilateurYal.arbre.expressions.IDF;
import compilateurYal.exceptions.AnalyseSemantiqueException;
import compilateurYal.tds.TableDesSymboles;
import compilateurYal.tds.entrees.EntreeFonction;
import compilateurYal.tds.entrees.EntreeVariable;
import compilateurYal.tds.symboles.Symbole;
import compilateurYal.tds.symboles.SymboleFonction;
import compilateurYal.tds.symboles.SymboleVariable;

public class DeclarationFonction extends Instruction {

    /**
     * identifiant de la fonction
     */
    private IDF idf;

    private ArbreAbstrait parametres;

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
     * nombre de paramètres de la fonction
     */
    private int nbParametres;

    /**
     * déplacement dans la zone variable du premier paramètre
     */
    private int deplacementPremierParam;

    /**
     * nombre de variables dans la région de la fonction
     */
    private int nVariablesRegion;

    /**
     * Constructeur par numéro de ligne
     * @param n ligne
     */
    public DeclarationFonction(IDF idf, ArbreAbstrait parametres, ArbreAbstrait declarations, ArbreAbstrait instructions, int n) {
        super(n);
        this.idf = idf;
        this.parametres = parametres;
        this.declarations = declarations;
        this.instructions = instructions;

        this.nRegion = TableDesSymboles.getInstance().getTableCourante().getNRegion();

        this.nbParametres = ((BlocDeParametresFormels)parametres).nbParametres();

        TableDesSymboles.getInstance().ajouter(new EntreeFonction(idf.getNom(), nbParametres), new SymboleFonction(nRegion, nbParametres), n);
    }

    /**
     * vérifie la fonction ainsi que ses déclarations et instructions et récupère le nombre de variables de la région
     */
    @Override
    public void verifier() {
        TableDesSymboles.getInstance().entrerRegionVerifier();
        idf.setNbParametres(nbParametres);
        idf.verifier();

        if (((BlocDeParametres)parametres).hasParams()) {
            IDF premierParam = ((DeclarationVariable)((BlocDeParametres)parametres).getParam(0)).getIDF();
            Symbole s = TableDesSymboles.getInstance().identifier(new EntreeVariable(premierParam.getNom()), noLigne);
            deplacementPremierParam = ((SymboleVariable)s).getDeplacement();
        } else {
            deplacementPremierParam = 0;
        }

        parametres.verifier();
        declarations.verifier();
        instructions.verifier();
        nVariablesRegion = TableDesSymboles.getInstance().getTableCourante().getNVariables();
        TableDesSymboles.getInstance().sortieRegionVerifier();

        boolean retourPresent = false;
        BlocDInstructions b = (BlocDInstructions)instructions;
        for (int i = 0; i < b.nbInstructions(); i++) {
            if (((Instruction)b.getInstruction(i)).instructionRetour()) {
                ((Retourne)b.getInstruction(i)).setNomFonction(idf.getNom() + nbParametres);
                retourPresent = true;
            }
        }
        if (retourPresent == false) {
            new AnalyseSemantiqueException(noLigne, "fonction terminée sans instruction de retour");
        }
    }

    /**
     * @return instructions MIPS pour l'entrée et la sortie de la fonction ainsi que son contenu
     */
    @Override
    public String toMIPS() {
        TableDesSymboles.getInstance().entrerRegionVerifier();
        StringBuilder sb = new StringBuilder();
        sb.append("                #ignorer cette fonction au lancement du mips\n" +
                    "    j " + idf.getNom() + nbParametres + "fin\n" +
                    "                #fonction " + idf.getNom() + nbParametres + "\n" +
                    idf.getNom() + nbParametres + ":\n" +
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
                    "                #réservation de l'espace pour les arguments\n" +
                    parametres.toMIPS() +
                    "                #affectation des paramètres\n");

        for (int i = 0; i < nbParametres; i++) {
            sb.append(  "    lw $v0, " + (16 + ((nbParametres * 4) + (i * 4))) + "($sp)\n" +
                        "    sw $v0, " + (deplacementPremierParam + (nbParametres * -4) + ((i + 1) * 4)) + "($s7)\n");
        }

        sb.append("                #réservation de l'espace pour les variables locales\n" +
                    declarations.toMIPS() +
                    "                #instructions de la fonction\n" +
                    instructions.toMIPS() +
                    "sortie" + idf.getNom() + nbParametres + ":\n" +
                    "                #sortie de la fonction\n" +
                    "                #restaurer le pointeur de pile" + nVariablesRegion + nRegion + "\n" +
                    "    addi $sp, $sp, " + (12 + (nVariablesRegion * 4) + (nbParametres * 4)) + "\n" +
                    "                #restaurer la base locale\n" +
                    "    lw $s7, " + (-4 + (nbParametres * -4)) + "($sp)\n" +
                    "                #restaurer le compteur ordinal\n" +
                    "    lw $ra, " + (nbParametres * -4) + "($sp)\n" +
                    "    jr $ra\n" +
                    idf.getNom() + nbParametres + "fin:\n");
        TableDesSymboles.getInstance().sortieRegionVerifier();
        return sb.toString();
    }

}
