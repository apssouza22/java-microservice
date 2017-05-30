package com.apssouza.mail.mailservice;

import com.apssouza.eventsourcing.Greeting;
import com.apssouza.eventsourcing.kafka.MessageListener;
import com.apssouza.eventsourcing.kafka.MessageProducer;
import java.util.concurrent.TimeUnit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MailserviceApplication {


    public static void main(String[] args) throws Exception {

        ConfigurableApplicationContext context = SpringApplication.run(MailserviceApplication.class, args);

        MessageProducer producer = context.getBean(MessageProducer.class);
        MessageListener listener = context.getBean(MessageListener.class);

        /*
         * Sending message to 'greeting' topic. This will send
         * and recieved a java object with the help of 
         * greetingKafkaListenerContainerFactory.
         */
        producer.sendGreetingMessage(new Greeting("Greetings", "World!"));
        listener.greetingLatch.await(10, TimeUnit.SECONDS);

        context.close();
    }

    @Bean
    public MessageProducer messageProducer() {
        return new MessageProducer();
    }
    
    @Bean
    public MessageListener messageListener() {
        return new MessageListener();
    }
}
