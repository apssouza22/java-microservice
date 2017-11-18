package com.apssouza.eventsourcing.eventstore;


import javax.persistence.*;
import java.time.Instant;

@Entity(name = "event_descriptors")
public class EventDescriptor {

    @Id
    @GeneratedValue(generator = "event_descriptors_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "event_descriptors_seq", sequenceName = "event_descriptors_seq", allocationSize = 1)
    private Long id;

    @Column(nullable = false, length = 600)
    private String body;

    @Column(nullable = false, name = "occurred_at")
    private Instant occurredAt = Instant.now();

    @Column(nullable = false, length = 60)
    private String type;
    
    @ManyToOne
    private EventStream eventStream;

    EventDescriptor(String body, Instant occurredAt, String type) {
        this.body = body;
        this.occurredAt = occurredAt;
        this.type = type;
    }

    private EventDescriptor() {
    }

    public Long getId() {
        return id;
    }

    public String getBody() {
        return body;
    }

    public Instant getOccurredAt() {
        return occurredAt;
    }

    public String getType() {
        return type;
    }

    public EventStream getEventStream() {
        return eventStream;
    }
    
}
