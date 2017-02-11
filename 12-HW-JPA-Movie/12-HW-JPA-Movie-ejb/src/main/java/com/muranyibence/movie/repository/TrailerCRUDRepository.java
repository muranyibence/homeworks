package com.muranyibence.movie.repository;

import com.muranyibence.movie.api.CRUDRepository;
import com.muranyibence.movie.entity.Actor;
import com.muranyibence.movie.entity.Trailer;
import com.muranyibence.movie.qualifier.CRUDEntityTypeQualifier;
import com.muranyibence.movie.qualifier.EntityType;
import javax.ejb.Stateless;

/**
 *
 * @author Bence
 */
@Stateless
@CRUDEntityTypeQualifier(EntityType.TRAILER)
public class TrailerCRUDRepository extends AbstractCRUDRepository<Trailer> implements CRUDRepository<Trailer> {

    @Override
    protected Class<Trailer> getEntityClass() {
        return Trailer.class;
    }

}
