package compilateurYal.yal.exceptions;

public class VariableNonDeclareException extends AnalyseException {
    public VariableNonDeclareException(String m) {
        super("VARIABLE NON DECLARE"+ m);
    }
}
