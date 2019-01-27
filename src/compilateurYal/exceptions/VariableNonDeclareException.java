package compilateurYal.exceptions;

import compilateurYal.tds.entrees.Entree;

public class VariableNonDeclareException extends SyntaxeException {

    public VariableNonDeclareException(Entree e) {
        super("variable " + e.getNom() + " non déclarée");
    }

}
