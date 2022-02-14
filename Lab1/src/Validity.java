import java.io.File;

public class Validity {
    public static final int REQUIRED_ARG_AMOUNT = 2;
    public static final int ERROR = 1;

    public static void checkValidity(int argsCount, String finName, String foutName) {
        File fin = new File(finName);
        File fout = new File(foutName);

        if (argsCount != REQUIRED_ARG_AMOUNT) {
            System.out.println("Incorrect amount of args");
            System.exit(ERROR);
        }

        if (!fin.exists()) {
            System.out.println(finName + " wasn't opened");
            System.exit(ERROR);
        }

        if (!fout.exists()) {
            System.out.println(foutName + " wasn't opened");
            System.exit(ERROR);
        }
    }
}
