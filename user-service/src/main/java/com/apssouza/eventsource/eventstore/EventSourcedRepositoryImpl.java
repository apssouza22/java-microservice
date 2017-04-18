package com.apssouza.eventsource.eventstore;

import com.apssouza.aggregates.Aggregate;
import com.apssouza.events.DomainEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

import static java.util.stream.Collectors.toList;

@Component
public class EventSourcedRepositoryImpl implements EventSourcedRepository {

    private final EventStore eventStore;
    private final EventSerializer eventSerializer;

    @Autowired
    public EventSourcedRepositoryImpl(
            EventStore eventStore,
            EventSerializer eventSerializer
    ) {
        this.eventStore = eventStore;
        this.eventSerializer = eventSerializer;
    }

    @Override
    public Aggregate save(Aggregate aggregate) {
        eventStore.saveEvents(
                aggregate.getUuid(),
                aggregate.getChanges()
                .stream()
                .map(eventSerializer::serialize)
                .collect(toList()));
        return aggregate;
    }


    @Override
    public List<DomainEvent> getRelatedEvents(UUID uuid) {
        return eventStore.getEventsForAggregate(uuid)
                .stream()
                .map(eventSerializer::deserialize)
                .collect(toList());
    }

}
