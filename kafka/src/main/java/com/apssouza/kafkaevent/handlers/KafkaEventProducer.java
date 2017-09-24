package com.apssouza.kafkaevent.handlers;

import com.apssouza.eventsourcing.events.AbstractDomainEvent;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * Kafka event email topic producer
 *
 * @author apssouza
 */
@Component
public class KafkaEventProducer {
    
    private static final Logger LOG = Logger.getLogger(KafkaEventProducer.class);

    @Autowired
    private KafkaTemplate<String, AbstractDomainEvent> kafkaTemplate;

    @Value(value = "${mail.topic.name}")
    private String topicName;

    public void sendEvent(AbstractDomainEvent event) {
        LOG.info("sending event " + event.getEventClass());
        kafkaTemplate.send(topicName, event);
    }
}
