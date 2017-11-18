package com.apssouza.eventsourcing.eventstore;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;

public interface EventStoreRepository extends JpaRepository<EventStream, Long> {

    Optional<EventStream> findByAggregateUUID(String uuid);

    default EventStream saveEvents(String aggregateId, List<EventDescriptor> events) {
        final EventStream eventStream = findByAggregateUUID(aggregateId)
                .orElseGet(() -> new EventStream(aggregateId));
        eventStream.addEvents(events);
        return save(eventStream);
    }

    default List<EventDescriptor> getEventsForAggregate(String aggregateId) {
        return findByAggregateUUID(aggregateId)
                .map(EventStream::getEvents)
                .orElse(emptyList());

    }
}
