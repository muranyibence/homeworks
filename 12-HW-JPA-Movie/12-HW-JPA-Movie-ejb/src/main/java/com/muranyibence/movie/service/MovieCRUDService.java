package com.muranyibence.movie.service;

import com.muranyibence.movie.api.CRUDRepository;
import com.muranyibence.movie.api.CRUDService;
import com.muranyibence.movie.entity.Movie;
import com.muranyibence.movie.qualifier.CRUDEntityTypeQualifier;
import com.muranyibence.movie.qualifier.EntityType;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Bence
 */
@Stateless
@CRUDEntityTypeQualifier(EntityType.MOVIE)
public class MovieCRUDService extends AbstractCRUDService<Movie> implements CRUDService<Movie> {

    public MovieCRUDService() {
        super(null);
    }

    @Inject
    public MovieCRUDService(@CRUDEntityTypeQualifier(EntityType.MOVIE) CRUDRepository<Movie> repository) {
        super(repository);
    }

}
