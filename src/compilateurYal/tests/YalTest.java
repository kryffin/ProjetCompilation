package compilateurYal.tests;

import compilateurYal.Yal;
import sun.tools.util.CommandLine;

import java.io.IOException;

class YalTest {

    @org.junit.jupiter.api.Test
    void main() {

        String[] args = {"tests/test.yal"};

        Yal.main(args);
        try {
            args[0] = "tests/mars.batch";
            CommandLine.parse(args);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}