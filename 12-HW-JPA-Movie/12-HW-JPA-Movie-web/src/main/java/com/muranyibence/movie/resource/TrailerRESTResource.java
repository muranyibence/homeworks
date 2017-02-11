/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muranyibence.movie.resource;

import com.muranyibence.movie.api.CRUDService;
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
@Path("trailer")
@Produces(MediaType.APPLICATION_JSON)
public class TrailerRESTResource {

    private CRUDService<Trailer> trailerService;
    private CRUDService<Movie> movieService;

    @Context
    private UriInfo context;

    public TrailerRESTResource() {
        //empty
    }

    @Inject
    public TrailerRESTResource(@CRUDEntityTypeQualifier(EntityType.MOVIE) CRUDService<Movie> movieService, @CRUDEntityTypeQualifier(EntityType.TRAILER) CRUDService<Trailer> trailerService) {
        this.trailerService = trailerService;
        this.movieService = this.movieService;
    }

    @GET
    @Path("/{id}")
    public Response getTrailerById(@PathParam("id") Long id) throws RepositoryException {
        Trailer trailer = trailerService.getEntityById(id);
        if (null == trailer) {
            return Response.noContent().build();
        }
        return Response.ok().entity(trailer).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createTrailer(Trailer trailer) throws RepositoryException {
        trailerService.createEntity(trailer);
        return Response.ok().entity(trailer).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateTrailer(Trailer trailer) throws RepositoryException {
        trailerService.updateEntity(trailer);
        return Response.ok().entity(trailer).build();
    }

    @DELETE
    @Path("/{id}")
    public Response removeTrailer(@PathParam("id") Long id) throws RepositoryException {
        trailerService.removeEntity(id);
        return Response.ok().build();
    }

    @POST
    @Path("addmovie/{trailerId}/{movieId}")
    public Response addMovie(@PathParam("trailerId") Long trailerId, @PathParam("movieId") Long movieId) throws RepositoryException {
        Trailer trailer = trailerService.getEntityById(trailerId);
        Movie movie = movieService.getEntityById(movieId);
        trailer.setMovie(movie);
        movie.addTrailer(trailer);
        trailerService.updateEntity(trailer);
        movieService.updateEntity(movie);
        return Response.ok().build();
    }
}
