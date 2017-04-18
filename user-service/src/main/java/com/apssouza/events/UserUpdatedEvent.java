package com.apssouza.events;

import com.apssouza.commands.UserUpdateCreateCommand;
import java.time.Instant;
import java.util.UUID;

/**
 *
 * @author apssouza
 */
public class UserUpdatedEvent implements DomainEvent {

    private final UUID uuid;
    private final String type = "Updated";
    private final Instant when = Instant.now();
    
    private final UserUpdateCreateCommand command;

    public UserUpdatedEvent(UUID uuid, UserUpdateCreateCommand command) {
        this.uuid = uuid;
        this.command = command;
    }

    @Override
    public UUID uuid() {
        return uuid;
    }

    public UserUpdateCreateCommand getCommand() {
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
}
