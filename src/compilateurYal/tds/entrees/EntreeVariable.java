package compilateurYal.tds.entrees;

public class EntreeVariable extends Entree {

    public EntreeVariable(String nom) {
        super(nom);
    }

    @Override
    public String toString() {
        return getNom();
    }

}
