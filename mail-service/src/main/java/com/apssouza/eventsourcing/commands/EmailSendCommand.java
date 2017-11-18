/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apssouza.eventsourcing.commands;

import java.time.Instant;
import java.util.UUID;

/**
 * Email send command
 *
 * @author apssouza
 */
public class EmailSendCommand {

    private final String uuid;
    private final Instant instant;

    public EmailSendCommand(String uuid, Instant instant) {
        this.uuid = uuid;
        this.instant = instant;
    }

    public String getUuid() {
        return uuid;
    }

    public Instant getInstant() {
        return instant;
    }

}
