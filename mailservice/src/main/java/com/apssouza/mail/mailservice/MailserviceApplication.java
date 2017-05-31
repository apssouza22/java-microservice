package com.apssouza.mail.mailservice;

import com.apssouza.kafkaevent.Greeting;
import com.apssouza.kafkaevent.kafka.MessageListener;
import com.apssouza.kafkaevent.kafka.MessageProducer;
import java.util.concurrent.TimeUnit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.apssouza.kafkaevent"})
public class MailserviceApplication {


    public static void main(String[] args) throws Exception {

        ConfigurableApplicationContext context =  SpringApplication.run(MailserviceApplication.class, args);
       
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

}
