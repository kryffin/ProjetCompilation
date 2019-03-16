package compilateurYal.tds.entrees;

public class EntreeFonction extends Entree {

    private int nbParametres;

    /**
     * Constructeur par le nom de l'entrée
     *
     * @param nom nom à assigner à l'entrée
     */
    public EntreeFonction(String nom, int nbParametres) {
        super(nom);
        this.nbParametres = nbParametres;
    }

    @Override
    public int hashCode() {
        return ("f" + getNom() + nbParametres).hashCode();
    }

    @Override
    public String toString() {
        return getNom();
    }

}
