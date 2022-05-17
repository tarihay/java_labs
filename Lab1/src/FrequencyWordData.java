public class FrequencyWordData {
    private String word;
    private int wordCount;
    private float frequency;

    public FrequencyWordData(String word, int wordCount, float frequency) {
        this.word = word;
        this.wordCount = wordCount;
        this.frequency = frequency;
    }

    public String getWord() {
        return word;
    }

    public int getWordCount() {
        return wordCount;
    }

    public float getFrequency() {
        return frequency;
    }
}
