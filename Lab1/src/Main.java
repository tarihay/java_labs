import java.util.HashMap;
import java.util.List;

public class Main {
    public static final int FIRST_ARG = 0;
    public static final int SECOND_ARG = 1;

    public static void main(String[] args) {
        String finName = args[FIRST_ARG];
        String foutName = args[SECOND_ARG];
        Validity.checkValidity(args.length, finName, foutName);

        HashMap<String, Integer> wordData = Input.fileInput(finName);

        List<FrequencyWordData> frequencyWordData = DataTreatment.fillAndSortByFrequency(wordData);

        Output.fileOutput(foutName, frequencyWordData);
    }
}
