package com.apssouza.eventsourcing.events;

import java.time.Instant;
import java.util.UUID;

/**
 *
 * @author apssouza
 */
public abstract class AbstractDomainEvent  {

    protected  UUID uuid;
    protected  String type = "Created";
    protected  Instant when = Instant.now();
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
    
    public void setEventClass(String eventClass){
        this.eventClass = eventClass;
    }
}
