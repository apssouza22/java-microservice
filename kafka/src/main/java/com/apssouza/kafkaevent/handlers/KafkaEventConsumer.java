package com.apssouza.kafkaevent.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import com.apssouza.eventsourcing.events.AbstractDomainEvent;

/**
 * Email Kafka event consumer
 * @author apssouza
 */
@Component
public class KafkaEventConsumer {

    @Autowired
    public ApplicationEventPublisher eventPublisher;
    

    @KafkaListener(
            topics = "${mail.topic.name}",
            containerFactory = "emailKafkaListenerContainerFactory"
    )
    public void emailListener(AbstractDomainEvent emailEvent) {
        System.out.println("Recieved event " + emailEvent.getEventClass());
        
        eventPublisher.publishEvent(emailEvent);
    }
}
