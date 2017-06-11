package com.apssouza.kafkaevent.encoders;

import com.apssouza.eventsourcing.events.AbstractDomainEvent;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectReader;
import org.apache.kafka.common.errors.SerializationException;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Logger;
import org.springframework.kafka.support.serializer.JsonDeserializer;

public class EventDeserializer<T extends AbstractDomainEvent> extends JsonDeserializer<T> {

    private static final Logger logger = Logger.getLogger(EventDeserializer.class.getName());

    private volatile ObjectReader reader;

    public EventDeserializer(Class<T> targetType) {
        super(targetType);
    }

    @Override
    public T deserialize(String topic, byte[] data) {
        if (this.reader == null) {
            try {
                JsonNode readTree = this.objectMapper.readTree(data);
                String eventClassName = readTree.get("eventClass").asText();
                Class<? extends T> eventClass = (Class<? extends T>) Class.forName(eventClassName);
                this.reader = this.objectMapper.readerFor(eventClass);
            } catch (Exception e) {
                logger.severe("Could not deserialize event: " + e.getMessage());
                throw new SerializationException("Could not deserialize event", e);
            }
        }
        try {
            T result = null;
            if (data != null) {
                result = this.reader.readValue(data);
            }
            return result;
        } catch (IOException e) {
            throw new SerializationException("Can't deserialize data [" + Arrays.toString(data)
                    + "] from topic [" + topic + "]", e);
        }
    }
}
