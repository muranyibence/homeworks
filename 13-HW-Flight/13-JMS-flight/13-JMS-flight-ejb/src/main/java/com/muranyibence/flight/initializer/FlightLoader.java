package com.muranyibence.flight.initializer;

import com.muranyibence.flight.entity.Flight;
import com.muranyibence.flight.service.FlightCRUDService;
import java.util.Calendar;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

/**
 *
 * @author Bence
 */
@Singleton
@Startup
public class FlightLoader {

    private Calendar startDate;
    private Calendar endDate;

    @Inject
    private FlightCRUDService flightService;

    public FlightLoader() {
        //empty
    }

    @PostConstruct
    public void init() {

        fillFlightEntities();

    }

    private void fillFlightEntities() {
        startDate = Calendar.getInstance();
        startDate.add(Calendar.MINUTE, 60);
        endDate = Calendar.getInstance();
        endDate.add(Calendar.MINUTE, 180);

        for (Integer i = 0; i < 5; i++) {
            Flight flight = new Flight();
            flight.setFromLocation("Budapest");
            flight.setToLocation("London");
            startDate.add(Calendar.MINUTE, 1);
            endDate.add(Calendar.MINUTE, 1);
            flight.setStartTime(startDate.getTime());
            flight.setArrivalTime(endDate.getTime());
            flightService.createFlight(flight);
        }

    }

}
