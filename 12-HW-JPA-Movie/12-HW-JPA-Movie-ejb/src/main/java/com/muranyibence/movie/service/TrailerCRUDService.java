package com.muranyibence.movie.service;

import com.muranyibence.movie.api.CRUDRepository;
import com.muranyibence.movie.api.CRUDService;
import com.muranyibence.movie.entity.Trailer;
import com.muranyibence.movie.qualifier.CRUDEntityTypeQualifier;
import com.muranyibence.movie.qualifier.EntityType;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Bence
 */
@Stateless
@CRUDEntityTypeQualifier(EntityType.TRAILER)
public class TrailerCRUDService extends AbstractCRUDService<Trailer> implements CRUDService<Trailer> {

    public TrailerCRUDService() {
        super(null);
    }

    @Inject
    public TrailerCRUDService(@CRUDEntityTypeQualifier(EntityType.TRAILER) CRUDRepository<Trailer> repository) {
        super(repository);
    }

}
