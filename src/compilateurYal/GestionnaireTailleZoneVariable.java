package compilateurYal;

public class GestionnaireTailleZoneVariable {

    /**
     * "pointeur" sur la taille de la zone variable à allouer pour la prochaine variable (0, -4, -8, etc...)
     */
    int tailleZoneVariable;

    /**
     * instance du singleton
     */
    private static GestionnaireTailleZoneVariable instance = new GestionnaireTailleZoneVariable();

    /**
     * Constructeur initialisant la taille à 0
     */
    private GestionnaireTailleZoneVariable () {
        tailleZoneVariable = 0;
    }

    /**
     * @return l'instance du singleton
     */
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
