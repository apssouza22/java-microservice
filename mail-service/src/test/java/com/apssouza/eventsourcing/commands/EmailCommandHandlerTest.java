package com.apssouza.eventsourcing.commands;

import com.apssouza.eventsourcing.aggregates.EmailAggregate;
import com.apssouza.eventsourcing.services.EventSourcingService;
import com.apssouza.infra.EventPublisher;
import java.util.UUID;
import org.junit.Test;
import org.mockito.Mockito;

public class EmailCommandHandlerTest {

    private EventSourcingService eventSourcingService;

    private EventPublisher eventPublisher;

    private EmailCommandHandler commandHandler;

    @Test
    public void create() {
        eventSourcingService = Mockito.mock(EventSourcingService.class);
        eventPublisher = Mockito.mock(EventPublisher.class);

        String uuid = UUID.randomUUID().toString();
        EmailCreateCommand command = new EmailCreateCommand(uuid, "Alexsandro", "apssouza22@gmail.com");
        commandHandler = new EmailCommandHandler(eventSourcingService, eventPublisher);
        commandHandler.create(command);
    }

    @Test
    public void send(EmailSendCommand command) throws Exception {
    }

    @Test
    public void delivery(EmailDeliveryCommand command) throws Exception {
    }

    @Test
    public void delete(EmailDeleteCommand command) throws Exception {
    }

    @Test
    public void getByUUID(String uuid) {
        EmailAggregate.from(uuid, eventSourcingService.getRelatedEvents(uuid));
    }
}
