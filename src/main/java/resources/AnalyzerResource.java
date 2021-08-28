package resources;

import domain.WordFrequency;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import services.AnalyzerService;

import java.util.List;

@Path("/analyzers")
public class AnalyzerResource {

    @Inject
    private AnalyzerService analyzerService;

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    @Path("highest-frequency")
    public int calculateHighestFrequency(final String text) {
        return analyzerService.calculateHighestFrequency(text);
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    @Path("frequency/{word}")
    public int calculateFrequencyForWord(@PathParam("word") final String word, final String text) {
        return analyzerService.calculateFrequencyForWord(text, word);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.TEXT_PLAIN)
    @Path("most-frequent-n-words/{n}")
    public List<WordFrequency> calculateMostFrequentNWords(@PathParam("n") final int n, final String text) {
        return analyzerService.calculateMostFrequentNWords(text, n);
    }
}
