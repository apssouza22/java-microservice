package com.apssouza.eventsource.eventstore;

import com.apssouza.aggregates.Aggregate;
import com.apssouza.events.DomainEvent;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author apssouza
 */
public interface EventSourcedRepository {
    
    Aggregate save(Aggregate aggregate) ;

    public List<DomainEvent> getRelatedEvents(UUID uuid);
    
}
