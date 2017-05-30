package com.apssouza.aggregates;

import com.apssouza.commands.UserDeleteCommand;
import com.apssouza.commands.UserUpdateCreateCommand;
import com.apssouza.entities.Account;
import com.apssouza.events.DomainEvent;
import com.apssouza.events.UserCreatedEvent;
import com.apssouza.events.UserDeletedEvent;
import com.apssouza.events.UserUpdatedEvent;
import com.google.common.collect.ImmutableList;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author apssouza
 */
public class UserAggregate implements Aggregate {

    private final UUID uuid;
    private final ImmutableList<DomainEvent> changes;
    private final UserState state;

    public UserAggregate(UUID uuid, ImmutableList changes) {
        this(uuid, changes, UserState.ACTIVE);
    }

    public UserAggregate(UUID uuid, ImmutableList changes, UserState state) {
        this.uuid = uuid;
        this.changes = changes;
        this.state = state;
    }

    public UserAggregate create(UserUpdateCreateCommand command) {
        Account account = new Account(command.getName(), command.getEmail(), command.getAuthId());
        return applyChange(new UserCreatedEvent(uuid, account));
    }

    public UserAggregate update(UserUpdateCreateCommand command) throws Exception {
        if (state == UserState.DELETED) {
            throw new Exception("Is not possible to edit a deleted user");
        }
        return applyChange(new UserUpdatedEvent(uuid, command));
    }

    public UserAggregate delete(UserDeleteCommand command) throws Exception {
        if (state == UserState.DELETED) {
            throw new Exception("Is not possible to delete a deleted user");
        }
        return applyChange(new UserDeletedEvent(uuid, command));
    }

    private UserAggregate apply(UserCreatedEvent event) {
        return new UserAggregate(uuid, changes, UserState.ACTIVE);
    }

    private UserAggregate apply(UserDeletedEvent event) {
        return new UserAggregate(uuid, changes, UserState.DELETED);
    }

    public static UserAggregate from(UUID uuid, List<DomainEvent> history) {
        return history
                .stream()
                .reduce(
                        new UserAggregate(uuid, ImmutableList.of(), UserState.ACTIVE),
                        (tx, event) -> tx.applyChange(event, false),
                        (t1, t2) -> {
                            throw new UnsupportedOperationException();
                        }
                );
    }

    private UserAggregate applyChange(DomainEvent event, boolean isNew) {
        final UserAggregate item = this.apply(event);
        if (isNew) {
            return new UserAggregate(item.getUuid(), appendChange(item, event), item.getState());
        } else {
            return item;
        }
    }

    private ImmutableList<DomainEvent> appendChange(UserAggregate item, DomainEvent event) {
        return ImmutableList
                .<DomainEvent>builder()
                .addAll(item.getUncommittedChanges())
                .add(event)
                .build();
    }

    private UserAggregate apply(DomainEvent event) {
        if (event instanceof UserCreatedEvent) {
            return this.apply((UserCreatedEvent) event);
        } else if (event instanceof UserDeletedEvent) {
            return this.apply((UserDeletedEvent) event);
        } else {
            throw new IllegalArgumentException("Cannot handle event " + event.getClass());
        }
    }

    private UserAggregate applyChange(DomainEvent event) {
        return applyChange(event, true);
    }

    public ImmutableList<DomainEvent> getUncommittedChanges() {
        return changes;
    }

    public UserAggregate markChangesAsCommitted() {
        return new UserAggregate(uuid, ImmutableList.of(), state);
    }

    public UUID getUuid() {
        return uuid;
    }

    public UserState getState() {
        return state;
    }

}

enum UserState {
    ACTIVE, INATIVE, DELETED
}
