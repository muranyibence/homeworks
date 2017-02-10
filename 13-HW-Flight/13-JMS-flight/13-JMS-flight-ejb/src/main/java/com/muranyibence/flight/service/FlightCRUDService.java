package com.muranyibence.flight.service;

import com.muranyibence.flight.entity.Flight;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Bence
 */
@Stateless
public class FlightCRUDService {

    @PersistenceContext(name = "FlightPU")
    private EntityManager entityManager;

    public FlightCRUDService() {
        //empty
    }

    public void createFlight(Flight flight) {
        entityManager.persist(flight);
    }

    public Flight findFlight(Long id) {
        return entityManager.find(Flight.class, id);
    }

    public void removeFlight(Long id) {
        entityManager.remove(entityManager.find(Flight.class, id));
    }

    public Flight updateFlight(Flight flight) {
        return entityManager.merge(flight);
    }

    public List<Flight> getAllFlight() {
        Query query = entityManager.createQuery("SELECT f FROM Flight f");
        return query.getResultList();
    }
}
