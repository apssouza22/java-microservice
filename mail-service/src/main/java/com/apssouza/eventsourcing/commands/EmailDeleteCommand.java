package com.apssouza.eventsourcing.commands;

import java.util.UUID;

/**
 *
 * @author apssouza
 */
public class EmailDeleteCommand {

    private UUID uuid;
    
    public EmailDeleteCommand(UUID id) {
        this.uuid = id;
    }

    public UUID getUuid() {
        return uuid;
    }
    
}
