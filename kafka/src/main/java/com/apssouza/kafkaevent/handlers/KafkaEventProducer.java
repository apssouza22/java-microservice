package com.apssouza.kafkaevent.handlers;

import com.apssouza.eventsourcing.events.AbstractDomainEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 *
 * @author apssouza
 */
@Component
public class KafkaEventProducer {

    @Autowired
    private KafkaTemplate<String, AbstractDomainEvent> kafkaTemplate;

    @Value(value = "${mail.topic.name}")
    private String topicName;

    public void sendEvent(AbstractDomainEvent event) {
        kafkaTemplate.send(topicName, event);
    }
}
