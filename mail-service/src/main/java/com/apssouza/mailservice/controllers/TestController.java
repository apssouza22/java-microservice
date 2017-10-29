package com.apssouza.mailservice.controllers;

import com.apssouza.eventsourcing.commands.EmailCommandHandler;
import com.apssouza.eventsourcing.commands.EmailCreateCommand;
import com.apssouza.eventsourcing.commands.EmailDeleteCommand;
import com.apssouza.eventsourcing.commands.EmailSendCommand;
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
