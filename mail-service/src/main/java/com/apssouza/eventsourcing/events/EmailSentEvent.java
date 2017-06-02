package com.apssouza.eventsourcing.events;

import com.apssouza.eventsourcing.entities.Email;
import java.time.Instant;
import java.util.UUID;

/**
 *
 * @author apssouza
 */
public class EmailSentEvent implements DomainEvent {

    private final UUID uuid;
    private final Instant when = Instant.now();
    private final String type = "sent";
    
    public EmailSentEvent(UUID uuid) {
        this.uuid = uuid;
    }

    @Override
    public UUID uuid() {
        return uuid;
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
