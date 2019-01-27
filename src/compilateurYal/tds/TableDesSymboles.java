package compilateurYal.tds;

import compilateurYal.GestionnaireTailleZoneVariable;
import compilateurYal.exceptions.DoubleDeclarationException;
import compilateurYal.exceptions.VariableNonDeclareException;
import compilateurYal.tds.entrees.Entree;
import compilateurYal.tds.symboles.Symbole;

import java.util.HashMap;

public class TableDesSymboles {

    private HashMap<Entree, Symbole> tds;
    private static TableDesSymboles instance = new TableDesSymboles();

    private TableDesSymboles () {
        tds = new HashMap<>();
    }

    public static TableDesSymboles getInstance () {
        return instance;
    }

    public void ajouter (Entree e, Symbole s) throws DoubleDeclarationException {
        if (tds.containsKey(e)) {
            throw new DoubleDeclarationException(e);
        } else {
            tds.put(e, s);
        }
    }

    public Symbole identifier (Entree e) throws VariableNonDeclareException {
        Symbole s = tds.get(e);
        if (s == null) {
            throw new VariableNonDeclareException(e);
        } else {
            return s;
        }
    }

    public int getTailleZoneVariable () {
        return GestionnaireTailleZoneVariable.getInstance().getTailleZoneVariable();
    }

    @Override
    public String toString() {
        return tds.toString();
    }
}
