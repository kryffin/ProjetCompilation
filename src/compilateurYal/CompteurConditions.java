package compilateurYal;

public class CompteurConditions {

    /**
     * compteur du numéro de la condition
     */
    private int compteur;

    /**
     * instance statique du compteur
     */
    private static CompteurConditions instance = new CompteurConditions();

    /**
     * Constructeur vide
     */
    private CompteurConditions () {
        compteur = 1;
    }

    /**
     * @return instance statique du compteur
     */
    public static CompteurConditions getInstance() {
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
