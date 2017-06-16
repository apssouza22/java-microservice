package com.apssouza.eventsourcing.events;

import java.time.Instant;
import java.util.UUID;

/**
 * Email sent event
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

    @Override
    public String getEventClass() {
        return this.getClass().getName();
    }
}
