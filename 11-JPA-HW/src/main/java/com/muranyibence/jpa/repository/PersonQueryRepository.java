package com.muranyibence.jpa.repository;

import com.muranyibence.jpa.api.PersonQueryRepositoryInterface;
import com.muranyibence.jpa.exception.RepositoryException;
import com.muranyibence.jpa.model.Address;
import com.muranyibence.jpa.model.Person;
import java.util.Date;
import java.util.List;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Bence
 */
@Stateless
public class PersonQueryRepository implements PersonQueryRepositoryInterface {

    private final EntityManagerFactory factory;
    private final EntityManager entityManager;

    public PersonQueryRepository() {
        factory = Persistence.createEntityManagerFactory("MoviePU");
        entityManager = factory.createEntityManager();
    }

    @Override
    public List<Person> getPersonsByName(String firstName, String lastName) throws RepositoryException {
        String selectQuery = "SELECT p FROM Person p WHERE p.firstName = :firstName AND p.lastName= :lastName";
        TypedQuery<Person> query = entityManager.createQuery(selectQuery, Person.class);
        query.setParameter("firstName", firstName);
        query.setParameter("lastName", lastName);
        return query.getResultList();
    }

    @Override
    public List<Person> getPersonsBornBefore(Date date) throws RepositoryException {
        String selectQuery = "SELECT p FROM Person p WHERE p.birthDate < :before";
        TypedQuery<Person> query = entityManager.createQuery(selectQuery, Person.class);
        query.setParameter("before", date);

        return query.getResultList();
    }

    @Override
    public List<Person> getPersons() throws RepositoryException {
        String selectQuery = "SELECT p FROM Person p";
        TypedQuery<Person> query = entityManager.createQuery(selectQuery, Person.class);
        return query.getResultList();
    }

    @Override
    public void removePersonByName(String firstName, String lastName) throws RepositoryException {
        String deleteQuery = "DELETE FROM Person p WHERE p.firstName = :firstName AND p.lastName= :lastName";
        Query query = entityManager.createQuery(deleteQuery);
        query.setParameter("firstName", firstName);
        query.setParameter("lastName", lastName);
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        query.executeUpdate();
        tx.commit();
    }

    @Override
    public void updateNullAddress(Address address) throws RepositoryException {
        String selectQuery = "SELECT p FROM Person p WHERE p.address IS NULL";
        TypedQuery<Person> query = entityManager.createQuery(selectQuery, Person.class);
        List<Person> persons = query.getResultList();
        for (Person p : persons) {
            EntityTransaction tx = entityManager.getTransaction();
            tx.begin();
            p.setAddress(address);
            entityManager.merge(p);
            tx.commit();

        }
    }

    @Override
    public void close() {
        entityManager.close();
        factory.close();
    }

}
