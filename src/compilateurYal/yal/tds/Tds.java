package compilateurYal.yal.tds;

import compilateurYal.yal.exceptions.AnalyseSemantiqueException;
import compilateurYal.yal.exceptions.DoubleDeclarationException;
import compilateurYal.yal.exceptions.VariableNonDeclareException;

import java.util.HashMap;
import java.util.Map;

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

    public Symbol
    identifier(Entree e) throws VariableNonDeclareException {
        System.out.println("==identifcation de " + e.getIdf() + " ==");
        for (Map.Entry mapentry : tds.entrySet()) {
            if(mapentry.getKey().toString().equals(e.getIdf().toString())){
                e = (Entree) mapentry.getKey();
                break;
            }
            System.out.println("clé: " + mapentry.getKey().toString()
                    + " | valeur: " + mapentry.getValue().toString());
        }
        if(tds.containsKey(e)){
            return tds.get(e);
        } else{
            System.out.println("EH BEN ALORS :o j'ai pas trouvé " + e.toString());
            throw new VariableNonDeclareException("");
        }
    }
}
