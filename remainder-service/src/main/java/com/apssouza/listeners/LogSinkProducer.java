package com.apssouza.listeners;

import com.apssouza.logging.LogSink;
import com.apssouza.events.TodoCreatedEvent;
import java.util.logging.Logger;


import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class LogSinkProducer {

    @EventListener
    public LogSink produce(TodoCreatedEvent event) {
        return Logger.getLogger(TodoCreatedEvent.class.getName())::info;
    }

}
