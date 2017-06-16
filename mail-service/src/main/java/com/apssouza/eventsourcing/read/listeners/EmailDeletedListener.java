package com.apssouza.eventsourcing.read.listeners;

import com.apssouza.eventsourcing.events.EmailCreatedEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * Email deleted listener
 *
 * @author apssouza
 */
@Component
public class EmailDeletedListener {

    private static final Logger LOG = Logger.getLogger(EmailDeletedListener.class.getName());

    @EventListener
    public void listener(EmailCreatedEvent event) {
        LOG.log(Level.ALL, event.getEmail().toString());
        //TODO: update views
    }
}
