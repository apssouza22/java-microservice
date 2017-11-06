package com.apssouza.eventsourcing.events;

import com.apssouza.eventsourcing.entities.Email;
import com.apssouza.infra.AbstractDomainEvent;
import com.apssouza.infra.AppEvent;
import java.time.Instant;

/**
 * Email created event
 *
 * @author apssouza
 */
public class EmailCreatedEvent extends AbstractDomainEvent implements EmailEvent {

    private String uuid;
    private String type = "Created";
    private Instant when = Instant.now();

    private Email email;

    public EmailCreatedEvent() {

    }

    public EmailCreatedEvent(String uuid, Email account) {
        this.uuid = uuid;
        this.email = account;
    }

    public String uuid() {
        return uuid;
    }

    public Email getEmail() {
        return email;
    }

    public String type() {
        return type;
    }

    @Override
    public Instant when() {
        return when;
    }

}
