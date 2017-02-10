package com.muranyibence.jpa.application;

import com.muranyibence.jpa.exception.RepositoryException;
import com.muranyibence.jpa.model.Address;
import com.muranyibence.jpa.model.Gender;
import com.muranyibence.jpa.model.Person;
import com.muranyibence.jpa.service.PersonCRUDService;
import com.muranyibence.jpa.service.PersonQueryService;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Logger;
import javax.inject.Inject;

/**
 *
 * @author Bence
 */
public class Application {

    private static final Logger LOGGER = Logger.getLogger(Application.class.getName());

    @Inject
    private PersonCRUDService personCRUDService;
    @Inject
    private PersonQueryService personQueryService;

    public Application() {
        //empty
    }

    public void execute() throws RepositoryException {
        Person p1 = personCRUDService.createPerson();
        p1.setFirstName("Tom");
        p1.setLastName("Cruise");
        Calendar c = Calendar.getInstance();
        c.set(1962, 7, 3);
        p1.setBirthDate(c.getTime());
        p1.setGender(Gender.MALE);
        personCRUDService.updatePerson(p1);

        Person p2 = personCRUDService.createPerson();
        p2.setFirstName("Brad");
        p2.setLastName("Pitt");
        c.set(1963, 12, 18);
        p2.setBirthDate(c.getTime());
        p2.setGender(Gender.MALE);
        personCRUDService.updatePerson(p2);

        List<Person> allPerson = personQueryService.getPersons();
        LOGGER.info("Testing getPerson:");
        for (Person p : allPerson) {
            LOGGER.info(p.getFirstName() + " " + p.getLastName());
        }

        List<Person> allPersonByName = personQueryService.getPersonsByName("Brad", "Pitt");
        LOGGER.info("Testing getPersonsByName:");
        for (Person p : allPersonByName) {
            LOGGER.info(p.getFirstName() + " " + p.getLastName());
        }

        c.set(1963, 1, 1);
        List<Person> allPersonByBirthDate = personQueryService.getPersonsBornBefore(c.getTime());
        LOGGER.info("Testing getPersonsBornBefore:");
        for (Person p : allPersonByBirthDate) {
            LOGGER.info(p.getFirstName() + " " + p.getLastName());
        }

        personQueryService.removePersonByName("Brad", "Pitt");
        List<Person> allPersonAfterRemoveBradPitt = personQueryService.getPersons();
        LOGGER.info("Testing removePersonByName:");
        for (Person p : allPersonAfterRemoveBradPitt) {
            LOGGER.info(p.getFirstName() + " " + p.getLastName());
        }

        LOGGER.info("Testing updateNullAddress:");
        Address newAddress = new Address();
        newAddress.setCity("New York");

        personQueryService.updateNullAddress(newAddress);

        List<Person> allPersonAfterUpdateAddress = personQueryService.getPersons();
        LOGGER.info("Name with Adress after updateAddress:");
        for (Person p : allPersonAfterUpdateAddress) {
            LOGGER.info(p.getFirstName() + " " + p.getLastName() + " Address: " + p.getAddress().getCity());
        }
    }
}
