package com.apssouza.eventsourcing.events;

import com.apssouza.eventsourcing.entities.Email;
import com.apssouza.infra.AbstractDomainEvent;
import java.time.Instant;

/**
 * Email delivered event
 *
 * @author apssouza
 */
public class EmailDeliveredEvent extends AbstractDomainEvent implements EmailEvent {

    private final String uuid;
    private final Instant when = Instant.now();
    private final String type = "sent";
    
    private final Email email;

    public EmailDeliveredEvent(String uuid, Email email) {
        this.uuid = uuid;
        this.email = email;
    }

    @Override
    public String uuid() {
        return uuid;
    }

    public String type() {
        return type;
    }

    @Override
    public Instant when() {
        return when;
    }

    @Override
    public Email getEmail() {
        return email;
    }

}
