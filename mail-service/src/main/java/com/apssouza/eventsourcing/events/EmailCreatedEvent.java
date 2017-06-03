package com.apssouza.eventsourcing.events;

import com.apssouza.eventsourcing.entities.Email;
import java.time.Instant;
import java.util.UUID;

/**
 *
 * @author apssouza
 */
public class EmailCreatedEvent extends AbstractDomainEvent implements DomainEvent {
    
    private final UUID uuid;
    private final String type = "Created";
    private final Instant when = Instant.now();
    
    private final Email email;

    public EmailCreatedEvent(UUID uuid, Email account) {
        this.uuid = uuid;
        this.email = account;
    }
    
    
    @Override
    public UUID uuid() {
        return uuid;
    }
    
    public Email getEmail(){
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
}
