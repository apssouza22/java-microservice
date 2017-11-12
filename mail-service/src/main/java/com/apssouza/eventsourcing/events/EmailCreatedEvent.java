package com.apssouza.eventsourcing.events;

import com.apssouza.eventsourcing.entities.Email;
import java.time.Instant;

/**
 * Email created event
 *
 * @author apssouza
 */
public class EmailCreatedEvent implements EmailEvent {

    private Email email;

    public EmailCreatedEvent(String uuid) {
    }

    protected String uuid;

    protected Instant when = Instant.now();

    @Override
    public String uuid() {
        return uuid;
    }

    @Override
    public Instant when() {
        return when;
    }

    public EmailCreatedEvent(String uuid, Email account) {
        this.email = account;
        this.uuid = uuid;
    }

    @Override
    public Email getEmail() {
        return email;
    }

}
