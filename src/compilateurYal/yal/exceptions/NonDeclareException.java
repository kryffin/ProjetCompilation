package compilateurYal.yal.exceptions;

import compilateurYal.yal.tds.Entree;

public class NonDeclareException extends AnalyseException {
    public NonDeclareException(Entree e) {
        super("ERREUR NOM DECLARE : Vous n'avez pas déclaré "+e.getNom()+"\n");
    }
}
