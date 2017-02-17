package com.muranyibence.movie.service;

import com.muranyibence.movie.api.CRUDRepository;
import com.muranyibence.movie.api.CRUDService;
import com.muranyibence.movie.entity.Category;
import com.muranyibence.movie.qualifier.CRUDEntityTypeQualifier;
import com.muranyibence.movie.qualifier.EntityType;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Bence
 */
@Stateless
@CRUDEntityTypeQualifier(EntityType.CATEGORY)
public class CategoryCRUDService extends AbstractCRUDService<Category> implements CRUDService<Category> {

    public CategoryCRUDService() {
        super(null);
    }

    @Inject
    public CategoryCRUDService(@CRUDEntityTypeQualifier(EntityType.CATEGORY) CRUDRepository<Category> repository) {
        super(repository);
    }

}
