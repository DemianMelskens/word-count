package resources;

import domain.IWordFrequencyAnalyzer;
import domain.WordFrequency;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/analyzers")
public class AnalyzerResource {

    @Inject
    private IWordFrequencyAnalyzer analyzerService;

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
    public int calculateFrequencyForWord(final String text, @PathParam("word") final String word) {
        return analyzerService.calculateFrequencyForWord(text, word);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.TEXT_PLAIN)
    @Path("most-frequent-n-words/{n}")
    public List<WordFrequency> calculateMostFrequentNWords(final String text, @PathParam("n") final int n) {
        return analyzerService.calculateMostFrequentNWords(text, n);
    }
}
