package com.jcore.dasyel.kafka_filter;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.handler.annotation.SendTo;

@EnableBinding(Processor.class)
@Configuration
public class MessageFilter {

    @StreamListener(Processor.INPUT)
    @SendTo(Processor.OUTPUT)
    public ChatMessage transform(final ChatMessage chatmessage) {
        final String contents = chatmessage.getContents().toUpperCase() + "!";
        return new ChatMessage(contents, chatmessage.getTime());
    }
}
