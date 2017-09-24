package com.apssouza.kafkaevent.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import com.apssouza.eventsourcing.events.AbstractDomainEvent;
import org.apache.log4j.Logger;

/**
 * Email Kafka event consumer
 * @author apssouza
 */
@Component
public class KafkaEventConsumer {

    private static final Logger LOG = Logger.getLogger(KafkaEventConsumer.class);
    
    @Autowired
    public ApplicationEventPublisher eventPublisher;
    

    @KafkaListener(
            topics = "${mail.topic.name}",
            containerFactory = "emailKafkaListenerContainerFactory"
    )
    public void emailListener(AbstractDomainEvent emailEvent) {
        LOG.info("Recieved event " + emailEvent.getEventClass());
        
        eventPublisher.publishEvent(emailEvent);
    }
}
