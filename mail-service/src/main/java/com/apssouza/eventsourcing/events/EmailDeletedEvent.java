package com.apssouza.eventsourcing.events;

import com.apssouza.eventsourcing.commands.EmailDeleteCommand;
import com.apssouza.eventsourcing.entities.Email;
import com.apssouza.infra.AbstractDomainEvent;
import com.apssouza.infra.AppEvent;
import java.time.Instant;

/**
 * Email deleted event
 *
 * @author apssouza
 */
public class EmailDeletedEvent extends AbstractDomainEvent implements EmailEvent {

    private final String uuid;
    private final String type = "Deleted";
    private final Instant when = Instant.now();
    
    private Email email;

    /**
     *
     * @param uuid
     * @param email
     */
    public EmailDeletedEvent(String uuid, Email email) {
        this.uuid = uuid;
        this.email = email;
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

    public Email getEmail() {
        return email;
    }

}
