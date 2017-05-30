package com.apssouza.aggregates;

import com.apssouza.events.DomainEvent;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author apssouza
 */
public interface Aggregate {
    
    UUID getUuid();
    
    List<DomainEvent> getUncommittedChanges();
    
    UserAggregate markChangesAsCommitted();
    
    UserState getState();
    
}
