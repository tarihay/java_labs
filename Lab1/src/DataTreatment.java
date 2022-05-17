import java.util.*;

public class DataTreatment {
    private static final int DEFAULT = 0;
    private static final int MAX_PERCENT = 100;

    public static List<FrequencyWordData> fillAndSortByFrequency(HashMap<String, Integer> wordData) {
        int wordsCount = DEFAULT;
        for (Map.Entry<String, Integer> it : wordData.entrySet()) {
            wordsCount += it.getValue();
        }

        List<FrequencyWordData> frequencyWordData = new LinkedList<>();
        for (Map.Entry<String, Integer> it : wordData.entrySet()) {
            frequencyWordData.add(new FrequencyWordData(it.getKey(), it.getValue(), 
                    (((float)it.getValue())*MAX_PERCENT)/wordsCount));
        }

        frequencyWordData.sort((first, second) -> (int)(second.getWordCount() - first.getWordCount()));

        return frequencyWordData;
    }
}

