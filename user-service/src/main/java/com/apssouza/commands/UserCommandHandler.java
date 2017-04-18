package com.apssouza.commands;

import com.apssouza.aggregates.UserAggregate;
import com.apssouza.entities.Account;
import com.apssouza.events.UserCreatedEvent;
import com.apssouza.eventsource.eventstore.EventSourcedRepository;
import com.apssouza.services.AccountService;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

/**
 *
 * @author apssouza
 */
@Component
public class UserCommandHandler {
 
    @Autowired
    EventSourcedRepository sourcedRepository;
    
    @Autowired
    AccountService accountService;
    
    @Autowired
    ApplicationEventPublisher eventPublisher;
    
    public void create(UserUpdateCreateCommand command) {
        Account account = new Account(command.getName(), command.getEmail(), command.getAuthId());
        UUID randomUUID = UUID.randomUUID();
        accountService.save(account);
        UserAggregate userAgg = getByUUID(randomUUID);
        userAgg.createUser(new UserCreatedEvent(randomUUID, command));
        sourcedRepository.save(userAgg);
    }

    public UserAggregate getByUUID(UUID uuid) {
        return new UserAggregate(uuid, sourcedRepository.getRelatedEvents(uuid));
    }


}
