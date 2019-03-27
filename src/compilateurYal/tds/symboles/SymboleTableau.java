package compilateurYal.tds.symboles;

import compilateurYal.arbre.expressions.Expression;

public class SymboleTableau extends Symbole {

    private int deplacement;
    private boolean dynamique;
    private Expression taille;
    private int borneSup;
    private int enjambee;
    private int adrOrigineVirtuelle;
    private int adrImplementation;

    public SymboleTableau(int deplacement, int nRegion, Expression taille) {
        super(nRegion);
        this.deplacement = deplacement;
        this.dynamique = true;
        this.taille = taille;
        this.enjambee = 4;
    }

    public int getDeplacement() {
        return deplacement;
    }

    public void setDeplacement(int deplacement) {
        this.deplacement = deplacement;
    }

    public boolean isDynamique() {
        return dynamique;
    }

    public void setDynamique(boolean dynamique) {
        this.dynamique = dynamique;
    }

    public Expression getTaille() {
        return taille;
    }

    public void setTaille(Expression taille) {
        this.taille = taille;
    }

    public int getBorneSup() {
        return borneSup;
    }

    public void setBorneSup(int borneSup) {
        this.borneSup = borneSup;
    }

    public int getEnjambee() {
        return enjambee;
    }

    public void setEnjambee(int enjambee) {
        this.enjambee = enjambee;
    }

    public int getAdrOrigineVirtuelle() {
        return adrOrigineVirtuelle;
    }

    public void setAdrOrigineVirtuelle(int adrOrigineVirtuelle) {
        this.adrOrigineVirtuelle = adrOrigineVirtuelle;
    }

    public int getAdrImplementation() {
        return adrImplementation;
    }

    public void setAdrImplementation(int adrImplementation) {
        this.adrImplementation = adrImplementation;
    }

}
