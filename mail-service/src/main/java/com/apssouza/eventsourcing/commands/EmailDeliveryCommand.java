package com.apssouza.eventsourcing.commands;

import java.time.Instant;
import java.util.UUID;

/**
 * Email delivery command
 *
 * @author apssouza
 */
public class EmailDeliveryCommand {

    private final UUID uuid;
    private final Instant instant;

    public EmailDeliveryCommand(UUID uuid, Instant instant) {
        this.uuid = uuid;
        this.instant = instant;
    }

    public UUID getUuid() {
        return uuid;
    }

    public Instant getInstant() {
        return instant;
    }

}
