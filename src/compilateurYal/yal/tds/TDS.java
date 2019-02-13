package compilateurYal.yal.tds;

import compilateurYal.yal.GestionnaireTailleVariable;
import compilateurYal.yal.exceptions.DoubleDeclarationException;
import compilateurYal.yal.exceptions.NonDeclareException;

import java.util.HashMap;

public class TDS {

    private HashMap<Entree,Symbole> tds;
    private static TDS instance = new TDS();

    private TDS(){
        tds = new HashMap<>();
    }

    public static TDS getInstance(){
        return instance;
    }

    public void ajouter (Entree e, Symbole s) throws DoubleDeclarationException {
        if( !tds.containsKey(e)) {
            tds.put(e, s);
        }else {
            throw new DoubleDeclarationException(e);
        }
    }

    public Symbole identifier (Entree e) throws NonDeclareException {
        if(!tds.containsKey(e)){
            throw new NonDeclareException(e);
        }
        return tds.get(e);
    }

    public int getTailleZoneVariable(){
        return GestionnaireTailleVariable.getInstance().getTailleZone();
    }
}
