package com.apssouza.events;

import com.apssouza.commands.UserDeleteCommand;
import java.time.Instant;
import java.util.UUID;

/**
 *
 * @author apssouza
 */
public class UserDeletedEvent implements DomainEvent {
    
    private final UUID uuid;
    private final String type = "Deleted";
    private final Instant when = Instant.now();
    
    private final UserDeleteCommand command;

    public UserDeletedEvent(UUID uuid, UserDeleteCommand command) {
        this.uuid = uuid;
        this.command = command;
    }
    
    
    @Override
    public UUID uuid() {
        return uuid;
    }
    
    public UserDeleteCommand getCommand(){
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
