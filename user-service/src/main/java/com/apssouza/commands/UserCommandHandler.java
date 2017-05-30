package com.apssouza.commands;

import com.apssouza.aggregates.UserAggregate;
import com.apssouza.eventsource.EventSourcingService;
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
    EventSourcingService eventSourcingService;

    @Autowired
    AccountService accountService;

    @Autowired
    ApplicationEventPublisher eventPublisher;

    public void create(UserUpdateCreateCommand command) {
        UUID randomUUID = UUID.randomUUID();
        UserAggregate userAgg = getByUUID(randomUUID);
        userAgg.create(command);
        eventSourcingService.save(userAgg);
    }

    public UserAggregate getByUUID(UUID uuid) {
        return UserAggregate.from(uuid, eventSourcingService.getRelatedEvents(uuid));
    }
}
