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
    protected String type = "Created";
    protected Instant when = Instant.now();
    protected String eventClass;

    public UUID uuid() {
        return uuid;
    }

    public Instant when() {
        return when;
    }

    public String getEventClass() {
        return eventClass;
    }

    public void setEventClass(String eventClass) {
        this.eventClass = eventClass;
    }
}
