package com.apssouza.eventsourcing.commands;

import java.util.UUID;

/**
 * Email create command
 *
 * @author apssouza
 */
public class EmailCreateCommand {

    private UUID uuid;

    private String name;

    private String email;

    public EmailCreateCommand(UUID uuid, String name, String email) {
        this.uuid = uuid;
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public UUID getUuid() {
        return uuid;
    }

}
