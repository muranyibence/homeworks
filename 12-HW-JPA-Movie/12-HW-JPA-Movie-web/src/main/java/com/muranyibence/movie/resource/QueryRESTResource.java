/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author Bence
 */
@Path("query")
public class QueryRESTResource {

    @Context
    private UriInfo context;
    @Inject
    private JPQLQueryService queryService;

    public QueryRESTResource() {
        //empty
    }

    @GET
    @Path("/movie/category/{categoryId}")
    public Response searchMovieByCategory(@QueryParam("categoryId") Long categoryId) {
        List<Movie> movies = queryService.searchMovieByCategory(categoryId);
        return Response.ok(movies).build();
    }

    @GET
    @Path("/movie/title/{title}")
    public Response searchMovieByTitle(@QueryParam("title") String title) {
        List<Movie> movies = queryService.searchMovieByTitle(title);
        return Response.ok(movies).build();
    }

    @GET
    @Path("/movies/title/{title}/category/{categoryId}")
    public Response searchMovieByTitleAndCategory(@QueryParam("title") String title, @QueryParam("categoryId") Long categoryId) {
        List<Movie> movies = queryService.searchMovieByTitleAndCategory(categoryId, title);
        return Response.ok(movies).build();
    }

    @GET
    @Path("/actor/firstname/{firstname}/lastname/{lastname}")
    public Response searchActorByName(@QueryParam("firstname") String firstname, @QueryParam("lastname") String lastname) {
        List<Actor> actors = queryService.getActorByName(firstname, lastname);
        return Response.ok(actors).build();
    }

    @GET
    @Path("/actor/movie/{movieId}")
    public Response searchActorByMovie(@QueryParam("movieId") Long movieId) {
        List<Actor> actors = queryService.getActorByMovie(movieId);
        return Response.ok(actors).build();
    }

    @GET
    @Path("/actor/firstname/{firstname}/lastname/{lastname}/movie/{movieId}")
    public Response searchActorByMovieAndName(@QueryParam("firstname") String firstname, @QueryParam("lastname") String lastname, @QueryParam("movieId") Long movieId) {
        List<Actor> actors = queryService.getActorByNameAndMovie(firstname, lastname, movieId);
        return Response.ok(actors).build();
    }

    @GET
    @Path("/actor/nationality/{nationality}")
    public Response searchActorByNationality(@QueryParam("nationality") String nationality) {
        List<Actor> actors = queryService.getActorByNationality(nationality);
        return Response.ok(actors).build();
    }

    @GET
    @Path("/trailer/movie/{movieId}")
    public Response searchTrailerByMovie(@QueryParam("movieId") Long movieId) {
        List<Trailer> trailers = queryService.getTrailerByMovieId(movieId);
        return Response.ok(trailers).build();
    }

}
