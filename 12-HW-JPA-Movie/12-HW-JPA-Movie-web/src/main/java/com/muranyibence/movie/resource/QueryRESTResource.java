package com.muranyibence.movie.resource;

import com.muranyibence.movie.entity.Actor;
import com.muranyibence.movie.entity.Movie;
import com.muranyibence.movie.entity.Trailer;
import com.muranyibence.movie.service.JPQLQueryService;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.inject.Inject;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Bence
 */
@Path("query")
@Produces(MediaType.APPLICATION_JSON)
public class QueryRESTResource {

    @Context
    private UriInfo context;
    private JPQLQueryService queryService;

    @Inject
    public QueryRESTResource(JPQLQueryService queryService) {
        this.queryService = queryService;
    }

    @GET
    @Path("/getmoviebycategory/{categoryId}")
    public List<Movie> searchMovieByCategory(@PathParam("categoryId") Long categoryId) {
        return queryService.searchMovieByCategory(categoryId);
    }

    @GET
    @Path("/getmoviebytitle/{title}")
    public List<Movie> searchMovieByTitle(@PathParam("title") String title) {
        return queryService.searchMovieByTitle(title);
    }

    @GET
    @Path("getmoviebycategoryandtitle/category/{categoryId}/title/{title}")
    public List<Movie> searchMovieByTitleAndCategory(@PathParam("title") String title, @PathParam("categoryId") Long categoryId) {
        return queryService.searchMovieByTitleAndCategory(categoryId, title);
    }

    @GET
    @Path("/getactorbyname/firstname/{firstname}/lastname/{lastname}")
    public List<Actor> searchActorByName(@PathParam("firstname") String firstname, @PathParam("lastname") String lastname) {
        return queryService.getActorByName(firstname, lastname);
    }

    @GET
    @Path("/getactorbymovieid/{movieId}")
    public List<Actor> searchActorByMovie(@PathParam("movieId") Long movieId) {
        return queryService.getActorByMovie(movieId);
    }

    @GET
    @Path("/getactorbynameandmovie/firstname/{firstname}/lastname/{lastname}/movie/{movieId}")
    public List<Actor> searchActorByMovieAndName(@PathParam("firstname") String firstname, @PathParam("lastname") String lastname, @PathParam("movieId") Long movieId) {
        return queryService.getActorByNameAndMovie(firstname, lastname, movieId);
    }

    @GET
    @Path("/getactorbynatinoality/{nationality}")
    public List<Actor> searchActorByNationality(@PathParam("nationality") String nationality) {
        return queryService.getActorByNationality(nationality);
    }

    @GET
    @Path("/gettrailerbymovieid/{movieId}")
    public List<Trailer> searchTrailerByMovie(@PathParam("movieId") Long movieId) {
        return queryService.getTrailerByMovieId(movieId);
    }

}
