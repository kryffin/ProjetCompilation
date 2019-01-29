package compilateurYal.tests;

import compilateurYal.arbre.expressions.ConstanteEntiere;
import compilateurYal.arbre.expressions.IDF;
import compilateurYal.arbre.instructions.Affectation;
import compilateurYal.arbre.instructions.Declaration;
import compilateurYal.arbre.instructions.Ecrire;
import compilateurYal.arbre.instructions.Lire;
import compilateurYal.exceptions.DoubleDeclarationException;
import compilateurYal.exceptions.VariableNonDeclareException;
import compilateurYal.tds.TableDesSymboles;
import compilateurYal.tds.entrees.Entree;
import compilateurYal.tds.entrees.EntreeVariable;
import compilateurYal.tds.symboles.Symbole;
import compilateurYal.tds.symboles.SymboleVariable;
import org.junit.jupiter.api.Test;

class YalTest {

    @Test
    void testIDF () {

        IDF idf = new IDF("a", 0);
        idf.verifier();
        int depl = ((SymboleVariable)TableDesSymboles.getInstance().identifier(new EntreeVariable("a"), 0)).getDeplacement();

        assert (idf.getNoLigne() == 0) : "Erreur sur le numéro de ligne d'un IDF";
        assert (idf.getNom() == "a") : "Erreur sur getName() d'un IDF!";
        assert (idf.getDeplacement() == depl) : "Erreur sur le déplacement d'un IDF dans la TDS";
        assert (idf.toMIPS().equals("    lw $v0, 0($s7)\n")) : "Erreur sur le toMIPS d'un IDF";

    }

    @Test
    void testConstanteEntiere () {

        ConstanteEntiere ce = new ConstanteEntiere("36", 10);
        ce.verifier();
        assert (ce.getNoLigne() == 10) : "Erreur sur le numéro de ligne d'une constante entière";
        assert (ce.getCste() == "36") : "Erreur sur le texte d'une constan te entière";
        assert (ce.toMIPS().equals("    li $v0, 36\n")) : "";

    }

    @Test
    void testEntree () {

        Entree e = new EntreeVariable("a");
        int depl = ((SymboleVariable)TableDesSymboles.getInstance().identifier(e, 0)).getDeplacement();

        assert (e.getNom().equals("a")) : "Erreur sur le nom d'une entrée";
        assert (depl == 0) : "Erreur sur la récupération d'un symbole de la TDS par une EntreeVariable";

    }

    @Test
    void testSymbole () {

        Symbole s = new SymboleVariable(40);

        assert (((SymboleVariable) s).getDeplacement() == 40) : "Erreur sur le déplacement d'un SymboleVariable";

    }

    @Test
    void testTDS () {

        int depl = ((SymboleVariable)TableDesSymboles.getInstance().identifier(new EntreeVariable("a"), 0)).getDeplacement();
        assert (depl == 0) : "Erreur sur l'identification d'une entrée dans la TDS";

        depl = ((SymboleVariable)TableDesSymboles.getInstance().identifier(new EntreeVariable("b"), 0)).getDeplacement();
        assert (depl == -4) : "Erreur sur l'identification d'une entrée dans la TDS";

        depl = ((SymboleVariable)TableDesSymboles.getInstance().identifier(new EntreeVariable("c"), 0)).getDeplacement();
        assert (depl == -8) : "Erreur sur l'identification d'une entrée dans la TDS";

    }

    @Test
    void testLire () {

        //lecture et stockage dans un IDF
        Lire l = new Lire(new IDF("c", 0), 0);
        l.verifier();
        String resultat =   "                #lecture de la sortie standard dans la variable c\n" +
                            "    li $v0, 5\n" +
                            "    syscall\n" +
                            "    sw $v0, -8($s7)\n";

        assert (l.getNoLigne() == 0) : "Erreur sur le numéro de ligne de Lire";
        assert (l.toMIPS().equals(resultat)) : "Erreur sur le toMIPS de Lire";

    }

    @Test
    void testEcrire () {

        //écriture d'une constante entière
        Ecrire e = new Ecrire(new ConstanteEntiere("35", 0), 0);
        e.verifier();
        String resultat =   "                # affichage de l'expression\n" +
                            "    li $v0, 35\n" +
                            "    move $a0, $v0\n" +
                            "    li $v0, 1\n" +
                            "    syscall\n" +
                            "    li $v0, 4      # retour à la ligne\n" +
                            "    la $a0, finLigne\n" +
                            "    syscall\n";

        assert (e.getNoLigne() == 0) : "Erreur sur le numéro de ligne de Ecrire d'une constante entière";
        assert (e.toMIPS().equals(resultat)) : "Erreur sur le toMIPS de Ecrire";

        //écriture d'un IDF
        e = new Ecrire(new IDF("b", 0), 0);
        e.verifier();
        resultat =  "                # affichage de l'expression\n" +
                    "    lw $v0, -4($s7)\n" +
                    "    move $a0, $v0\n" +
                    "    li $v0, 1\n" +
                    "    syscall\n" +
                    "    li $v0, 4      # retour à la ligne\n" +
                    "    la $a0, finLigne\n" +
                    "    syscall\n";

        assert (e.getNoLigne() == 0) : "Erreur sur le numéro de ligne de Ecrire d'un IDF";
        assert (e.toMIPS().equals(resultat)) : "Erreur sur le toMIPS de Ecrire";

    }

    @Test
    void testDeclaration () {

        Declaration d = new Declaration(new IDF("d", 0), 0);
        d.verifier();
        String resultat =   "                #entier d\n" +
                            "    add $sp, $sp, -4\n\n";

        assert (d.getNoLigne() == 0) : "Erreur sur le numéro de ligne de Declaration";
        assert (d.toMIPS().equals(resultat)) : "Erreur sur le toMIPS d'une Declaration";

    }

    @Test
    void testAffectation () {

        //affectation d'une constante entière
        Affectation a = new Affectation(new IDF("a", 0), new ConstanteEntiere("9", 0), 0);
        a.verifier();
        String resultat =   "                #affectation de 9 dans a\n" +
                            "    li $v0, 9\n" +
                            "    sw $v0, 0($s7)\n\n";

        assert (a.toMIPS().equals(resultat)) : "Erreur sur le toMIPS d'une Affectation d'une Constante Entière";

        //affectation d'un IDF
        a = new Affectation(new IDF("a", 0), new IDF("c", 0), 0);
        a.verifier();
        resultat =  "                #affectation de c dans a\n" +
                    "    lw $v0, -8($s7)\n" +
                    "    sw $v0, 0($s7)\n\n";

        assert (a.toMIPS().equals(resultat)) : "Erreur sur le toMIPS d'une Affectation d'un IDF";

    }

    @Test
    void testException () {

        //test d'une variable non déclarée
        boolean exception = false;

        try {
            TableDesSymboles.getInstance().identifier(new EntreeVariable("e"), 0);
        } catch (VariableNonDeclareException e) {
            exception = true;
        }

        assert (exception) : "Erreur sur le lancement d'une exception pour variable non déclarée";

        //test d'une double déclaration
        exception = false;

        try {
            TableDesSymboles.getInstance().ajouter(new EntreeVariable("a"), new SymboleVariable(0), 0);
        } catch (DoubleDeclarationException e) {
            exception = true;
        }

        assert (exception) : "Erreur sur le lancement d'une exception pour variable non déclarée";

    }

    @org.junit.jupiter.api.Test
    void main() {

        TableDesSymboles.getInstance().ajouter(new EntreeVariable("a"), new SymboleVariable(0), 0);
        TableDesSymboles.getInstance().ajouter(new EntreeVariable("b"), new SymboleVariable(-4), 0);
        TableDesSymboles.getInstance().ajouter(new EntreeVariable("c"), new SymboleVariable(-8), 0);

        testIDF();
        testConstanteEntiere();
        testEntree();
        testSymbole();
        testTDS();
        testLire();
        testEcrire();
        testDeclaration();
        testAffectation();
        testException();

    }
}