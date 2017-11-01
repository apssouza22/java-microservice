package com.apssouza.eventsourcing.services;

import com.apssouza.eventsourcing.aggregates.Aggregate;
import com.apssouza.eventsourcing.eventstore.EventSerializer;
import com.apssouza.eventsourcing.eventstore.EventStoreRepository;
import com.apssouza.eventsourcing.eventstore.EventStream;
import com.apssouza.infra.AppEvent;
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

    @Autowired
    public EventSourcingServiceImpl(
            EventStoreRepository eventStore,
            EventSerializer eventSerializer,
            ApplicationEventPublisher eventPublisher
    ) {
        this.eventStoreRepository = eventStore;
        this.eventSerializer = eventSerializer;
    }

    @Override
    @Transactional
    public Aggregate save(Aggregate aggregate) {
        final List<AppEvent> pendingEvents = aggregate.getUncommittedChanges();
        eventStoreRepository.saveEvents(
                aggregate.getUuid(),
                pendingEvents
                        .stream()
                        .map(eventSerializer::serialize)
                        .collect(toList())
        );
        return aggregate.markChangesAsCommitted();
    }

    @Override
    public List<AppEvent> getRelatedEvents(String uuid) {
        return eventStoreRepository.getEventsForAggregate(uuid)
                .stream()
                .map(eventSerializer::deserialize)
                .collect(toList());
    }
    
    @Override
    public  EventStream getAggregate(String uuid) {
        return eventStoreRepository.findByAggregateUUID(uuid)
                .orElseGet( () -> new EventStream());
    }

}
