package com.apssouza.infra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

/**
 *
 * The project event publisher handler We are using distributed event with Kafka
 * and local event with Spring event publisher
 *
 * @author apssouza
 */
@Component
@EnableBinding(Source.class)
public class EventPublisher {
    
    private final MessageChannel channel;

    private final ApplicationEventPublisher publisher;

    /**
     *
     * @param source
     * @param publisher
     */
    @Autowired
    public EventPublisher(Source source, ApplicationEventPublisher publisher) {
        this.channel = source.output();
        this.publisher = publisher;
    }

    public void publish(AppEvent event) {
        publisher.publishEvent(event);
    }

    public void stream(AppEvent event) {
        Message<AppEvent> msg = MessageBuilder.withPayload(event)
                .setHeader("type", event.getClass().getSimpleName())
                .build();
        channel.send(msg);
        System.out.println("published");
    }

}
