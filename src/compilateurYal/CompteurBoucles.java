package compilateurYal;

public class CompteurBoucles {

    /**
     * comptuer de numéro de la boucle
     */
    private int compteur;

    /**
     * instance statique du compteur
     */
    private static CompteurBoucles instance = new CompteurBoucles();

    /**
     * Constructeur vide
     */
    private CompteurBoucles () {
        compteur = 1;
    }

    /**
     * @return instance statique du compteur
     */
    public static CompteurBoucles getInstance() {
        return instance;
    }

    /**
     * incrémente le compteur
     */
    public void incrementerCompteur () {
        compteur++;
    }

    /**
     * @return indice du compteur
     */
    public int getCompteur() {
        return compteur;
    }

}
