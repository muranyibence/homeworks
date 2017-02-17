package com.muranyibence.movie.repository;

import com.muranyibence.movie.api.CRUDRepository;
import com.muranyibence.movie.entity.Movie;
import com.muranyibence.movie.qualifier.CRUDEntityTypeQualifier;
import com.muranyibence.movie.qualifier.EntityType;
import javax.ejb.Stateless;

/**
 *
 * @author Bence
 */
@Stateless
@CRUDEntityTypeQualifier(EntityType.MOVIE)
public class MovieCRUDRepository extends AbstractCRUDRepository<Movie> implements CRUDRepository<Movie> {

    @Override
    protected Class<Movie> getEntityClass() {
        return Movie.class;
    }

}
