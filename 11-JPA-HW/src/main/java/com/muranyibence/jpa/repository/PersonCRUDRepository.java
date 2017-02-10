package com.muranyibence.jpa.repository;

import com.muranyibence.jpa.api.PersonCRUDRepositoryInterface;
import com.muranyibence.jpa.exception.RepositoryException;
import com.muranyibence.jpa.model.Person;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author Bence
 */
@Stateless
public class PersonCRUDRepository implements PersonCRUDRepositoryInterface {

    private final EntityManagerFactory factory;
    private final EntityManager entityManager;

    public PersonCRUDRepository() {
        factory = Persistence.createEntityManagerFactory("MoviePU");
        entityManager = factory.createEntityManager();
    }

    @Override
    public Person createPerson(Long id) throws RepositoryException {
        Person person = new Person();
        person.setId(id);
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        entityManager.persist(person);
        tx.commit();
        return person;
    }

    @Override
    public Person createPerson() throws RepositoryException {
        Person person = new Person();
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        entityManager.persist(person);
        tx.commit();
        return person;
    }

    @Override
    public Person findPerson(Long id) throws RepositoryException {
        return entityManager.find(Person.class, id);
    }

    @Override
    public void updatePerson(Person person) throws RepositoryException {
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        entityManager.merge(person);
        tx.commit();
    }

    @Override
    public void removePerson(Long id) throws RepositoryException {
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        Person person = entityManager.find(Person.class, id);
        entityManager.remove(person);
        tx.commit();
    }

    @Override
    public void close() {
        factory.close();
        entityManager.close();
    }

}
