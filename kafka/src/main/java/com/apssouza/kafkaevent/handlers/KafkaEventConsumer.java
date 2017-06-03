package com.apssouza.kafkaevent.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import com.apssouza.eventsourcing.events.AbstractDomainEvent;

/**
 *
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
        System.out.println("Recieved event " + emailEvent.type());
        
        //Todo a way of republish a event locally, write a serializer to get the real event
        eventPublisher.publishEvent(emailEvent);
    }
}
