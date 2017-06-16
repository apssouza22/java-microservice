package com.apssouza.eventsourcing.read.listeners;

import com.apssouza.eventsourcing.events.EmailCreatedEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * Email created listener
 *
 * @author apssouza
 */
@Component
public class EmailCreatedListener {

    private static final Logger LOG = Logger.getLogger(EmailCreatedListener.class.getName());

    @EventListener
    public void listener(EmailCreatedEvent event) {
        LOG.log(Level.ALL, event.type());
        //TODO: update email data views
    }

}
