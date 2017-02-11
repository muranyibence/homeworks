package com.muranyibence.movie.service;

import com.muranyibence.movie.api.CRUDRepository;
import com.muranyibence.movie.api.CRUDService;
import com.muranyibence.movie.exception.RepositoryException;

/**
 *
 * @author Bence
 * @param <T>
 */
public class AbstractCRUDService<T> implements CRUDService<T> {

    private final CRUDRepository<T> repository;

    public AbstractCRUDService(CRUDRepository<T> repository) {
        this.repository = repository;
    }

    @Override
    public void createEntity(T entity) throws RepositoryException {
        repository.persist(entity);
    }

    @Override
    public T getEntityById(Long entityId) throws RepositoryException {
        return repository.find(entityId);
    }

    @Override
    public T updateEntity(T entity) throws RepositoryException {
        return repository.merge(entity);
    }

    @Override
    public void removeEntity(Long entityId) throws RepositoryException {
        repository.remove(entityId);
    }

}
