package com.apssouza.eventsourcing.aggregates;

import com.apssouza.eventsourcing.commands.EmailCreateCommand;
import com.apssouza.eventsourcing.commands.EmailDeleteCommand;
import com.apssouza.eventsourcing.commands.EmailSendCommand;
import com.apssouza.eventsourcing.entities.Email;
import com.apssouza.infra.AppEvent;
import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author apssouza
 */
public class EmailAggregateTest {
    
    
    @Before
    public void setUp() {
    }

    /**
     * Test of create method, of class EmailAggregate.
     */
    @Test
    public void testCreate() throws Exception {
        final Email email = new Email("alex", "alex@test.com", EmailState.CREATED);
        String uuid = UUID.randomUUID().toString();
        EmailAggregate result = getAggregateWithStateCreated(email,uuid);
        
        assertEquals(email.getEmail(), result.getState().getEmail());
        assertEquals(email.getName(), result.getState().getName());
        AppEvent event = result.getUncommittedChanges().get(0);
        assertEquals(uuid, result.getUuid());
        assertEquals(uuid, event.uuid());
        assertEquals(EmailState.CREATED, result.getState().getState());
    }

    /**
     * Test of send method, of class EmailAggregate.
     */
    @Test
    public void testSend() throws Exception {
        final Email email = new Email("alex", "alex@test.com", EmailState.CREATED);
        String uuid = UUID.randomUUID().toString();
        EmailAggregate aggregateWithStateCreated = getAggregateWithStateCreated(email, uuid);
        EmailSendCommand command = new EmailSendCommand(uuid, Instant.MIN);
        EmailAggregate result = aggregateWithStateCreated.send(command);
        
        assertEquals(EmailState.SENT, result.getState().getState());
    }
    
    
    /**
     * Test of from method, of class EmailAggregate.
     */
    @Test
    public void testFromWithEmptyChanges() {
        System.out.println("from");
        String uuid = "";
        List<AppEvent> history = Collections.emptyList();
        EmailAggregate expResult = new EmailAggregate(uuid, history);
        EmailAggregate result = EmailAggregate.from(uuid, history);
        assertEquals(expResult.getState().toString(), result.getState().toString());
        assertEquals(expResult.getUncommittedChanges(), result.getUncommittedChanges());
        assertEquals(expResult.getUuid(), result.getUuid());
    }
    
    @Test
    public void testFromWithChanges() throws Exception {
        final Email email = new Email("alex", "alex@test.com", EmailState.CREATED);
        String uuid = UUID.randomUUID().toString();
        EmailAggregate expResult = getAggregateWithStateCreated(email,uuid);
        
        EmailAggregate result = EmailAggregate.from(uuid, expResult.getUncommittedChanges());
        EmailAggregate expResultCommitted = expResult.markChangesAsCommitted();
        assertEquals(expResultCommitted.getState().toString(), result.getState().toString());
        assertEquals(expResultCommitted.getUncommittedChanges(), result.getUncommittedChanges());
        assertEquals(expResultCommitted.getUuid(), result.getUuid());
    }

    /**
     * Test of markChangesAsCommitted method, of class EmailAggregate.
     */
    @Test
    public void testMarkChangesAsCommitted() throws Exception {
        final Email email = new Email("alex", "alex@test.com", EmailState.CREATED);
        String uuid = UUID.randomUUID().toString();
        EmailAggregate instance = getAggregateWithStateCreated(email,uuid);
        EmailAggregate result = instance.markChangesAsCommitted();
        assertFalse(instance.getUncommittedChanges().isEmpty());
        assertTrue(result.getUncommittedChanges().isEmpty());
    }
    
    /**
     * Test of delete method, of class EmailAggregate.
     */
    @Test
    public void testDelete() throws Exception {
        final Email email = new Email("alex", "alex@test.com", EmailState.CREATED);
        String uuid = UUID.randomUUID().toString();
        EmailAggregate aggregateWithStateCreated = getAggregateWithStateCreated(email, uuid);
        EmailDeleteCommand command = new EmailDeleteCommand(uuid);
        EmailAggregate result = aggregateWithStateCreated.delete(command);
        
        assertEquals(EmailState.DELETED, result.getState().getState());
    }
    
    private EmailAggregate getAggregateWithNoEvent(String uuid) {
        return EmailAggregate.from(
                uuid, 
                Collections.emptyList()
        );
    }
    
    private EmailAggregate getAggregateWithStateCreated(Email email, String uuid) throws Exception {
        EmailAggregate instance = getAggregateWithNoEvent(uuid);
        return instance.create(new EmailCreateCommand(
                uuid, 
                email
        ));
    }

}
