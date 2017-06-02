package com.apssouza.eventsourcing.aggregates;

import com.apssouza.eventsourcing.commands.EmailDeleteCommand;
import com.apssouza.eventsourcing.commands.EmailCreateCommand;
import com.apssouza.eventsourcing.commands.EmailDeliveryCommand;
import com.apssouza.eventsourcing.commands.EmailSendCommand;
import com.apssouza.eventsourcing.entities.Email;
import com.apssouza.eventsourcing.events.DomainEvent;
import com.apssouza.eventsourcing.events.EmailCreatedEvent;
import com.apssouza.eventsourcing.events.EmailDeliveredEvent;
import com.apssouza.eventsourcing.events.EmailDeletedEvent;
import com.apssouza.eventsourcing.events.EmailSentEvent;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * @author apssouza
 */
public class EmailAggregate implements Aggregate {

    private final UUID uuid;
    private final List<DomainEvent> changes;
    private final EmailState state;

    public EmailAggregate(UUID uuid, List<DomainEvent> changes) {
        this(uuid, changes, EmailState.CREATED);
    }

    public EmailAggregate(UUID uuid, List<DomainEvent> changes, EmailState state) {
        this.uuid = uuid;
        this.changes = changes;
        this.state = state;
    }

    public EmailAggregate create(EmailCreateCommand command) {
        Email email = new Email(command.getName(), command.getEmail());
        return applyChange(new EmailCreatedEvent(uuid, email));
    }

    public EmailAggregate send(EmailSendCommand command) throws Exception {
        if (state == EmailState.DELETED) {
            throw new Exception("Is not possible to edit a deleted user");
        }
        return applyChange(new EmailSentEvent(command.getUuid()));
    }

    public EmailAggregate delivery(EmailDeliveryCommand command) throws Exception {
         if (state == EmailState.DELETED) {
            throw new Exception("Is not possible to edit a deleted user");
        }
        return applyChange(new EmailDeliveredEvent(command.getUuid()));
    }
    
    
    public EmailAggregate delete(EmailDeleteCommand command) throws Exception {
        if (state == EmailState.DELETED) {
            throw new Exception("Is not possible to delete a deleted user");
        }
        return applyChange(new EmailDeletedEvent(uuid, command));
    }

    private EmailAggregate apply(EmailCreatedEvent event) {
        return new EmailAggregate(uuid, changes, EmailState.CREATED);
    }

    private EmailAggregate apply(EmailDeletedEvent event) {
        return new EmailAggregate(uuid, changes, EmailState.DELETED);
    }
    
    private EmailAggregate apply(EmailSentEvent event) {
        return new EmailAggregate(uuid, changes, EmailState.SENT);
    }
    
    private EmailAggregate apply(EmailDeliveredEvent event) {
        return new EmailAggregate(uuid, changes, EmailState.DELIVERED);
    }

    public static EmailAggregate from(UUID uuid, List<DomainEvent> history) {
        
        EmailAggregate emailAggregate = new EmailAggregate(uuid, new CopyOnWriteArrayList(), EmailState.CREATED);
        
        return history
                .stream()
                .reduce(emailAggregate,
                        (tx, event) -> tx.applyChange(event, false),
                        (t1, t2) -> {
                            throw new UnsupportedOperationException();
                        }
                );
    }

    private EmailAggregate applyChange(DomainEvent event, boolean isNew) {
        final EmailAggregate item = this.apply(event);
        if (isNew) {
            return new EmailAggregate(item.getUuid(), appendChange(item, event), item.getState());
        } else {
            return item;
        }
    }

    private CopyOnWriteArrayList<DomainEvent> appendChange(EmailAggregate item, DomainEvent event) {
        CopyOnWriteArrayList<DomainEvent> listChanges = new CopyOnWriteArrayList(changes);
        listChanges.add(event);
        return listChanges;
    }

    private EmailAggregate apply(DomainEvent event) {
        if (event instanceof EmailCreatedEvent) {
            return this.apply((EmailCreatedEvent) event);
        } else if (event instanceof EmailSentEvent) {
            return this.apply((EmailSentEvent) event);
        } else if (event instanceof EmailDeliveredEvent) {
            return this.apply((EmailDeliveredEvent) event);
        } else if (event instanceof EmailDeletedEvent) {
            return this.apply((EmailDeletedEvent) event);
        } else {
            throw new IllegalArgumentException("Cannot handle event " + event.getClass());
        }
    }

    private EmailAggregate applyChange(DomainEvent event) {
        return applyChange(event, true);
    }

    @Override
    public List<DomainEvent> getUncommittedChanges() {
        return changes;
    }

    @Override
    public EmailAggregate markChangesAsCommitted() {
        return new EmailAggregate(uuid, new CopyOnWriteArrayList(), state);
    }

    @Override
    public UUID getUuid() {
        return uuid;
    }

    @Override
    public EmailState getState() {
        return state;
    }

}

