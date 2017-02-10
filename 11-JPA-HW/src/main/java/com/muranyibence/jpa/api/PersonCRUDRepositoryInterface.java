package com.muranyibence.jpa.api;

import com.muranyibence.jpa.exception.RepositoryException;
import com.muranyibence.jpa.model.Person;

/**
 *
 * @author Bence
 */
public interface PersonCRUDRepositoryInterface {

    Person createPerson(Long id) throws RepositoryException;

    Person createPerson() throws RepositoryException;

    Person findPerson(Long id) throws RepositoryException;

    void updatePerson(Person person) throws RepositoryException;

    void removePerson(Long id) throws RepositoryException;

    void close();

}
