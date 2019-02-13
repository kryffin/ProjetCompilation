package compilateurYal.exceptions;

public abstract class AnalyseException extends RuntimeException {

    /**
     * Constructeur par message d'erreur
     * @param m message d'erreur
     */
    protected AnalyseException(String m) {
        super(m) ;
    }

}
