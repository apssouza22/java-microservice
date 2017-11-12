package com.apssouza.eventsourcing.commands;

import com.apssouza.eventsourcing.entities.Email;

/**
 * Email create command
 *
 * @author apssouza
 */
public class EmailCreateCommand {

    private final String uuid;

    private final Email email;

    public EmailCreateCommand(String uuid, Email email) {
        this.uuid = uuid;
        this.email = email;
    }

    public Email getEmail() {
        return email;
    }

    public String getUuid() {
        return uuid;
    }

}
