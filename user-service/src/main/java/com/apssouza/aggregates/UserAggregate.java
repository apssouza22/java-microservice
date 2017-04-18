package com.apssouza.aggregates;

import com.apssouza.events.DomainEvent;
import com.apssouza.events.UserCreatedEvent;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * @author apssouza
 */
public class UserAggregate implements Aggregate{

    private final UUID uuid;
    private final List<DomainEvent> changes;

    public UserAggregate(UUID uuid, List<DomainEvent> changes) {
        this.uuid = uuid;
        this.changes = changes;
    }

    public UserAggregate createUser(UserCreatedEvent event) {
        return new UserAggregate(this.uuid, appendChange(this.changes, event));
    }

    public UserAggregate updateUser(UserCreatedEvent event) {
        return new UserAggregate(this.uuid, appendChange(this.changes, event));
    }

    private List<DomainEvent> appendChange(
            List<DomainEvent> changes,
            DomainEvent event
    ) {
        CopyOnWriteArrayList<DomainEvent> listChanges = new CopyOnWriteArrayList(changes);
        listChanges.add(event);
        return listChanges;
    }

    public UUID getUuid() {
        return uuid;
    }

    public List<DomainEvent> getChanges() {
        return new CopyOnWriteArrayList(changes);
    }

}
