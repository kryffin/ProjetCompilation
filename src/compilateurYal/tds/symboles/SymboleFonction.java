package compilateurYal.tds.symboles;

public class SymboleFonction extends Symbole {

    /**
     * nombre de paramètres de la fonction
     */
    private int nbParametres;

    /**
     * Constructeur par numéro de région et nombre de paramètres
     * @param nRegion numéro de région
     * @param nbParametres nombre de paramètres
     */
    public SymboleFonction(int nRegion, int nbParametres) {
        super(nRegion);
        this.nbParametres = nbParametres;
    }

    /**
     * @return nombre de paramètres
     */
    public int getNbParametres() {
        return nbParametres;
    }

}
