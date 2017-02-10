package com.muranyibence.flight.client;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 *
 * @author Bence
 */
@MessageDriven(mappedName = "jms/flightTopic")
public class FlightMessageListener implements MessageListener {

    private static final Logger LOGGER = Logger.getLogger(FlightMessageListener.class.getName());

    @Override
    public void onMessage(Message message) {
        String messagebody = null;
        try {
            messagebody = message.getBody(String.class);
        } catch (JMSException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }

        LOGGER.info("[FLIGHTMESSAGE] "+messagebody);

    }

}
