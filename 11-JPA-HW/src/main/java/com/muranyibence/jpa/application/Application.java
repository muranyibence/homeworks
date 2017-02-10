package com.muranyibence.jpa.application;

import com.muranyibence.jpa.exception.RepositoryException;
import com.muranyibence.jpa.model.Gender;
import com.muranyibence.jpa.model.Person;
import com.muranyibence.jpa.repository.PersonCRUDRepository;
import java.util.Calendar;
import javax.inject.Inject;

/**
 *
 * @author Bence
 */
public class Application {

    @Inject
    private PersonCRUDRepository personCRUDRepository;

    public Application() {
        //empty
    }

    public void execute() throws RepositoryException {
        Person p1 = personCRUDRepository.createPerson();
        p1.setFirstName("Tom");
        p1.setLastName("Cruise");
        Calendar c = Calendar.getInstance();
        c.set(1962, 7, 3);
        p1.setBirthDate(c.getTime());
        p1.setGender(Gender.MALE);
        personCRUDRepository.updatePerson(p1);

    }
}
