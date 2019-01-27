package compilateurYal.exceptions;

public abstract class SyntaxeException extends RuntimeException {

    public SyntaxeException (String m) {
        super("ERREUR SYNTAXIQUE : " + m);
    }

}
