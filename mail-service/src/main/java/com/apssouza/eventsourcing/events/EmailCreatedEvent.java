package com.apssouza.eventsourcing.events;

import com.apssouza.eventsourcing.entities.Email;
import java.time.Instant;
import java.util.UUID;

/**
 * Email created event
 *
 * @author apssouza
 */
public class EmailCreatedEvent extends AbstractDomainEvent implements DomainEvent {

    private UUID uuid;
    private String type = "Created";
    private Instant when = Instant.now();

    private Email email;

    public EmailCreatedEvent() {

    }

    public EmailCreatedEvent(UUID uuid, Email account) {
        this.uuid = uuid;
        this.email = account;
    }

    @Override
    public UUID uuid() {
        return uuid;
    }

    public Email getEmail() {
        return email;
    }

    @Override
    public String type() {
        return type;
    }

    @Override
    public Instant when() {
        return when;
    }

    @Override
    public String getEventClass() {
        return this.getClass().getName();
    }

}
