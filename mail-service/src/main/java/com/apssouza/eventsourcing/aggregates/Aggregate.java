package com.apssouza.eventsourcing.aggregates;

import com.apssouza.eventsourcing.events.DomainEvent;
import java.util.List;
import java.util.UUID;

/**
 * The aggregate interface
 *
 * @author apssouza
 */
public interface Aggregate {

    UUID getUuid();

    List<DomainEvent> getUncommittedChanges();

    Aggregate markChangesAsCommitted();

    ObjectState getState();

}
