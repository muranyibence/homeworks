package com.muranyibence.movie.repository;

import com.muranyibence.movie.api.CRUDRepository;
import com.muranyibence.movie.entity.Actor;
import com.muranyibence.movie.entity.Category;
import com.muranyibence.movie.qualifier.CRUDEntityTypeQualifier;
import com.muranyibence.movie.qualifier.EntityType;
import javax.ejb.Stateless;

/**
 *
 * @author Bence
 */
@Stateless
@CRUDEntityTypeQualifier(EntityType.CATEGORY)
public class CategoryCRUDRepository extends AbstractCRUDRepository<Category> implements CRUDRepository<Category> {

    @Override
    protected Class<Category> getEntityClass() {
        return Category.class;
    }

}
