package com.muranyibence.movie.resource;

import com.muranyibence.movie.api.CRUDService;
import com.muranyibence.movie.entity.Actor;
import com.muranyibence.movie.entity.Category;
import com.muranyibence.movie.entity.Movie;
import com.muranyibence.movie.entity.Trailer;
import com.muranyibence.movie.exception.RepositoryException;
import com.muranyibence.movie.qualifier.CRUDEntityTypeQualifier;
import com.muranyibence.movie.qualifier.EntityType;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author Bence
 */
@Path("movie")
@Produces(MediaType.APPLICATION_JSON)
public class MovieRESTResource {

    private CRUDService<Movie> movieService;
    private CRUDService<Actor> actorService;
    private CRUDService<Trailer> trailerService;
    private CRUDService<Category> categoryService;
    @Context
    private UriInfo context;

    public MovieRESTResource() {
        //empty
    }

    @Inject
    public MovieRESTResource(@CRUDEntityTypeQualifier(EntityType.TRAILER) CRUDService<Trailer> trailerService, @CRUDEntityTypeQualifier(EntityType.CATEGORY) CRUDService<Category> categoryService, @CRUDEntityTypeQualifier(EntityType.ACTOR) CRUDService<Actor> actorService, @CRUDEntityTypeQualifier(EntityType.MOVIE) CRUDService<Movie> movieService) {
        this.movieService = movieService;
        this.actorService = actorService;
        this.trailerService = trailerService;
        this.categoryService = categoryService;
    }

    @GET
    @Path("/{id}")
    public Response getMovieById(@PathParam("id") Long id) throws RepositoryException {
        Movie movie = movieService.getEntityById(id);
        if (null == movie) {
            return Response.noContent().build();
        }
        return Response.ok().entity(movie).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createMovie(Movie movie) throws RepositoryException {
        movieService.createEntity(movie);
        return Response.ok().entity(movie).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateMovie(Movie movie) throws RepositoryException {
        movieService.updateEntity(movie);
        return Response.ok().entity(movie).build();
    }

    @DELETE
    @Path("/{id}")
    public Response removeMovie(@PathParam("id") Long id) throws RepositoryException {
        movieService.removeEntity(id);
        return Response.ok().build();
    }

    @POST
    @Path("addactor/{movieId}/{actorId}")
    public Response addActor(@PathParam("actorId") Long actorId, @PathParam("movieId") Long movieId) throws RepositoryException {
        Actor actor = actorService.getEntityById(actorId);
        Movie movie = movieService.getEntityById(movieId);
        actor.addMovie(movie);
        movie.addActor(actor);
        actorService.updateEntity(actor);
        movieService.updateEntity(movie);
        return Response.ok().build();
    }

    @POST
    @Path("addcategory/{movieId}/{categoryId}")
    public Response addCategory(@PathParam("categoryId") Long categoryId, @PathParam("movieId") Long movieId) throws RepositoryException {
        Category category = categoryService.getEntityById(categoryId);
        Movie movie = movieService.getEntityById(movieId);
        category.addMovie(movie);
        movie.setCategory(category);
        categoryService.updateEntity(category);
        movieService.updateEntity(movie);
        return Response.ok().build();
    }

    @POST
    @Path("addtrailer/{movieId}/{trailerId}")
    public Response addTrailer(@PathParam("trailerId") Long trailerId, @PathParam("movieId") Long movieId) throws RepositoryException {
        Trailer trailer = trailerService.getEntityById(trailerId);
        Movie movie = movieService.getEntityById(movieId);
        trailer.setMovie(movie);
        movie.addTrailer(trailer);
        trailerService.updateEntity(trailer);
        movieService.updateEntity(movie);
        return Response.ok().build();
    }

}
