package com.apssouza.eventsourcing.eventstore;

import com.apssouza.infra.AppEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;

@Component
public class EventSerializer {

    private final ObjectMapper objectMapper;

    public EventSerializer() {
        this.objectMapper = new ObjectMapper();
        objectMapper.configure(FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    public EventDescriptor serialize(AppEvent event) {
        try {
            return new EventDescriptor(objectMapper.writeValueAsString(event), event.when(), AppEvent.class.getCanonicalName());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public AppEvent deserialize(EventDescriptor eventDescriptor) {
        try {
            return objectMapper.readValue(eventDescriptor.getBody(), AppEvent.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
