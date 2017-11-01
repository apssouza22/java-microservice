package com.apssouza.eventsourcing.commands;

/**
 * Email create command
 *
 * @author apssouza
 */
public class EmailCreateCommand {

    private final String uuid;

    private final String name;

    private final String email;

    public EmailCreateCommand(String uuid, String name, String email) {
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

    public String getUuid() {
        return uuid;
    }

}
