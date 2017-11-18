package com.apssouza.eventsourcing.eventstore;


import java.io.Serializable;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;
import static javax.persistence.FetchType.EAGER;

@Entity(name = "event_streams")
public class EventStream implements Serializable {
    
    @Id
    @GeneratedValue(generator = "event_stream_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "event_stream_seq", sequenceName = "event_stream_seq", allocationSize = 1)
    private Long id;

    @Column(unique = true, nullable = false, name = "aggregate_uuid", length = 36)
    private String aggregateUUID;

    @Version
    @Column(nullable = false)
    private long version;

    @OneToMany(
            cascade = CascadeType.ALL, 
            orphanRemoval = true, 
            fetch = EAGER
    )
    private List<EventDescriptor> events;

    public EventStream() {
        this.events = new ArrayList<>();
    }

    EventStream(String aggregateUUID) {
        this.events = new ArrayList<>();
        this.aggregateUUID = aggregateUUID;
    }

    public Long getId() {
        return id;
    }

    public String getAggregateUUID() {
        return aggregateUUID;
    }

    public long getVersion() {
        return version;
    }

    void addEvents(List<EventDescriptor> events) {
        this.events.addAll(events);
    }

    List<EventDescriptor> getEvents() {
        return events
                .stream()
                .sorted(comparing(EventDescriptor::getOccurredAt))
                .collect(toList());
    }

}
