package com.apssouza.eventsourcing.events;

import com.apssouza.eventsourcing.commands.EmailDeleteCommand;
import java.time.Instant;
import java.util.UUID;

/**
 * Email deleted event
 *
 * @author apssouza
 */
public class EmailDeletedEvent implements DomainEvent {

    private final UUID uuid;
    private final String type = "Deleted";
    private final Instant when = Instant.now();

    private final EmailDeleteCommand command;

    public EmailDeletedEvent(UUID uuid, EmailDeleteCommand command) {
        this.uuid = uuid;
        this.command = command;
    }

    @Override
    public UUID uuid() {
        return uuid;
    }

    public EmailDeleteCommand getCommand() {
        return command;
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
