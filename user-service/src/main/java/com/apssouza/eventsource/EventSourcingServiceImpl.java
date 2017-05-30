package com.apssouza.eventsource;

import com.apssouza.aggregates.Aggregate;
import com.apssouza.events.DomainEvent;
import com.apssouza.eventsource.eventstore.EventSerializer;
import com.apssouza.eventsource.eventstore.EventStoreRepository;
import java.util.List;
import java.util.UUID;
import static java.util.stream.Collectors.toList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

/**
 *
 * @author apssouza
 */
public class EventSourcingServiceImpl implements EventSourcingService{

    private final EventSerializer eventSerializer;
    private final EventStoreRepository eventStoreRepository;
    private final ApplicationEventPublisher eventPublisher;

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

}
