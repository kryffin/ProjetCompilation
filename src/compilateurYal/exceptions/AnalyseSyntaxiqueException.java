package compilateurYal.exceptions;

public class AnalyseSyntaxiqueException extends AnalyseException {

    /**
     * Constructeur par message d'erreur
     * @param m message d'erreur
     */
    public AnalyseSyntaxiqueException(String m) {
        super("ERREUR SYNTAXIQUE : \n\t" + m + "\n") ;
    }

}
