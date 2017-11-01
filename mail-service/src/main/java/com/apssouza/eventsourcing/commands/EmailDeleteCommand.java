package com.apssouza.eventsourcing.commands;

/**
 * Email delete command
 *
 * @author apssouza
 */
public class EmailDeleteCommand {

    private String uuid;

    public EmailDeleteCommand(String id) {
        this.uuid = id;
    }

    public String getUuid() {
        return uuid;
    }

}
