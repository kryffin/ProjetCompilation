package compilateurYal.yal;

public class GestionnaireTailleVariable {

    private static GestionnaireTailleVariable instance = new GestionnaireTailleVariable();
    private int tailleZone;

    private GestionnaireTailleVariable(){
        tailleZone=0;
    }

    public static GestionnaireTailleVariable getInstance(){
        return instance;
    }

    public int getTailleZone(){
        return tailleZone;
    }

    public void incrementerTailleZone(){
        tailleZone -= 4;
    }
}
