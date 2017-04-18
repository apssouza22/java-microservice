package com.apssouza.events;

import java.time.Instant;
import java.util.UUID;

/**
 *
 * @author apssouza
 */
public interface DomainEvent {

    UUID uuid();
    String type();
    Instant when();
}
