package compilateurYal.exceptions;

import compilateurYal.tds.entrees.Entree;

public class DoubleDeclarationException extends AnalyseSyntaxiqueException {

    /**
     * Constructeur par l'entrée causant l'erreur ainsi que la ligne de l'erreur
     * @param e entrée causant l'erreur
     * @param n ligne de l'erreur
     */
    public DoubleDeclarationException (Entree e, int n) {
        super("ligne " + n + "\n\tdouble déclaration de la variable " + e.getNom());
    }

}
