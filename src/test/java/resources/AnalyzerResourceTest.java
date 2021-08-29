package resources;

import domain.IWordFrequencyAnalyzer;
import domain.WordFrequency;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class AnalyzerResourceTest {

    @Mock
    IWordFrequencyAnalyzer analyzerService;

    @InjectMocks
    AnalyzerResource analyzerResource;

    private static Stream<Arguments> calculateHighestFrequencyParameters() {
        return Stream.of(
                Arguments.of("At that moment she realized she had a sixth sense.", 2),
                Arguments.of("Just go ahead and press that button.", 1),
                Arguments.of("He drank it life before spitting it out.", 2),
                Arguments.of("The pink door swung open to reveal pink giraffes and pink elephants.", 3),
                Arguments.of("He used to get *(*&(&^confused between soldiers.", 1)
        );
    }

    @ParameterizedTest
    @MethodSource("calculateHighestFrequencyParameters")
    public void calculateHighestFrequency(final String text, final int expected) {
        doReturn(expected).when(analyzerService).calculateHighestFrequency(text);

        assertThat(analyzerResource.calculateHighestFrequency(text)).isEqualTo(expected);
        verify(analyzerService).calculateHighestFrequency(text);
    }

    private static Stream<Arguments> calculateFrequencyForWordParameters() {
        return Stream.of(
                Arguments.of("At that moment she realized she had a sixth sense.", "Had", 1),
                Arguments.of("Just go ahead and press that button.", "gO", 1),
                Arguments.of("He drank it life before spitting it out.", "it", 2),
                Arguments.of("The pink door swung open to reveal pink giraffes and pink elephants.", "pink", 3),
                Arguments.of("He used to get *(*&(&^confused between soldiers.", "confused", 1)
        );
    }

    @ParameterizedTest
    @MethodSource("calculateFrequencyForWordParameters")
    void calculateFrequencyForWord(final String text, final String word, final int expected) {
        doReturn(expected).when(analyzerService).calculateFrequencyForWord(text, word);

        assertThat(analyzerResource.calculateFrequencyForWord(text, word)).isEqualTo(expected);
        verify(analyzerService).calculateFrequencyForWord(text, word);
    }

    private static Stream<Arguments> calculateMostFrequentNWordsParameters() {
        return Stream.of(
                Arguments.of(
                        "At that moment she realized she had a sixth sense.", 1,
                        List.of(new WordFrequency("she", 2))
                ),
                Arguments.of(
                        "Just go ahead and press that button.", 2,
                        List.of(
                                new WordFrequency("and", 1),
                                new WordFrequency("ahead", 1)
                        )
                ),
                Arguments.of(
                        "He drank it life before spitting it out.", 3,
                        List.of(
                                new WordFrequency("it", 2),
                                new WordFrequency("before", 1),
                                new WordFrequency("drank", 1)
                        )
                ),
                Arguments.of(
                        "The pink door swung open to reveal pink giraffes and pink elephants.", 4,
                        List.of(
                                new WordFrequency("pink", 3),
                                new WordFrequency("and", 1),
                                new WordFrequency("door", 1),
                                new WordFrequency("elephants", 1)
                        )
                ),
                Arguments.of(
                        "He used to get *(*&(&^confused between soldiers.", 2,
                        List.of(
                                new WordFrequency("between", 1),
                                new WordFrequency("confused", 1)
                        )
                )
        );
    }

    @ParameterizedTest
    @MethodSource("calculateMostFrequentNWordsParameters")
    void calculateMostFrequentNWords(final String text, final int n, final List<WordFrequency> expected) {
        doReturn(expected).when(analyzerService).calculateMostFrequentNWords(text, n);

        assertThat(analyzerResource.calculateMostFrequentNWords(text, n)).containsExactlyInAnyOrderElementsOf(expected);
        verify(analyzerService).calculateMostFrequentNWords(text, n);
    }
}
