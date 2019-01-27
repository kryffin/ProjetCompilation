package compilateurYal.yal.tds;

import compilateurYal.yal.exceptions.AnalyseSemantiqueException;
import compilateurYal.yal.exceptions.DoubleDeclarationException;
import compilateurYal.yal.exceptions.VariableNonDeclareException;

import java.util.HashMap;

public class Tds {
    private static Tds ourInstance = new Tds();

    public static Tds getInstance() {
        return ourInstance;
    }

    private HashMap<Entree, Symbol> tds;
    private Tds() {
        tds = new HashMap<>();
    }

    public void ajouter(Entree e, Symbol s) throws DoubleDeclarationException {

        tds.put(e, s);
    }

    public Symbol identifier(Entree e) throws VariableNonDeclareException {

        if(tds.containsKey(e)){
            return tds.get(e);
        } else{
            throw new VariableNonDeclareException("");
        }
    }
}
