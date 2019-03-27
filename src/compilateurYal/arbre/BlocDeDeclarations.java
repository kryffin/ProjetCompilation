package compilateurYal.arbre;

import compilateurYal.GestionnaireTailleZoneVariable;
import compilateurYal.arbre.instructions.DeclarationTableau;
import compilateurYal.tds.TableDesSymboles;
import compilateurYal.tds.entrees.EntreeTableau;
import compilateurYal.tds.symboles.SymboleTableau;

import java.util.ArrayList;

public class BlocDeDeclarations extends ArbreAbstrait {

    /**
     * liste des arbres abstraits représentants des déclarations
     */
    private ArrayList<ArbreAbstrait> declarations;

    private ArrayList<String> tableaux;

    /**
     * Constructeur par numéro de ligne
     * @param n ligne
     */
    public BlocDeDeclarations(int n) {
        super(n);
        declarations = new ArrayList<>();
        tableaux = new ArrayList<>();
    }

    /**
     * Méthdoe ajoutant une déclaration à la liste
     * @param a arbre abstrait représentant la déclaration à ajouter
     */
    public void ajouter (ArbreAbstrait a) {
        declarations.add(a);
    }

    public void ajouterTableau (ArbreAbstrait a) {
        declarations.add(a);
        DeclarationTableau dt = (DeclarationTableau) a;
        tableaux.add(dt.getNom());
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

        //calcul du déplacement par rapport à la base
        int deplacementCasesTableaux = GestionnaireTailleZoneVariable.getInstance().getTailleZoneVariable();
        sb.append("                #déplacement de base du premier tableau");
        sb.append("    addi $s0, $zero, " + deplacementCasesTableaux + "\n");

        //pour chaque tableau déclarés
        for (String nom : tableaux) {
            SymboleTableau st = (SymboleTableau)TableDesSymboles.getInstance().identifier(new EntreeTableau(nom), noLigne);

            //sauvegarde de s0 (déplacement) dans la case variable du tableau
            sb.append("                #chargement de ce déplacement dans la case variable de " + nom + "\n");
            sb.append("    sw $s0, " + st.getDeplacement() + "($s7)\n");

            //boucle instanciant les cases du tableau
            sb.append("        #boucle d'instanciation des cases de " + nom + "\n");
            sb.append("        #récupération de la taille du tableau pour instancier un foreach\n");
            sb.append(st.getTaille().toMIPS());
            sb.append("cases" + nom + ":\n");
            sb.append("    beq $v0, $zero, fincases" + nom + "\n");
            sb.append("    add $sp, $sp, -4\n");
            sb.append("                #incrémentation du déplacement pour les futurs tableau (s0)\n");
            sb.append("    subi $s0, $s0, 4\n");
            sb.append("    subi $v0, $v0, 1\n");
            sb.append("    j cases" + nom + "\n");
            sb.append("fincases" + nom + ":\n\n");
        }

        return sb.toString() ;
    }
}
