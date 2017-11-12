package com.apssouza.eventsourcing.commands;

import com.apssouza.eventsourcing.aggregates.Aggregate;
import com.apssouza.eventsourcing.aggregates.EmailAggregate;
import com.apssouza.eventsourcing.aggregates.EmailState;
import com.apssouza.eventsourcing.entities.Email;
import com.apssouza.eventsourcing.eventstore.EventStream;
import com.apssouza.eventsourcing.services.EventSourcingService;
import com.apssouza.infra.EventPublisher;
import java.time.Instant;
import java.util.UUID;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.verify;

public class EmailCommandHandlerTest {

    private EventSourcingService eventSourcingService;

    private EventPublisher eventPublisher;

    private EmailCommandHandler commandHandler;

    @Before
    public void setUp() {
        eventSourcingService = Mockito.mock(EventSourcingService.class);
        eventPublisher = Mockito.mock(EventPublisher.class);
        commandHandler = new EmailCommandHandler(eventSourcingService, eventPublisher);
    }

    @Test
    public void create() throws Exception {
        String uuid = UUID.randomUUID().toString();
        EmailCreateCommand command = new EmailCreateCommand(
                uuid,
                new Email("Alexsandro", "apssouza22@gmail.com", EmailState.CREATED)
        );
        commandHandler.create(command);
        verify(eventPublisher, Mockito.times(1)).publish(Mockito.anyObject());
        verify(eventPublisher, Mockito.times(1)).stream(Mockito.anyObject());
    }

    @Test
    public void send() throws Exception {
        String uuid = UUID.randomUUID().toString();
        EmailSendCommand command = new EmailSendCommand(uuid, Instant.now());
        commandHandler.send(command);
        verify(eventSourcingService, Mockito.times(1)).save(Mockito.any(Aggregate.class));
        verify(eventPublisher, Mockito.times(1)).stream(Mockito.anyObject());
    }

    @Test
    public void getByUUID() {
        String uuid = "123";
        Mockito.when(eventSourcingService.getAggregate(Mockito.anyString()))
                .thenReturn(new EventStream());
        EmailAggregate byUUID = commandHandler.getByUUID(uuid);
        Assert.assertTrue(byUUID.getState().getEmail() == null);
    }
}
