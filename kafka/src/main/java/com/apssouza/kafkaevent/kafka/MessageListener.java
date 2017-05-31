package com.apssouza.kafkaevent.kafka;

import com.apssouza.kafkaevent.Greeting;
import java.util.concurrent.CountDownLatch;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 *
 * @author apssouza
 */
@Component
public class MessageListener {
 
    public CountDownLatch greetingLatch = new CountDownLatch(1);

        @KafkaListener(topics = "${greeting.topic.name}", containerFactory = "greetingKafkaListenerContainerFactory")
        public void greetingListener(Greeting greeting) {
            System.out.println("Recieved greeting message: " + greeting);
            this.greetingLatch.countDown();
        }
}
