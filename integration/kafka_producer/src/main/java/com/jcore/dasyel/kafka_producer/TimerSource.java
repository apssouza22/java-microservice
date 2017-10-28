package com.jcore.dasyel.kafka_producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableBinding(Source.class)
public class TimerSource {

    private final MessageChannel channel;

    @Autowired
    public TimerSource(Source source) {
        this.channel = source.output();
    }

    @PostMapping("/publish")
    public void publish() {
        ChatMessage chatMessage = new ChatMessage("hello world", System.currentTimeMillis());
        Message<ChatMessage> msg = MessageBuilder.withPayload(chatMessage).build();
        channel.send(msg);
        System.out.println("published");
    }

}
