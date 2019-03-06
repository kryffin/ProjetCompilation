package compilateurYal;

public class CompteurRegions {

    /**
     * compteur de numéro de région
     */
    private int compteur;

    /**
     * instance statique du compteur
     */
    private static CompteurRegions instance = new CompteurRegions();

    /**
     * Constructeur vide
     */
    private CompteurRegions() {
        compteur = 0;
    }

    /**
     * @return instance statique du compteur
     */
    public static CompteurRegions getInstance() {
        return instance;
    }

    /**
     * incrémente le compteur
     */
    public void incrementerCompteur () {
        compteur++;
    }

    /**
     * remise à zéro du compteur
     */
    public void reset () {
        compteur = 0;
    }

    /**
     * @return indice du compteur
     */
    public int getCompteur() {
        return compteur;
    }

}
