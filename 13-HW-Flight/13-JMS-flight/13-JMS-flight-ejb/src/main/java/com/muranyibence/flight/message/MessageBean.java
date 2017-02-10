package com.muranyibence.flight.message;

import com.muranyibence.flight.entity.Flight;
import com.muranyibence.flight.service.FlightCRUDService;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.Topic;

/**
 *
 * @author Bence
 */
@Stateless
public class MessageBean {

    @Inject
    private JMSContext context;

    @Inject
    private FlightCRUDService flightService;
    @Resource(lookup = "jms/flightTopic")
    private Topic topic;
    private List<Flight> flights;
    private Calendar calendar;
    private Date start;
    private Date end;
    private static final String FLIGHT_ID="Flight ID: ";
    private static final String FROM=" From: ";
        private static final String TO=" To: ";
    public MessageBean() {
        //empty
    }

    @Inject
    public MessageBean(JMSContext context) {
        this.context = context;
    }

    public void deletedFlightMessage(Flight flight) {
        context.createProducer().send(topic, FLIGHT_ID + flight.getId() + FROM + flight.getFromLocation() + TO + flight.getToLocation() + " deleted");
    }

    public void modifiedFlightMessage(Flight flight) {
        context.createProducer().send(topic, FLIGHT_ID + flight.getId() + FROM + flight.getFromLocation() + TO + flight.getToLocation() + " modified");
    }

    @Schedule(minute = "*", hour = "*")
    public void startsFlightInHour() {
        calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, 60);
        start = calendar.getTime();
        calendar.add(Calendar.MINUTE, 1);
        end = calendar.getTime();
        flights = flightService.getAllFlight();
        for (Flight flight : flights) {
            if (flight.getStartTime().after(start) && flight.getStartTime().before(end)) {
                context.createProducer().send(topic, FLIGHT_ID + flight.getId() + FROM + flight.getFromLocation() + TO + flight.getToLocation() + " starts at: " + flight.getStartTime());
            }
        }
    }
}
