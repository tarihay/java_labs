import java.util.HashMap;
import java.util.List;

public class Main {
    private static final int INPUT_ARG = 0;
    private static final int OUTPUT_ARG = 1;

    public static void main(String[] args) {
        String finName = args[INPUT_ARG];
        String foutName = args[OUTPUT_ARG];
        Validity.checkValidity(args.length, finName, foutName);

        HashMap<String, Integer> wordData = Input.fileInput(finName);

        List<FrequencyWordData> frequencyWordData = DataTreatment.fillAndSortByFrequency(wordData);

        Output.fileOutput(foutName, frequencyWordData);
    }
}
