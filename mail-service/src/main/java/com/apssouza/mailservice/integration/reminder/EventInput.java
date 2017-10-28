package com.apssouza.mailservice.integration.reminder;

import org.apache.log4j.Logger;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.handler.annotation.Payload;

/**
 *
 * The Stream Kafka processor
 *
 * @author apssouza
 */
@EnableBinding(Sink.class)
public class EventInput {

    Logger LOG = Logger.getLogger(EventInput.class);

    @StreamListener(target = Sink.INPUT, condition = "headers['type']=='TodoCreatedEvent'")
    public void transform(@Payload TodoCreatedEvent event) {
        
        LOG.info("when = "+ event.when());
        LOG.info("transfoming..");
    }

}
