package com.apssouza.eventsourcing.events;

import com.apssouza.eventsourcing.commands.EmailDeleteCommand;
import com.apssouza.infra.AbstractDomainEvent;
import com.apssouza.infra.AppEvent;
import java.time.Instant;

/**
 * Email deleted event
 *
 * @author apssouza
 */
public class EmailDeletedEvent extends AbstractDomainEvent implements AppEvent {

    private final String uuid;
    private final String type = "Deleted";
    private final Instant when = Instant.now();

    private final EmailDeleteCommand command;

    public EmailDeletedEvent(String uuid, EmailDeleteCommand command) {
        this.uuid = uuid;
        this.command = command;
    }

    @Override
    public String uuid() {
        return uuid;
    }

    public EmailDeleteCommand getCommand() {
        return command;
    }

    public String type() {
        return type;
    }

    @Override
    public Instant when() {
        return when;
    }

}
