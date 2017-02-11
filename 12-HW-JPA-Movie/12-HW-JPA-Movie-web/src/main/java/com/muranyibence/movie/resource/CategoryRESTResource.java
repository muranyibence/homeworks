/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muranyibence.movie.resource;

import com.muranyibence.movie.api.CRUDService;
import com.muranyibence.movie.entity.Actor;
import com.muranyibence.movie.entity.Category;
import com.muranyibence.movie.entity.Movie;
import com.muranyibence.movie.exception.RepositoryException;
import com.muranyibence.movie.qualifier.CRUDEntityTypeQualifier;
import com.muranyibence.movie.qualifier.EntityType;
import com.muranyibence.movie.service.CategoryCRUDService;
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
@Path("category")
@Produces(MediaType.APPLICATION_JSON)
public class CategoryRESTResource {

    private CRUDService<Category> categoryService;
    private CRUDService<Movie> movieService;

    @Context
    private UriInfo context;

    public CategoryRESTResource() {
        //empty
    }

    @Inject
    public CategoryRESTResource(@CRUDEntityTypeQualifier(EntityType.CATEGORY) CRUDService<Category> categoryService, @CRUDEntityTypeQualifier(EntityType.MOVIE) CRUDService<Movie> movieService) {
        this.categoryService = categoryService;
        this.movieService = movieService;
    }

    @GET
    @Path("/{id}")
    public Response getCategoryById(@PathParam("id") Long id) throws RepositoryException {
        Category category = categoryService.getEntityById(id);
        if (null == category) {
            return Response.noContent().build();
        }
        return Response.ok().entity(category).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createCategory(Category category) throws RepositoryException {
        categoryService.createEntity(category);
        return Response.ok().entity(category).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateCategory(Category category) throws RepositoryException {
        categoryService.updateEntity(category);
        return Response.ok().entity(category).build();
    }

    @DELETE
    @Path("/{id}")
    public Response removeCategory(@PathParam("id") Long id) throws RepositoryException {
        categoryService.removeEntity(id);
        return Response.ok().build();
    }

    @POST
    @Path("addmovie/{categoryId}/{movieId}")
    public Response addMovie(@PathParam("categoryId") Long categoryId, @PathParam("movieId") Long movieId) throws RepositoryException {
        Category category = categoryService.getEntityById(categoryId);
        Movie movie = movieService.getEntityById(movieId);
        category.addMovie(movie);
        movie.setCategory(category);
        categoryService.updateEntity(category);
        movieService.updateEntity(movie);
        return Response.ok().build();
    }
}
