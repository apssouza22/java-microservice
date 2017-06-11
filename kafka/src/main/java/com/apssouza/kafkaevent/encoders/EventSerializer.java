package com.apssouza.kafkaevent.encoders;

import com.apssouza.eventsourcing.events.AbstractDomainEvent;
import java.io.IOException;
import org.apache.kafka.common.errors.SerializationException;
import org.springframework.kafka.support.serializer.JsonSerializer;

public class EventSerializer<T extends AbstractDomainEvent> extends JsonSerializer {

    public byte[] serialize(String topic, T data) {
        try {
            byte[] result = null;
            if (data != null) {
                data.setEventClass(data.getClass().getCanonicalName());
                result = this.objectMapper.writeValueAsBytes(data);
            }
            return result;
        } catch (IOException ex) {
            throw new SerializationException("Can't serialize data [" + data + "] for topic [" + topic + "]", ex);
        }
    }

}
