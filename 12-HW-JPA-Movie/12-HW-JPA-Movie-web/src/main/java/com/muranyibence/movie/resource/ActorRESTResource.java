/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muranyibence.movie.resource;

import com.muranyibence.movie.api.CRUDService;
import com.muranyibence.movie.entity.Actor;
import com.muranyibence.movie.entity.Movie;
import com.muranyibence.movie.entity.Trailer;
import com.muranyibence.movie.exception.RepositoryException;
import com.muranyibence.movie.qualifier.CRUDEntityTypeQualifier;
import com.muranyibence.movie.qualifier.EntityType;
import com.muranyibence.movie.service.ActorCRUDService;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.enterprise.context.RequestScoped;
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
@Path("actor")
@Produces(MediaType.APPLICATION_JSON)
public class ActorRESTResource {

    private CRUDService<Actor> actorService;
    private CRUDService<Movie> movieService;
    @Context
    private UriInfo context;

    public ActorRESTResource() {
        //empty
    }

    @Inject
    public ActorRESTResource(@CRUDEntityTypeQualifier(EntityType.ACTOR) CRUDService<Actor> actorService, @CRUDEntityTypeQualifier(EntityType.MOVIE) CRUDService<Movie> movieService) {
        this.actorService = actorService;
        this.movieService = movieService;

    }

    @GET
    @Path("/{id}")
    public Response getActorById(@PathParam("id") Long id) throws RepositoryException {
        Actor actor = actorService.getEntityById(id);
        if (null == actor) {
            return Response.noContent().build();
        }
        return Response.ok().entity(actor).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createActor(Actor actor) throws RepositoryException {
        actorService.createEntity(actor);
        return Response.ok().entity(actor).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateActor(Actor actor) throws RepositoryException {
        actorService.updateEntity(actor);
        return Response.ok().entity(actor).build();
    }

    @DELETE
    @Path("/{id}")
    public Response removeActor(@PathParam("id") Long id) throws RepositoryException {
        actorService.removeEntity(id);
        return Response.ok().build();
    }

    @POST
    @Path("addmovie/{actorId}/{movieId}")
    public Response addMovie(@PathParam("actorId") Long actorId, @PathParam("movieId") Long movieId) throws RepositoryException {
        Actor actor = actorService.getEntityById(actorId);
        Movie movie = movieService.getEntityById(movieId);
        actor.addMovie(movie);
        movie.addActor(actor);
        actorService.updateEntity(actor);
        movieService.updateEntity(movie);
        return Response.ok().build();
    }
}
