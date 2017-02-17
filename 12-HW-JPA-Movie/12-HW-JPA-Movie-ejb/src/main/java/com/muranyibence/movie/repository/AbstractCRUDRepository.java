package com.muranyibence.movie.repository;

import com.muranyibence.movie.api.CRUDRepository;
import com.muranyibence.movie.exception.RepositoryException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Bence
 * @param <T>
 */
public abstract class AbstractCRUDRepository<T> implements CRUDRepository<T> {

    @PersistenceContext(unitName = "MoviePU")
    private EntityManager entityManager;

    public AbstractCRUDRepository() {
        //empty
    }

    @Override
    public void persist(T entity) throws RepositoryException {
        getEntityManager().persist(entity);
    }

    @Override
    public T find(Long entityId) throws RepositoryException {
        return getEntityManager().find(getEntityClass(), entityId);
    }

    @Override
    public T merge(T entity) throws RepositoryException {
        return getEntityManager().merge(entity);
    }

    @Override
    public void remove(Long entityId) throws RepositoryException {
        getEntityManager().remove(find(entityId));
    }

    protected EntityManager getEntityManager() {
        return entityManager;
    }

    protected abstract Class<T> getEntityClass();

}
