package com.muranyibence.jpa.api;

import com.muranyibence.jpa.exception.RepositoryException;
import com.muranyibence.jpa.model.Address;
import com.muranyibence.jpa.model.Person;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Bence
 */
public interface PersonQueryRepositoryInterface {

    List<Person> getPersonsByName(String firstName, String lastName) throws RepositoryException;

    List<Person> getPersonsBornBefore(Date date) throws RepositoryException;

    void removePersonByName(String firstName, String lastName) throws RepositoryException;

    List<Person> getPersons() throws RepositoryException;

    void updateNullAddress(Address address) throws RepositoryException;

    void close();

}
