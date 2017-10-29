package com.apssouza.infra;

import java.time.Instant;
import java.util.UUID;

/**
 * Abstract system event. The domain event across all services
 *
 * @author apssouza
 */
public abstract class AbstractDomainEvent implements AppEvent {

    protected UUID uuid;
    protected Instant when = Instant.now();

    public UUID uuid() {
        return uuid;
    }

    public Instant when() {
        return when;
    }
}
