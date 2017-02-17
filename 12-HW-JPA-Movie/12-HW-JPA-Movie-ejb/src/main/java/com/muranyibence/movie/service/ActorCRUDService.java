package com.muranyibence.movie.service;

import com.muranyibence.movie.api.CRUDRepository;
import com.muranyibence.movie.api.CRUDService;
import com.muranyibence.movie.entity.Actor;
import com.muranyibence.movie.qualifier.CRUDEntityTypeQualifier;
import com.muranyibence.movie.qualifier.EntityType;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Bence
 */
@Stateless
@CRUDEntityTypeQualifier(EntityType.ACTOR)
public class ActorCRUDService extends AbstractCRUDService<Actor> implements CRUDService<Actor> {

    public ActorCRUDService() {
        super(null);
    }

    @Inject
    public ActorCRUDService(@CRUDEntityTypeQualifier(EntityType.ACTOR) CRUDRepository<Actor> repository) {
        super(repository);
    }  

}
