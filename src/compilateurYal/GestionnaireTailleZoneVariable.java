package compilateurYal;

public class GestionnaireTailleZoneVariable {

    int tailleZoneVariable;
    private static GestionnaireTailleZoneVariable instance = new GestionnaireTailleZoneVariable();

    private GestionnaireTailleZoneVariable () {
        tailleZoneVariable = 0;
    }

    public static GestionnaireTailleZoneVariable getInstance () {
        return instance;
    }

    public int getTailleZoneVariable () {
        return tailleZoneVariable;
    }

    public void incrementerTailleZoneVariable () {
        tailleZoneVariable -= 4;
    }

}
