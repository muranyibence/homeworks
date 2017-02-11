package com.muranyibence.movie.service;

import com.muranyibence.movie.entity.Actor;
import com.muranyibence.movie.entity.Movie;
import com.muranyibence.movie.entity.Trailer;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Bence
 */
@Stateless
public class JPQLQueryService {

    @PersistenceContext(unitName = "MoviePU")
    private EntityManager entityManager;

    public List<Movie> searchMovieByCategory(Long categoryId) {
        String selectQuery = "SELECT m FROM Movie m WHERE m.category.id = :categoryId";

        TypedQuery<Movie> query = entityManager.createQuery(selectQuery, Movie.class);
        query.setParameter("categoryId", categoryId);
        return query.getResultList();
    }

    public List<Movie> searchMovieByTitle(String title) {
        String selectQuery = "SELECT m FROM Movie m WHERE m.title = :title";
        TypedQuery<Movie> query = entityManager.createQuery(selectQuery, Movie.class);
        query.setParameter("title", "%" + title + "%");
        return query.getResultList();

    }

    public List<Movie> searchMovieByTitleAndCategory(Long categoryId, String title) {
        String selectQuery = "SELECT m FROM Movie m WHERE m.category.id = :categoryId AND m.title = :title";
        TypedQuery<Movie> query = entityManager.createQuery(selectQuery, Movie.class);
        query.setParameter("categoryId", categoryId);
        query.setParameter("title", "%" + title + "%");
        return query.getResultList();
    }

    public List<Actor> getActorByName(String firstName, String lastName) {
        String selectQuery = "SELECT  a FROM Actor a WHERE a.firstName = :firstName AND a.lastName = :lastName";
        TypedQuery<Actor> query = entityManager.createQuery(selectQuery, Actor.class);
        query.setParameter("firstName", firstName);
        query.setParameter("lastName", lastName);
        return query.getResultList();
    }

    public List<Actor> getActorByMovie(Long movieId) {
        String selectQuery = "SELECT DISTINCT a FROM Actor a INNER JOIN a.movies m WHERE m.id = :movieId";
        TypedQuery<Actor> query = entityManager.createQuery(selectQuery, Actor.class);
        query.setParameter("movieId", movieId);
        return query.getResultList();
    }

    public List<Actor> getActorByNameAndMovie(String firstName, String lastName, Long movieId) {
        String selectQuery = "SELECT DISTINCT a FROM Actor a INNER JOIN a.movies m WHERE a.firstName = :firstName AND a.lastName = :lastName AND m.id = :movieId";
        TypedQuery<Actor> query = entityManager.createQuery(selectQuery, Actor.class);
        query.setParameter("movieId", movieId);
        query.setParameter("firstName", firstName);
        query.setParameter("lastName", lastName);
        return query.getResultList();
    }

    public List<Actor> getActorByNationality(String nationality) {
        String selectQuery = "SELECT a FROM Actor a WHERE a.nationality = :nationality";
        TypedQuery<Actor> query = entityManager.createQuery(selectQuery, Actor.class);
        query.setParameter("nationality", nationality);

        return query.getResultList();
    }

    public List<Trailer> getTrailerByMovieId(Long movieId) {
        String selectQuery = "SELECT t FROM Trailer t WHERE t.movie.id = :movieId";
        TypedQuery<Trailer> query = entityManager.createQuery(selectQuery, Trailer.class);
        query.setParameter("movieId", movieId);
        return query.getResultList();
    }

}
