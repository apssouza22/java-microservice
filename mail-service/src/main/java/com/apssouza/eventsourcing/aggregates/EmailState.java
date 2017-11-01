package com.apssouza.eventsourcing.aggregates;

/**
 * Email state
 *
 * @author apssouza
 */
public enum EmailState implements ObjectState {
    CREATED,
    SENT,
    DELIVERED,
    READ,
    FAILED,
    REPLIED,
    DELETED
}
