package com.muranyibence.movie.repository;

import com.muranyibence.movie.api.CRUDRepository;
import com.muranyibence.movie.entity.Actor;
import com.muranyibence.movie.qualifier.CRUDEntityTypeQualifier;
import com.muranyibence.movie.qualifier.EntityType;
import javax.ejb.Stateless;

/**
 *
 * @author Bence
 */
@Stateless
@CRUDEntityTypeQualifier(EntityType.ACTOR)
public class ActorCRUDRepository extends AbstractCRUDRepository<Actor> implements CRUDRepository<Actor> {

    @Override
    protected Class<Actor> getEntityClass() {
        return Actor.class;
    }

}
