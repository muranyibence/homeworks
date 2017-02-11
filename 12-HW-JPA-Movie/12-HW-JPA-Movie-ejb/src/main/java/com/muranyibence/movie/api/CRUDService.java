package com.muranyibence.movie.api;

import com.muranyibence.movie.exception.RepositoryException;

/**
 *
 * @author Bence
 * @param <T>
 */
public interface CRUDService<T> {

    void createEntity(T entity) throws RepositoryException;

    T getEntityById(Long entityId) throws RepositoryException;

    T updateEntity(T entity) throws RepositoryException;

    void removeEntity(Long entityId) throws RepositoryException;

}
