package com.muranyibence.jpa.service;

import com.muranyibence.jpa.exception.RepositoryException;
import com.muranyibence.jpa.model.Person;
import com.muranyibence.jpa.repository.PersonCRUDRepository;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Bence
 */
@Stateless
public class PersonCRUDService {

    @Inject
    private PersonCRUDRepository personCRUD;

    public Person createPerson(Long id) throws RepositoryException {
        return personCRUD.createPerson(id);
    }

    public Person createPerson() throws RepositoryException {
        return personCRUD.createPerson();
    }

    public Person findPerson(Long id) throws RepositoryException {
        return personCRUD.findPerson(id);
    }

    public void updatePerson(Person person) throws RepositoryException {
        personCRUD.updatePerson(person);
    }

    public void removePerson(Long id) throws RepositoryException {
        personCRUD.removePerson(id);
    }

    @PreDestroy
    public void close() {
        personCRUD.close();
    }
}
