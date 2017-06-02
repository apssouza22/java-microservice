package com.apssouza.eventsourcing.aggregates;

/**
 *
 * @author apssouza
 */
public enum EmailState implements ObjectState{
    CREATED,
    SENT,
    DELIVERED,
    READ,
    FAILED,
    REPLIED,
    DELETED
}
