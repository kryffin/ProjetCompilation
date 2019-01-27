package compilateurYal.yal;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import compilateurYal.yal.analyse.AnalyseurLexical;
import compilateurYal.yal.analyse.AnalyseurSyntaxique;
import compilateurYal.yal.arbre.ArbreAbstrait;
import compilateurYal.yal.exceptions.AnalyseException;

public class Yal {
    
    public Yal(String nomFichier) {
        try {
            AnalyseurSyntaxique analyseur = new AnalyseurSyntaxique(new AnalyseurLexical(new FileReader(nomFichier)));
            ArbreAbstrait arbre = (ArbreAbstrait) analyseur.parse().value;

            arbre.verifier() ;
            System.out.println("COMPILATION OK") ;

            String nomSortie = nomFichier.replaceAll("[.]yal", ".mips") ;
            PrintWriter flot = new PrintWriter(new BufferedWriter(new FileWriter(nomSortie))) ;
            flot.println(arbre.toMIPS());
            flot.close() ;
        }
        catch (FileNotFoundException ex) {
            System.err.println("Fichier " + nomFichier + " inexistant") ;
        }
        catch (AnalyseException ex) {
            System.err.println(ex.getMessage());
        }
        catch (Exception ex) {
            Logger.getLogger(Yal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Nombre incorrect d'arguments") ;
            System.err.println("\tjava -jar compilateurYal.yal.jar <fichierSource.compilateurYal.yal>") ;
            System.exit(1) ;
        }
        new Yal(args[0]) ;
    }
    
}
