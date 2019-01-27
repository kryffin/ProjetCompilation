package compilateurYal.tds.entrees;

public abstract class Entree {

    private String nom;

    public Entree (String nom) {
        this.nom = nom;
    }

    public String getNom () {
        return nom;
    }

    @Override
    public int hashCode() {
        return nom.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        Entree e = (Entree) obj;
        return this.nom.equals(((Entree) obj).nom);
    }
}
