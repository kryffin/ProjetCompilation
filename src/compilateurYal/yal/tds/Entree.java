package compilateurYal.yal.tds;

import compilateurYal.yal.arbre.expressions.Idf;

public class Entree {

    private Idf idf;


    public Entree(Idf idf){
        this.idf = idf;
    }

    public Idf getIdf(){
        return idf;
    }

    public String toString(){
        return idf.toString();
    }
}
