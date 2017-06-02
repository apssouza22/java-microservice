package com.apssouza.eventsourcing;

import java.time.Instant;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 *
 * @author apssouza
 */
@SpringBootApplication
public class EventsourcingApplication {
    
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(EventsourcingApplication.class, args);
//        EmailCommandHandler commandHandler = context.getBean(EmailCommandHandler.class);
//        
//        UUID randomUUID = UUID.randomUUID();
//        
//        EmailCreateCommand commandCreate = new EmailCreateCommand(randomUUID, "Alex", "apssouza22@gmail.com");
//        EmailSendCommand send = new EmailSendCommand(randomUUID, Instant.now());
//        EmailDeliveryCommand delivery = new EmailDeliveryCommand(randomUUID, Instant.now());
//        EmailDeleteCommand delete = new EmailDeleteCommand(randomUUID);
//        try {
//            commandHandler.create(commandCreate);
//            commandHandler.send(send);
//            commandHandler.delivery(delivery);
//            commandHandler.delete(delete);
//        } catch (Exception ex) {
//            Logger.getLogger(EventsourcingApplication.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
}
