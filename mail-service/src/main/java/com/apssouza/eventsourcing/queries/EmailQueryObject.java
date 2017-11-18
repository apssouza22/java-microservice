package com.apssouza.eventsourcing.queries;

import com.apssouza.eventsourcing.entities.Email;
import com.apssouza.eventsourcing.events.EmailCreatedEvent;
import com.apssouza.eventsourcing.events.EmailDeletedEvent;
import com.apssouza.mailservice.repository.EmailRepository;
import java.util.List;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * Email query service, read data from the read data source This can be
 * separated in a new service
 *
 * @author apssouza
 */
@Component
public class EmailQueryObject {

    private final EmailRepository emailRepository;

    public EmailQueryObject(EmailRepository emailRepository) {
        this.emailRepository = emailRepository;
    }

    public List<Email> all() {
        return emailRepository.findAll();
    }

    @EventListener
    public void updateEmail(EmailCreatedEvent event) {
        System.out.println("event= "+ event.getEmail());
        emailRepository.save(event.getEmail());
    }

    @EventListener
    public void updateEmail(EmailDeletedEvent event) {
        emailRepository.delete(event.getEmail());
    }

}
