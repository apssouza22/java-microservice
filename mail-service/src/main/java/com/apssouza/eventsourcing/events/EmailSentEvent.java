package com.apssouza.eventsourcing.events;

import com.apssouza.infra.AbstractDomainEvent;
import com.apssouza.infra.AppEvent;
import java.time.Instant;
import java.util.UUID;

/**
 * Email sent event
 *
 * @author apssouza
 */
public class EmailSentEvent extends AbstractDomainEvent implements AppEvent {

    private final String uuid;
    private final Instant when = Instant.now();
    private final String type = "sent";

    public EmailSentEvent(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public String uuid() {
        return uuid;
    }

    public String type() {
        return type;
    }

    @Override
    public Instant when() {
        return when;
    }

}
