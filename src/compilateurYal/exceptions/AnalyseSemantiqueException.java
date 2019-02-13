package compilateurYal.exceptions;

import compilateurYal.Yal;

public class AnalyseSemantiqueException extends AnalyseException {

    /**
     * Constructeur par ligne de l'erreur et le message d'erreur
     * @param ligne ligne de l'erreur
     * @param m message d'erreur
     */
    public AnalyseSemantiqueException(int ligne, String m) {
        super("ERREUR SEMANTIQUE :\n\tligne " + ligne + "\n\t" + m + "\n") ;
        System.err.println(getMessage());
        Yal.exception = true;
    }

}
