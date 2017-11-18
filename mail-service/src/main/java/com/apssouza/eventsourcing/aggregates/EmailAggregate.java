package com.apssouza.eventsourcing.aggregates;

import com.apssouza.eventsourcing.commands.EmailDeleteCommand;
import com.apssouza.eventsourcing.commands.EmailCreateCommand;
import com.apssouza.eventsourcing.commands.EmailDeliveryCommand;
import com.apssouza.eventsourcing.commands.EmailSendCommand;
import com.apssouza.eventsourcing.entities.Email;
import com.apssouza.eventsourcing.events.EmailCreatedEvent;
import com.apssouza.eventsourcing.events.EmailDeliveredEvent;
import com.apssouza.eventsourcing.events.EmailDeletedEvent;
import com.apssouza.eventsourcing.events.EmailEvent;
import com.apssouza.eventsourcing.events.EmailSentEvent;
import com.apssouza.infra.AppEvent;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Email aggregate, it aggregate all events related to Email and get the final
 * state
 *
 * @author apssouza
 */
public class EmailAggregate extends AbstractAggregate implements Aggregate {

    private final Email state;

    public EmailAggregate(String uuid, List<AppEvent> changes) {
        this(uuid, changes, new Email());
    }

    public EmailAggregate(String uuid, List<AppEvent> changes, Email state) {
        this.uuid = uuid;
        this.changes = changes;
        this.state = state;
    }

    public EmailAggregate create(EmailCreateCommand command) throws Exception {
        if (state.getState() != null) {
            throw new Exception("Is not possible to edit a deleted email");
        }
        return applyChange(new EmailCreatedEvent(uuid, command.getEmail()));
    }

    public EmailAggregate send(EmailSendCommand command) throws Exception {
        if (state.getState() == EmailState.DELETED) {
            throw new Exception("Is not possible to edit a deleted email");
        }
        return applyChange(new EmailSentEvent(
                command.getUuid(),
                getState().setState(EmailState.SENT)));
    }

    public EmailAggregate delivery(EmailDeliveryCommand command) throws Exception {
        if (state.getState() == EmailState.DELETED) {
            throw new Exception("Is not possible to edit a deleted email");
        }
        return applyChange(new EmailDeliveredEvent(
                command.getUuid(),
                getState().setState(EmailState.DELIVERED)
        ));
    }

    public EmailAggregate delete(EmailDeleteCommand command) throws Exception {
        if (state.getState() == EmailState.DELETED) {
            throw new Exception("Is not possible to delete a deleted email");
        }
        return applyChange(new EmailDeletedEvent(uuid,
                getState().setState(EmailState.DELETED)
        ));
    }

    private EmailAggregate apply(AppEvent event) {
        if (event instanceof EmailCreatedEvent) {
            return this.apply((EmailCreatedEvent) event);
        }
        if (event instanceof EmailSentEvent) {
            return this.apply((EmailSentEvent) event);
        }
        if (event instanceof EmailDeliveredEvent) {
            return this.apply((EmailDeliveredEvent) event);
        }
        if (event instanceof EmailDeletedEvent) {
            return this.apply((EmailDeletedEvent) event);
        }
        throw new IllegalArgumentException("Cannot handle event " + event.getClass());

    }

    private EmailAggregate apply(EmailCreatedEvent event) {
        return new EmailAggregate(uuid, changes, event.getEmail());
    }

    private EmailAggregate apply(EmailDeletedEvent event) {
        return new EmailAggregate(uuid, changes, event.getEmail());
    }

    private EmailAggregate apply(EmailSentEvent event) {
        return new EmailAggregate(uuid, changes, event.getEmail());
    }

    private EmailAggregate apply(EmailDeliveredEvent event) {
        return new EmailAggregate(uuid, changes, event.getEmail());
    }

    public static EmailAggregate from(String uuid, List<AppEvent> history) {

        EmailAggregate emailAggregate = new EmailAggregate(uuid, new CopyOnWriteArrayList(), new Email());

        return history
                .stream()
                .reduce(emailAggregate,
                        (tx, event) -> tx.applyChange(event, false),
                        (t1, t2) -> {
                            throw new UnsupportedOperationException();
                        }
                );
    }

    private EmailAggregate applyChange(AppEvent event, boolean isNew) {
        final EmailAggregate item = this.apply(event);
        if (isNew) {
            return new EmailAggregate(item.getUuid(), appendChange(event), item.getState());
        } else {
            return item;
        }
    }

    private EmailAggregate applyChange(EmailEvent event) {
        return applyChange(event, true);
    }

    @Override
    public EmailAggregate markChangesAsCommitted() {
        return new EmailAggregate(uuid, new CopyOnWriteArrayList(), state);
    }

    public Email getState() {
        return state;
    }

}
