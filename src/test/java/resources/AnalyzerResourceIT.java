package resources;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.microshed.testing.jaxrs.RESTClient;
import org.microshed.testing.jupiter.MicroShedTest;
import org.microshed.testing.testcontainers.ApplicationContainer;
import org.testcontainers.junit.jupiter.Container;

import java.util.stream.Stream;

@MicroShedTest
public class AnalyzerResourceIT {

    @Container
    public static ApplicationContainer app = new ApplicationContainer()
            .withExposedPorts(8080)
            .withAppContextRoot("/word-count-1.0");

    // This injects a REST _Client_ proxy of the PersonService shown above
    // This allows us to easily invoke HTTP requests on the running application container
    @RESTClient
    public static AnalyzerResource analyzerResource;

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

    }
}
