package compilateurYal.tds.entrees;

public class EntreeFonction extends Entree {

    /**
     * Constructeur par le nom de l'entrée
     *
     * @param nom nom à assigner à l'entrée
     */
    public EntreeFonction(String nom) {
        super(nom);
    }

    @Override
    public String toString() {
        return getNom();
    }
}
