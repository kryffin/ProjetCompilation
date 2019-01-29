package compilateurYal.exceptions;

public class AnalyseLexicaleException extends AnalyseException {

    /**
     * Constructeur par ligne de l'erreur, colonne et message d'erreur
     * @param ligne ligne de l'erreur
     * @param colonne colonne de l'erreur
     * @param m message d'erreur
     */
    public AnalyseLexicaleException(int ligne, int colonne, String m) {
        super("ERREUR LEXICALE :\n\tligne " + ligne + " colonne " + colonne + "\n\t" + m + " : caractère non reconnu") ;
    }

}
