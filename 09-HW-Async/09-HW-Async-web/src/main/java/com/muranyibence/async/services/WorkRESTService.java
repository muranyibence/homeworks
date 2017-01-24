package com.muranyibence.async.services;

import com.muranyibence.async.AsyncDemonstrator;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Bence
 */
@Path("work")
@Produces(MediaType.TEXT_PLAIN)
@RequestScoped
public class WorkRESTService {

    private AsyncDemonstrator asyncDemonstrator = new AsyncDemonstrator();
    private Future<String> result;
    private String resultString;
    private static final Logger LOGGER = Logger.getLogger(WorkRESTService.class.getName());

    /**
     * URL:
     * http://localhost:8080/09-HW-Async-web/webresources/work/voiddemonstrator
     * A metódus egyszerűen elaltat egy szálat 1mp-re, logolja a kezdetét és
     * végét, kiegészítve a kettő között eltelt idővel.
     */
    @GET
    @Path("/voiddemonstrator")
    public String voidDemonstrator() {
        asyncDemonstrator.workSomething();
        return "Worksomething done";
    }

    /**
     * URL:
     * http://localhost:8080/09-HW-Async-web/webresources/work/futuredemonstrator
     * A hívott metódus egyszerűen elaltat egy szálat 1mp-re, de ezúttal a mért
     * eltelt időt vissza is adja, amit a REST hívás eredményébe is belerakunk.
     */
    @Path("/futuredemonstrator")
    @GET
    public String futureDemonstrator() {
        result = asyncDemonstrator.workSomethingWithResult();
        LOGGER.log(Level.INFO, "workSomethingWithResult in progress, doing something else");
        resultString = null;
        try {
            resultString = result.get();
        } catch (InterruptedException | ExecutionException ex) {
            Logger.getLogger(WorkRESTService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultString;
    }
}
