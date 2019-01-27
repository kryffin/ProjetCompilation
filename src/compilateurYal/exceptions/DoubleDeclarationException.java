package compilateurYal.exceptions;

import compilateurYal.tds.entrees.Entree;

public class DoubleDeclarationException extends SyntaxeException {

    public DoubleDeclarationException (Entree e) {
        super("double déclaration de la variable " + e.getNom());
    }

}
