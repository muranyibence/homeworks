package com.muranyibence.jpa.service;

import com.muranyibence.jpa.exception.RepositoryException;
import com.muranyibence.jpa.model.Address;
import com.muranyibence.jpa.model.Person;
import com.muranyibence.jpa.repository.PersonQueryRepository;
import java.util.Date;
import java.util.List;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Bence
 */
@Stateless
public class PersonQueryService {

    @Inject
    private PersonQueryRepository personQuery;

    public List<Person> getPersonsByName(String firstName, String lastName) throws RepositoryException {
        return personQuery.getPersonsByName(firstName, lastName);
    }

    public List<Person> getPersonsBornBefore(Date date) throws RepositoryException {
        return personQuery.getPersonsBornBefore(date);
    }

    public List<Person> getPersons() throws RepositoryException {
        return personQuery.getPersons();
    }

    public void removePersonByName(String firstName, String lastName) throws RepositoryException {
        personQuery.removePersonByName(firstName, lastName);
    }

    public void updateNullAddress(Address address) throws RepositoryException {
        personQuery.updateNullAddress(address);
    }

    @PreDestroy
    public void close() {
        personQuery.close();
    }
}
