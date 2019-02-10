package compilateurYal;

public class CompteurEcrireLogique {

    /**
     * compteur du numéro de l'instruction ecrire d'un booléen
     */
    private int compteur;

    /**
     * instance statique du compteur
     */
    private static CompteurEcrireLogique instance = new CompteurEcrireLogique();

    /**
     * Constructeur vide
     */
    private CompteurEcrireLogique () {
        compteur = 1;
    }

    /**
     * @return instance statique du compteur
     */
    public static CompteurEcrireLogique getInstance() {
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
