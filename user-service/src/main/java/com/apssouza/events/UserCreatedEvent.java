package com.apssouza.events;

import com.apssouza.commands.UserUpdateCreateCommand;
import java.time.Instant;
import java.util.UUID;

/**
 *
 * @author apssouza
 */
public class UserCreatedEvent implements DomainEvent {
    
    private final UUID uuid;
    private final String type = "Created";
    private final Instant when = Instant.now();
    
    private final UserUpdateCreateCommand command;

    public UserCreatedEvent(UUID uuid, UserUpdateCreateCommand command) {
        this.uuid = uuid;
        this.command = command;
    }
    
    
    @Override
    public UUID uuid() {
        return uuid;
    }
    
    public UserUpdateCreateCommand getCommand(){
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
