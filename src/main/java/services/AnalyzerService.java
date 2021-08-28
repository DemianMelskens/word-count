package services;

import domain.WordFrequency;
import domain.WordFrequencyAnalyzer;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class AnalyzerService implements WordFrequencyAnalyzer {


    @Override
    public int calculateHighestFrequency(final String text) {
        List<String> words = this.determineWords(text);
        return words.stream()
                .map(word -> Collections.frequency(words, word))
                .max(Integer::compareTo)
                .orElse(0);
    }

    @Override
    public int calculateFrequencyForWord(final String text, final String word) {
        List<String> words = this.determineWords(text);
        return Collections.frequency(words, word.toLowerCase());
    }

    @Override
    public List<WordFrequency> calculateMostFrequentNWords(final String text, final int n) {
        List<String> words = this.determineWords(text);
        return words.stream()
                .distinct()
                .map(word -> new WordFrequency(word, Collections.frequency(words, word)))
                .sorted(Comparator.comparingInt(WordFrequency::getFrequency)
                        .reversed()
                        .thenComparing(WordFrequency::getWord))
                .limit(n)
                .collect(Collectors.toList());
    }

    private List<String> determineWords(final String text) {
        return Arrays.stream(text.toLowerCase().split("[^a-zA-Z]"))
                .filter(word -> !word.isBlank())
                .collect(Collectors.toList());
    }
}
