import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

public class Output {
    private static final String TWO_DIGIT_FORMAT = "#0.00";

    public static void fileOutput(String outName, List<FrequencyWordData> frequencyWordData) {
        File fout = new File(outName);

        try (FileWriter writer = new FileWriter(fout, false)){
            for (int i = 0; i < frequencyWordData.size(); i++) {
                FrequencyWordData current = frequencyWordData.get(i);
                String formattedFrequency = new DecimalFormat(TWO_DIGIT_FORMAT).format(current.getFrequency());
                writer.write(current.getWord() + " , " + current.getWordCount() + " , " + formattedFrequency + "%\n");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
