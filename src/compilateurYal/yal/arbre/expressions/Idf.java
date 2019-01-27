package compilateurYal.yal.arbre.expressions;

import compilateurYal.yal.arbre.ArbreAbstrait;

public class Idf  extends  Expression{
    public String idf;

    public Idf(String name, int n){
        super(n);
        idf = name;
    }

    @Override
    public void verifier() {

    }

    @Override
    public String toMIPS() {
        return idf;
    }

    @Override
    public void ajouter(ArbreAbstrait... a) {
    }
}
