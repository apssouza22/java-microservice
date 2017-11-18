package com.apssouza.eventsourcing.events;

import com.apssouza.eventsourcing.entities.Email;
import com.apssouza.infra.AbstractDomainEvent;

/**
 * Email sent event
 *
 * @author apssouza
 */
public class EmailSentEvent extends AbstractDomainEvent implements EmailEvent {

    private final String type = "sent";
    
    private Email email;

    public EmailSentEvent(String uuid, Email email) {
        this.uuid = uuid;
        this.email = email;
    }

    @Override
    public Email getEmail() {
        return email;
    }

}
