package com.apssouza.eventsourcing.events;

import java.time.Instant;
import java.util.UUID;

/**
 * Application Domain event
 *
 * @author apssouza
 */
public interface DomainEvent {

    UUID uuid();

    String type();

    Instant when();

    String getEventClass();
}
