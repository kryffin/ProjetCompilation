package compilateurYal.tds.entrees;

public class EntreeVariable extends Entree {

    /**
     * Constructeur par nom de l'entrée
     * @param nom nom à assigner à l'entrée
     */
    public EntreeVariable(String nom) {
        super(nom);
    }

    @Override
    public String toString() {
        return getNom();
    }

}
