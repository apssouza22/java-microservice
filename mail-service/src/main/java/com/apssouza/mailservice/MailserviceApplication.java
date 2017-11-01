package com.apssouza.mailservice;

import com.apssouza.eventsourcing.EventsourcingApplication;
import com.apssouza.eventsourcing.commands.EmailCommandHandler;
import com.apssouza.eventsourcing.commands.EmailCreateCommand;
import com.apssouza.eventsourcing.commands.EmailDeleteCommand;
import com.apssouza.eventsourcing.commands.EmailDeliveryCommand;
import com.apssouza.eventsourcing.commands.EmailSendCommand;
import java.time.Instant;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class MailserviceApplication {

    /**
     * Comunicating with Kafka
     * @param args
     * @throws Exception 
     */
    public static void main(String[] args) throws Exception {

        ConfigurableApplicationContext context =  SpringApplication.run(MailserviceApplication.class, args);
//       
//        KafkaEventProducer producer = context.getBean(KafkaEventProducer.class);
//        KafkaEventConsumer listener = context.getBean(KafkaEventConsumer.class);
//        UUID uuid = UUID.randomUUID();
//        Email email = new Email("alex", "apssouza@gmail.com");
//        KafkaIntegrationEvent kafkaIntegrationEvent = new KafkaIntegrationEvent(
//                new EmailCreatedEvent(uuid, email), 
//                EmailCreatedEvent.class.getName()
//        );
//        producer.sendEvent(kafkaIntegrationEvent);
//        
//        
//
//        context.close();
    }
    
    /**
     * Using event sourcing
     * @param args 
     */
     public static void main2(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(EventsourcingApplication.class, args);
         EmailCommandHandler commandHandler = context.getBean(EmailCommandHandler.class);
        
        String randomUUID = UUID.randomUUID().toString();
        
        EmailCreateCommand commandCreate = new EmailCreateCommand(randomUUID, "Alex", "apssouza22@gmail.com");
        EmailSendCommand send = new EmailSendCommand(randomUUID, Instant.now());
        EmailDeliveryCommand delivery = new EmailDeliveryCommand(randomUUID, Instant.now());
        EmailDeleteCommand delete = new EmailDeleteCommand(randomUUID);
        try {
            commandHandler.create(commandCreate);
            commandHandler.send(send);
            commandHandler.delivery(delivery);
            commandHandler.delete(delete);
        } catch (Exception ex) {
            Logger.getLogger(EventsourcingApplication.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
