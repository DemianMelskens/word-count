import services.AnalyzerService;

public class Main {

    public static void main(String[] args) {
        final AnalyzerService analyzerService = new AnalyzerService();
        final String text = "At that moment she realized she had a sixth sense.";
        final String word = "She";
        final int n = 3;

        System.out.printf("Highest frequency: %s%n", analyzerService.calculateHighestFrequency(text));
        System.out.printf("Word: \"%s\", frequency: %s%n", word, analyzerService.calculateFrequencyForWord(text, word));
        System.out.printf("Most frequent N words: %s%n", analyzerService.calculateMostFrequentNWords(text, n));
    }
}
