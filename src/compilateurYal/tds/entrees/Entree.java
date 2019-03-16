package compilateurYal.tds.entrees;

public abstract class Entree {

    /**
     * nom de l'entrée
     */
    private String nom;

    /**
     * Constructeur par le nom de l'entrée
     * @param nom nom à assigner à l'entrée
     */
    public Entree (String nom) {
        this.nom = nom;
    }

    /**
     * @return nom de l'entrée
     */
    public String getNom () {
        return nom;
    }

    /**
     * @return hashcode du nom de l'entrée
     */
    @Override
    abstract public int hashCode();

    /**
     * Retourne vrai si this et obj sont égaux (par rapport au nom), faux sinon
     * @param obj objet à comparer
     * @return vrai si les objets sont égaux, faux sinon
     */
    @Override
    public boolean equals(Object obj) {
        Entree e = (Entree) obj;
        return this.nom.equals(((Entree) obj).nom);
    }
}
