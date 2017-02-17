package com.muranyibence.movie.api;

import com.muranyibence.movie.exception.RepositoryException;



/**
 *
 * @author Bence
 * @param <T>
 */
public interface CRUDRepository<T> {

    void persist(T t) throws RepositoryException;

    T find(Long entityId) throws RepositoryException;

    T merge(T t) throws RepositoryException;

    void remove(Long entityId) throws RepositoryException;

}
