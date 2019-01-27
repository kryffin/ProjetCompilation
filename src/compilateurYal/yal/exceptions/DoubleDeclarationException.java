package compilateurYal.yal.exceptions;

public class DoubleDeclarationException extends AnalyseException {
    protected DoubleDeclarationException(String m) {
        super("DOUBLE DECLARATION DE LA VARIABLE " + m);
    }
}
