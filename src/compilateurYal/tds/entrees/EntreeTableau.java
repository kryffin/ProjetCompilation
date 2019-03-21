package compilateurYal.tds.entrees;

public class EntreeTableau extends Entree {

    /**
     * Constructeur par le nom de l'entrée
     * @param nom nom à assigner à l'entrée
     */
    public EntreeTableau(String nom) {
        super(nom);
    }

    @Override
    public int hashCode() {
        return ("v" + getNom()).hashCode();
    }

}
