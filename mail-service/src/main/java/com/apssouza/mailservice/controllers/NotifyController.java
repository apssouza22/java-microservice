package com.apssouza.mailservice.controllers;

import com.apssouza.eventsourcing.aggregates.EmailState;
import com.apssouza.eventsourcing.commands.EmailCommandHandler;
import com.apssouza.eventsourcing.commands.EmailCreateCommand;
import com.apssouza.eventsourcing.entities.Email;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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

    @GetMapping("email")
    public @ResponseBody Future<String> sendEmail() {
        Supplier supplier = () -> {
            String uuid = UUID.randomUUID().toString();
            EmailCreateCommand command = new EmailCreateCommand(
                    uuid,
                    new Email("Alexsandro", "apssouza22@gmail.com", EmailState.CREATED)
            );
            Executors.newCachedThreadPool().submit(() -> {
                try {
                    emailCommandHandler.create(command);
                } catch (Exception ex) {
                    ReflectionUtils.rethrowRuntimeException(ex);
                }
            });
            return "Email sent successfully.  - code= " + uuid;
        };

        return CompletableFuture.supplyAsync(supplier, Executors.newCachedThreadPool());

    }

}
