package com.apssouza.mailservice.controllers;

import com.apssouza.eventsourcing.aggregates.EmailState;
import com.apssouza.eventsourcing.commands.EmailCommandHandler;
import com.apssouza.eventsourcing.commands.EmailCreateCommand;
import com.apssouza.eventsourcing.entities.Email;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
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
@RequestMapping("/send")
public class NotifyController {

    @Autowired
    EmailCommandHandler emailCommandHandler;

    @RequestMapping("email")
    public CompletableFuture<String> sendEmail() {
        CompletableFuture<String> result = CompletableFuture.completedFuture("Email sent successfully");
        try {
            String uuid = UUID.randomUUID().toString();
            EmailCreateCommand command = new EmailCreateCommand(
                    uuid,
                    new Email("Alexsandro", "apssouza22@gmail.com", EmailState.CREATED)
            );
            Executors.newCachedThreadPool().submit(() -> {
                emailCommandHandler.create(command);
                return null;
            });
        } catch (Exception ex) {
            Logger.getLogger(NotifyController.class.getName())
                    .log(Level.SEVERE, null, ex);
        }

        return result;
    }

}
