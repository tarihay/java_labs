import java.io.*;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Input {
    public static final String REGULAR_EXP = "[А-Яа-яЁёA-Za-z_0-9]+";
    public static final int FIRST_TIME = 1;

    public static HashMap<String, Integer> fileInput(String finName) {
        File fin = new File(finName);

        HashMap<String, Integer> wordData = new HashMap<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fin));
            String line = reader.readLine();
            while (line != null) {
                Pattern pattern = Pattern.compile(REGULAR_EXP);
                Matcher matcher = pattern.matcher(line);
                while (matcher.find()) {
                    String word = line.substring(matcher.start(), matcher.end());
                    if (wordData.containsKey(word)) {
                        int wordCount = wordData.get(word);
                        wordData.put(word, wordCount+1); //increasing by 1 because this word met another one time
                    }
                    else {
                        wordData.put(word, FIRST_TIME);
                    }
                }

                line = reader.readLine();
            }
        } catch (FileNotFoundException ex1) {
            ex1.printStackTrace();
        } catch (IOException ex2) {
            ex2.printStackTrace();
        }

        return wordData;
    }
}
