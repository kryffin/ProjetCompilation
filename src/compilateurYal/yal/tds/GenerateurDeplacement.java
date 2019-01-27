package compilateurYal.yal.tds;

public class GenerateurDeplacement {
    private static GenerateurDeplacement ourInstance = new GenerateurDeplacement();

    public static GenerateurDeplacement getInstance() {
        return ourInstance;
    }

    private int nextDeplacement;
    private GenerateurDeplacement() {
        nextDeplacement = 0;
    }

    public int getNextDeplacement(){
        int res = nextDeplacement;
        nextDeplacement = nextDeplacement -4;
        return res;
    }
}
