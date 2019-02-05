package compilateurYal.tests;

import compilateurYal.arbre.BlocDInstructions;
import compilateurYal.arbre.expressions.ConstanteEntiere;
import compilateurYal.arbre.expressions.IDF;
import compilateurYal.arbre.expressions.arithmetique.Addition;
import compilateurYal.arbre.expressions.arithmetique.Division;
import compilateurYal.arbre.expressions.arithmetique.Multiplication;
import compilateurYal.arbre.expressions.arithmetique.Soustraction;
import compilateurYal.arbre.expressions.logique.*;
import compilateurYal.arbre.instructions.*;
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
        String resultat =   "                #affichage de 35\n" +
                            "    li $v0, 35\n" +
                            "    move $a0, $v0\n" +
                            "    li $v0, 1\n" +
                            "    syscall\n" +
                            "                #retour à la ligne\n" +
                            "    li $v0, 4\n" +
                            "    la $a0, finLigne\n" +
                            "    syscall\n\n";

        assert (e.getNoLigne() == 0) : "Erreur sur le numéro de ligne de Ecrire d'une constante entière";
        assert (e.toMIPS().equals(resultat)) : "Erreur sur le toMIPS de Ecrire";

        //écriture d'un IDF
        e = new Ecrire(new IDF("b", 0), 0);
        e.verifier();
        resultat =  "                #affichage de b\n" +
                    "    lw $v0, -4($s7)\n" +
                    "    move $a0, $v0\n" +
                    "    li $v0, 1\n" +
                    "    syscall\n" +
                    "                #retour à la ligne\n" +
                    "    li $v0, 4\n" +
                    "    la $a0, finLigne\n" +
                    "    syscall\n\n";

        assert (e.getNoLigne() == 0) : "Erreur sur le numéro de ligne de Ecrire d'un IDF";
        assert (e.toMIPS().equals(resultat)) : "Erreur sur le toMIPS de Ecrire2";

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

    @Test
    void testAddition () {

        String resultat;

        //addition entre 2 variables
        Addition add = new Addition(new IDF("a", 0), new IDF("b", 0), 0);
        add.verifier();

        resultat =  "    lw $v0, 0($s7)\n" +
                    "    sw $v0, ($sp)\n" +
                    "    add $sp, $sp, -4\n" +
                    "    lw $v0, -4($s7)\n" +
                    "    add $sp, $sp, 4\n" +
                    "    lw $t8, ($sp)\n" +
                    "    add $v0, $t8, $v0\n";

        assert(add.toMIPS().equals(resultat)) : "Erreur sur le toMIPS d'une addition";

        //addition entre une variable et une constante
        add = new Addition(new IDF("c", 0), new ConstanteEntiere("4", 0), 0);
        add.verifier();

        resultat =  "    lw $v0, -8($s7)\n" +
                    "    move $t8, $v0\n" +
                    "    li $v0, 4\n" +
                    "    add $v0, $t8, $v0\n";

        assert(add.toMIPS().equals(resultat)) : "Erreur sur le toMIPS d'une addition";

        //addition entre deux variables
        add = new Addition(new ConstanteEntiere("3", 0), new ConstanteEntiere("4", 0), 0);
        add.verifier();

        resultat =  "    li $v0, 3\n" +
                    "    move $t8, $v0\n" +
                    "    li $v0, 4\n" +
                    "    add $v0, $t8, $v0\n";

        assert(add.toMIPS().equals(resultat)) : "Erreur sur le toMIPS d'une addition";

    }

    @Test
    void testSoustraction () {

        String resultat;

        //soustraction entre 2 variables
        Soustraction sub = new Soustraction(new IDF("a", 0), new IDF("b", 0), 0);
        sub.verifier();

        resultat =  "    lw $v0, 0($s7)\n" +
                    "    sw $v0, ($sp)\n" +
                    "    add $sp, $sp, -4\n" +
                    "    lw $v0, -4($s7)\n" +
                    "    add $sp, $sp, 4\n" +
                    "    lw $t8, ($sp)\n" +
                    "    sub $v0, $t8, $v0\n";

        assert(sub.toMIPS().equals(resultat)) : "Erreur sur le toMIPS d'une soustraction";

        //soustraction entre une variable et une constante
        sub = new Soustraction(new IDF("c", 0), new ConstanteEntiere("4", 0), 0);
        sub.verifier();

        resultat =  "    lw $v0, -8($s7)\n" +
                    "    move $t8, $v0\n" +
                    "    li $v0, 4\n" +
                    "    sub $v0, $t8, $v0\n";

        assert(sub.toMIPS().equals(resultat)) : "Erreur sur le toMIPS d'une soustraction";

        //soustraction entre deux variables
        sub = new Soustraction(new ConstanteEntiere("3", 0), new ConstanteEntiere("4", 0), 0);
        sub.verifier();

        resultat =  "    li $v0, 3\n" +
                    "    move $t8, $v0\n" +
                    "    li $v0, 4\n" +
                    "    sub $v0, $t8, $v0\n";

        assert(sub.toMIPS().equals(resultat)) : "Erreur sur le toMIPS d'une soustraction";

    }

    @Test
    void testMultiplication () {

        String resultat;

        //multiplication entre 2 variables
        Multiplication mult = new Multiplication(new IDF("a", 0), new IDF("b", 0), 0);
        mult.verifier();

        resultat =  "    lw $v0, 0($s7)\n" +
                    "    sw $v0, ($sp)\n" +
                    "    add $sp, $sp, -4\n" +
                    "    lw $v0, -4($s7)\n" +
                    "    add $sp, $sp, 4\n" +
                    "    lw $t8, ($sp)\n" +
                    "    mult $t8, $v0\n" +
                    "    mflo $v0\n";

        assert(mult.toMIPS().equals(resultat)) : "Erreur sur le toMIPS d'une multiplication";

        //multiplication entre une variable et une constante
        mult = new Multiplication(new IDF("c", 0), new ConstanteEntiere("4", 0), 0);
        mult.verifier();

        resultat =  "    lw $v0, -8($s7)\n" +
                    "    move $t8, $v0\n" +
                    "    li $v0, 4\n" +
                    "    mult $t8, $v0\n" +
                    "    mflo $v0\n";

        assert(mult.toMIPS().equals(resultat)) : "Erreur sur le toMIPS d'une multiplication";

        //multiplication entre deux variables
        mult = new Multiplication(new ConstanteEntiere("3", 0), new ConstanteEntiere("4", 0), 0);
        mult.verifier();

        resultat =  "    li $v0, 3\n" +
                    "    move $t8, $v0\n" +
                    "    li $v0, 4\n" +
                    "    mult $t8, $v0\n" +
                    "    mflo $v0\n";

        assert(mult.toMIPS().equals(resultat)) : "Erreur sur le toMIPS d'une multiplication";

    }

    @Test
    void testDivision () {

        String resultat;

        //division entre 2 variables
        Division div = new Division(new IDF("a", 0), new IDF("b", 0), 0);
        div.verifier();

        resultat =  "    lw $v0, 0($s7)\n" +
                    "    sw $v0, ($sp)\n" +
                    "    add $sp, $sp, -4\n" +
                    "    lw $v0, -4($s7)\n" +
                    "    add $sp, $sp, 4\n" +
                    "    lw $t8, ($sp)\n" +
                    "    div $t8, $v0\n" +
                    "    mflo $v0\n";

        assert(div.toMIPS().equals(resultat)) : "Erreur sur le toMIPS d'une division";

        //division entre une variable et une constante
        div = new Division(new IDF("c", 0), new ConstanteEntiere("4", 0), 0);
        div.verifier();

        resultat =  "    lw $v0, -8($s7)\n" +
                    "    move $t8, $v0\n" +
                    "    li $v0, 4\n" +
                    "    div $t8, $v0\n" +
                    "    mflo $v0\n";

        assert(div.toMIPS().equals(resultat)) : "Erreur sur le toMIPS d'une division";

        //division entre deux variables
        div = new Division(new ConstanteEntiere("3", 0), new ConstanteEntiere("4", 0), 0);
        div.verifier();

        resultat =  "    li $v0, 3\n" +
                    "    move $t8, $v0\n" +
                    "    li $v0, 4\n" +
                    "    div $t8, $v0\n" +
                    "    mflo $v0\n";

        assert(div.toMIPS().equals(resultat)) : "Erreur sur le toMIPS d'une division";

    }

    @Test
    void testOperationsArithmetiques () {
        testAddition();
        testSoustraction();
        testMultiplication();
        testDivision();
    }

    @Test
    void testEgal () {

        String resultat;

        //egalité entre 2 variables
        Egal eg = new Egal(new IDF("a", 0), new IDF("b", 0), 0);
        eg.verifier();

        resultat =  "    lw $v0, 0($s7)\n" +
                    "    sw $v0, ($sp)\n" +
                    "    add $sp, $sp, -4\n" +
                    "    lw $v0, -4($s7)\n" +
                    "    add $sp, $sp, 4\n" +
                    "    lw $t8, ($sp)\n" +
                    "    seq $v0, $t8, $v0\n";

        assert(eg.toMIPS().equals(resultat)) : "Erreur sur le toMIPS d'une egalité";

        //egalité entre une variable et une constante
        eg = new Egal(new IDF("c", 0), new ConstanteEntiere("1", 0), 0);
        eg.verifier();

        resultat =  "    lw $v0, -8($s7)\n" +
                    "    move $t8, $v0\n" +
                    "    li $v0, 1\n" +
                    "    seq $v0, $t8, $v0\n";

        assert(eg.toMIPS().equals(resultat)) : "Erreur sur le toMIPS d'une egalité";

        //egalité entre deux variables
        eg = new Egal(new ConstanteEntiere("0", 0), new ConstanteEntiere("1", 0), 0);
        eg.verifier();

        resultat =  "    li $v0, 0\n" +
                    "    move $t8, $v0\n" +
                    "    li $v0, 1\n" +
                    "    seq $v0, $t8, $v0\n";

        assert(eg.toMIPS().equals(resultat)) : "Erreur sur le toMIPS d'une egalité";

    }

    @Test
    void testDifferent () {

        String resultat;

        //différence entre 2 variables
        Different diff = new Different(new IDF("a", 0), new IDF("b", 0), 0);
        diff.verifier();

        resultat =  "    lw $v0, 0($s7)\n" +
                    "    sw $v0, ($sp)\n" +
                    "    add $sp, $sp, -4\n" +
                    "    lw $v0, -4($s7)\n" +
                    "    add $sp, $sp, 4\n" +
                    "    lw $t8, ($sp)\n" +
                    "    sne $v0, $t8, $v0\n";

        assert(diff.toMIPS().equals(resultat)) : "Erreur sur le toMIPS d'une différence";

        //différence entre une variable et une constante
        diff = new Different(new IDF("c", 0), new ConstanteEntiere("1", 0), 0);
        diff.verifier();

        resultat =  "    lw $v0, -8($s7)\n" +
                    "    move $t8, $v0\n" +
                    "    li $v0, 1\n" +
                    "    sne $v0, $t8, $v0\n";

        assert(diff.toMIPS().equals(resultat)) : "Erreur sur le toMIPS d'une différence";

        //différence entre deux variables
        diff = new Different(new ConstanteEntiere("0", 0), new ConstanteEntiere("1", 0), 0);
        diff.verifier();

        resultat =  "    li $v0, 0\n" +
                    "    move $t8, $v0\n" +
                    "    li $v0, 1\n" +
                    "    sne $v0, $t8, $v0\n";

        assert(diff.toMIPS().equals(resultat)) : "Erreur sur le toMIPS d'une différence";

    }

    @Test
    void testInferieur () {

        String resultat;

        //infériorité entre 2 variables
        Inferieur inf = new Inferieur(new IDF("a", 0), new IDF("b", 0), 0);
        inf.verifier();

        resultat =  "    lw $v0, 0($s7)\n" +
                    "    sw $v0, ($sp)\n" +
                    "    add $sp, $sp, -4\n" +
                    "    lw $v0, -4($s7)\n" +
                    "    add $sp, $sp, 4\n" +
                    "    lw $t8, ($sp)\n" +
                    "    slt $v0, $t8, $v0\n";

        assert(inf.toMIPS().equals(resultat)) : "Erreur sur le toMIPS d'une infériorité";

        //infériorité entre une variable et une constante
        inf = new Inferieur(new IDF("c", 0), new ConstanteEntiere("1", 0), 0);
        inf.verifier();

        resultat =  "    lw $v0, -8($s7)\n" +
                    "    move $t8, $v0\n" +
                    "    li $v0, 1\n" +
                    "    slt $v0, $t8, $v0\n";

        assert(inf.toMIPS().equals(resultat)) : "Erreur sur le toMIPS d'une infériorité";

        //infériorité entre deux variables
        inf = new Inferieur(new ConstanteEntiere("0", 0), new ConstanteEntiere("1", 0), 0);
        inf.verifier();

        resultat =  "    li $v0, 0\n" +
                    "    move $t8, $v0\n" +
                    "    li $v0, 1\n" +
                    "    slt $v0, $t8, $v0\n";

        assert(inf.toMIPS().equals(resultat)) : "Erreur sur le toMIPS d'une infériorité";

    }

    @Test
    void testSuperieur () {

        String resultat;

        //supériorité entre 2 variables
        Superieur sup = new Superieur(new IDF("a", 0), new IDF("b", 0), 0);
        sup.verifier();

        resultat =  "    lw $v0, 0($s7)\n" +
                    "    sw $v0, ($sp)\n" +
                    "    add $sp, $sp, -4\n" +
                    "    lw $v0, -4($s7)\n" +
                    "    add $sp, $sp, 4\n" +
                    "    lw $t8, ($sp)\n" +
                    "    sgt $v0, $t8, $v0\n";

        assert(sup.toMIPS().equals(resultat)) : "Erreur sur le toMIPS d'une supériorité";

        //supériorité entre une variable et une constante
        sup = new Superieur(new IDF("c", 0), new ConstanteEntiere("1", 0), 0);
        sup.verifier();

        resultat =  "    lw $v0, -8($s7)\n" +
                    "    move $t8, $v0\n" +
                    "    li $v0, 1\n" +
                    "    sgt $v0, $t8, $v0\n";

        assert(sup.toMIPS().equals(resultat)) : "Erreur sur le toMIPS d'une supériorité";

        //supériorité entre deux variables
        sup = new Superieur(new ConstanteEntiere("0", 0), new ConstanteEntiere("1", 0), 0);
        sup.verifier();

        resultat =  "    li $v0, 0\n" +
                    "    move $t8, $v0\n" +
                    "    li $v0, 1\n" +
                    "    sgt $v0, $t8, $v0\n";

        assert(sup.toMIPS().equals(resultat)) : "Erreur sur le toMIPS d'une supériorité";

    }

    @Test
    void testInferieurEgal () {

        String resultat;

        //infériorité ou égalité entre 2 variables
        InferieurEgal inf = new InferieurEgal(new IDF("a", 0), new IDF("b", 0), 0);
        inf.verifier();

        resultat =  "    lw $v0, 0($s7)\n" +
                    "    sw $v0, ($sp)\n" +
                    "    add $sp, $sp, -4\n" +
                    "    lw $v0, -4($s7)\n" +
                    "    add $sp, $sp, 4\n" +
                    "    lw $t8, ($sp)\n" +
                    "    sle $v0, $t8, $v0\n";

        assert(inf.toMIPS().equals(resultat)) : "Erreur sur le toMIPS d'une infériorité ou égalité";

        //infériorité ou égalité entre une variable et une constante
        inf = new InferieurEgal(new IDF("c", 0), new ConstanteEntiere("1", 0), 0);
        inf.verifier();

        resultat =  "    lw $v0, -8($s7)\n" +
                    "    move $t8, $v0\n" +
                    "    li $v0, 1\n" +
                    "    sle $v0, $t8, $v0\n";

        assert(inf.toMIPS().equals(resultat)) : "Erreur sur le toMIPS d'une infériorité ou égalité";

        //infériorité ou égalité entre deux variables
        inf = new InferieurEgal(new ConstanteEntiere("0", 0), new ConstanteEntiere("1", 0), 0);
        inf.verifier();

        resultat =  "    li $v0, 0\n" +
                    "    move $t8, $v0\n" +
                    "    li $v0, 1\n" +
                    "    sle $v0, $t8, $v0\n";

        assert(inf.toMIPS().equals(resultat)) : "Erreur sur le toMIPS d'une infériorité ou égalité";

    }

    @Test
    void testSuperieurEgal () {

        String resultat;

        //supériorité ou égalité entre 2 variables
        SuperieurEgal sup = new SuperieurEgal(new IDF("a", 0), new IDF("b", 0), 0);
        sup.verifier();

        resultat =  "    lw $v0, 0($s7)\n" +
                    "    sw $v0, ($sp)\n" +
                    "    add $sp, $sp, -4\n" +
                    "    lw $v0, -4($s7)\n" +
                    "    add $sp, $sp, 4\n" +
                    "    lw $t8, ($sp)\n" +
                    "    sge $v0, $t8, $v0\n";

        assert(sup.toMIPS().equals(resultat)) : "Erreur sur le toMIPS d'une supériorité ou égalité";

        //supériorité ou égalité entre une variable et une constante
        sup = new SuperieurEgal(new IDF("c", 0), new ConstanteEntiere("1", 0), 0);
        sup.verifier();

        resultat =  "    lw $v0, -8($s7)\n" +
                    "    move $t8, $v0\n" +
                    "    li $v0, 1\n" +
                    "    sge $v0, $t8, $v0\n";

        assert(sup.toMIPS().equals(resultat)) : "Erreur sur le toMIPS d'une supériorité ou égalité";

        //supériorité ou égalité entre deux variables
        sup = new SuperieurEgal(new ConstanteEntiere("0", 0), new ConstanteEntiere("1", 0), 0);
        sup.verifier();

        resultat =  "    li $v0, 0\n" +
                    "    move $t8, $v0\n" +
                    "    li $v0, 1\n" +
                    "    sge $v0, $t8, $v0\n";

        assert(sup.toMIPS().equals(resultat)) : "Erreur sur le toMIPS d'une supériorité ou égalité";

    }

    @Test
    void testNegation () {

        String resultat;

        Negation neg = new Negation(new IDF("a", 0), 0);
        neg.verifier();

        resultat =  "    lw $v0, 0($s7)\n" +
                    "    xori $v0, $v0, 1\n";

        assert(neg.toMIPS().equals(resultat)) : "Erreur sur le toMIPS d'une négation";

        neg = new Negation(new ConstanteEntiere("1", 0), 0);
        neg.verifier();

        resultat =  "    li $v0, 1\n" +
                    "    xori $v0, $v0, 1\n";

        assert(neg.toMIPS().equals(resultat)) : "Erreur sur le toMIPS d'une négation";

    }

    @Test
    void testOperationsLogiques () {
        testEgal();
        testDifferent();
        testInferieur();
        testSuperieur();
        testInferieurEgal();
        testSuperieurEgal();
    }

    @Test
    void testOperationsBinaires () {
        testOperationsArithmetiques();
        testOperationsLogiques();
    }

    @Test
    void testCondition () {

        String resultat;

        Condition cond = new Condition( new ConstanteEntiere("0", 0),
                                        new Ecrire(new ConstanteEntiere("19", 0), 0),
                                        new Ecrire(new ConstanteEntiere("91", 0), 0),
                                        0);
        cond.verifier();

        resultat =  "        #conditionnelle 1 sur 0\n" +
                    "si1:\n" +
                    "    li $v0, 0\n" +
                    "    beq $v0, $zero, sinon1\n\n" +
                    "alors1:\n" +
                    "                #affichage de 19\n" +
                    "    li $v0, 19\n" +
                    "    move $a0, $v0\n" +
                    "    li $v0, 1\n" +
                    "    syscall\n" +
                    "                #retour à la ligne\n" +
                    "    li $v0, 4\n" +
                    "    la $a0, finLigne\n" +
                    "    syscall\n\n" +
                    "    j finsi1\n\n" +
                    "sinon1:\n" +
                    "                #affichage de 91\n" +
                    "    li $v0, 91\n" +
                    "    move $a0, $v0\n" +
                    "    li $v0, 1\n" +
                    "    syscall\n" +
                    "                #retour à la ligne\n" +
                    "    li $v0, 4\n" +
                    "    la $a0, finLigne\n" +
                    "    syscall\n\n" +
                    "finsi1:\n\n";

        assert (cond.toMIPS().equals(resultat)) : "Erreur sur le toMIPS d'une condition";

    }

    @Test
    void testBoucle () {

        String resultat;

        Boucle bouc = new Boucle(   new Egal(new ConstanteEntiere("1", 0), new ConstanteEntiere("1", 0), 0),
                                    new Ecrire(new ConstanteEntiere("19", 0), 0),
                                    0);
        bouc.verifier();

        resultat =  "        #boucle 1 sur ( 1 == 1 )\n" +
                    "tantque1:\n" +
                    "    li $v0, 1\n" +
                    "    move $t8, $v0\n" +
                    "    li $v0, 1\n" +
                    "    seq $v0, $t8, $v0\n" +
                    "    beq $v0, $zero, fintantque1\n" +
                    "                #affichage de 19\n" +
                    "    li $v0, 19\n" +
                    "    move $a0, $v0\n" +
                    "    li $v0, 1\n" +
                    "    syscall\n" +
                    "                #retour à la ligne\n" +
                    "    li $v0, 4\n" +
                    "    la $a0, finLigne\n" +
                    "    syscall\n\n" +
                    "    j tantque1\n\n" +
                    "fintantque1:\n\n";

        assert (bouc.toMIPS().equals(resultat)) : "Erreur sur le toMIPS d'une boucle";

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

        testOperationsBinaires();

        testNegation();

        testCondition();
        testBoucle();

    }
}