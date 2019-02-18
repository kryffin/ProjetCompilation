package compilateurYal.tds.symboles;

public abstract class Symbole {

    protected int nRegion;

    public Symbole (int nRegion) {
        this.nRegion = nRegion;
    }

    public void setRegion (int nRegion) {
        this.nRegion = nRegion;
    }

    public int getNRegion () {
        return nRegion;
    }

}
