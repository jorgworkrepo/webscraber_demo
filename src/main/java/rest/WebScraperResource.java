package rest;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import webscraper.TagCounter;
import webscraper.Tester;
import webscraper.TagDTO;

/**
 * REST Web Service
 *
 * @author lam
 */
@Path("scrape")
public class WebScraperResource {

    @Context
    private UriInfo context;

    @Path("sequential")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getTagsSequential() {

        LocalTime begin = LocalTime.now();
        List<TagCounter> dataFetched = Tester.runSequential();
        LocalTime end = LocalTime.now();

        long endTime = ChronoUnit.NANOS.between(begin, end);

        return TagDTO.getTagsAsJson("Sequential fetching", dataFetched, endTime);
    }

    @Path("parallel")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getTagsParallel() {

        // TODO:

        return "Make me return results, fetched by a parallel strategy";
    }

}
