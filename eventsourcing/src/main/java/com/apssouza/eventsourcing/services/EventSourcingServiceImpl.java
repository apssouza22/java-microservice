package com.apssouza.eventsourcing.services;

import com.apssouza.eventsourcing.aggregates.Aggregate;
import com.apssouza.eventsourcing.events.DomainEvent;
import com.apssouza.eventsourcing.eventstore.EventSerializer;
import com.apssouza.eventsourcing.eventstore.EventStoreRepository;
import com.apssouza.eventsourcing.eventstore.EventStream;
import java.util.List;
import java.util.UUID;
import static java.util.stream.Collectors.toList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author apssouza
 */
@Service
@Transactional(readOnly = true)
public class EventSourcingServiceImpl implements EventSourcingService{

    private final EventSerializer eventSerializer;
    private final EventStoreRepository eventStoreRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Autowired
    public EventSourcingServiceImpl(
            EventStoreRepository eventStore,
            EventSerializer eventSerializer,
            ApplicationEventPublisher eventPublisher
    ) {
        this.eventStoreRepository = eventStore;
        this.eventSerializer = eventSerializer;
        this.eventPublisher = eventPublisher;
    }

    @Override
    @Transactional
    public Aggregate save(Aggregate aggregate) {
        final List<DomainEvent> pendingEvents = aggregate.getUncommittedChanges();
        eventStoreRepository.saveEvents(
                aggregate.getUuid(),
                pendingEvents
                        .stream()
                        .map(eventSerializer::serialize)
                        .collect(toList())
        );
        pendingEvents.forEach(eventPublisher::publishEvent);
        return aggregate.markChangesAsCommitted();
    }

    @Override
    public List<DomainEvent> getRelatedEvents(UUID uuid) {
        return eventStoreRepository.getEventsForAggregate(uuid)
                .stream()
                .map(eventSerializer::deserialize)
                .collect(toList());
    }
    
    @Override
    public  EventStream getAggregate(UUID uuid) {
        return eventStoreRepository.findByAggregateUUID(uuid)
                .orElseGet( () -> new EventStream());
    }

}
