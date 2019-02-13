package compilateurYal.yal.exceptions;

import compilateurYal.yal.tds.Entree;

public class DoubleDeclarationException extends AnalyseException {
    public DoubleDeclarationException(Entree e) {
        super("ERREUR DOUBLE DECLARATION : Vous avez déclaré 2 fois "+e.getNom()+"\n");
    }
}
