package com.apssouza.mailservice.controllers;

import com.apssouza.eventsourcing.commands.EmailCommandHandler;
import com.apssouza.eventsourcing.commands.EmailCreateCommand;
import com.apssouza.eventsourcing.commands.EmailDeleteCommand;
import com.apssouza.eventsourcing.commands.EmailSendCommand;
import com.apssouza.eventsourcing.entities.Email;
import com.apssouza.eventsourcing.events.EmailCreatedEvent;
import com.apssouza.kafkaevent.handlers.KafkaEventConsumer;
import com.apssouza.kafkaevent.handlers.KafkaEventProducer;
import java.time.Instant;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author apssouza
 */
@RestController
public class TestController {

    @Autowired
    EmailCommandHandler emailCommandHandler;
    
    @Autowired
    KafkaEventProducer producer;
    
    @Autowired
    KafkaEventConsumer listener;

    @RequestMapping("event/publish")
    public String send() {
        UUID uuid = UUID.randomUUID();
        Email email = new Email("alex", "apssouza@gmail.com");
        producer.sendEvent(new EmailCreatedEvent(uuid, email));
//        KafkaIntegrationEvent kafkaIntegrationEvent = new KafkaIntegrationEvent(
//                new EmailCreatedEvent(uuid, email),
//                EmailCreatedEvent.class.getName()
//        );
        return "sent";
    }

    @RequestMapping("commander")
    public String index() {
        try {
            UUID uuid = UUID.randomUUID();
            EmailCreateCommand command = new EmailCreateCommand(uuid, "Alexsandro", "apssouza22@gmail.com");
            EmailSendCommand emailSendCommand = new EmailSendCommand(uuid, Instant.now());
            EmailDeleteCommand emailDeleteCommand = new EmailDeleteCommand(uuid);
            emailCommandHandler.create(command);
            emailCommandHandler.send(emailSendCommand);
            emailCommandHandler.delete(emailDeleteCommand);
        } catch (Exception ex) {
            Logger.getLogger(TestController.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return "command executed successfully";
    }

}
