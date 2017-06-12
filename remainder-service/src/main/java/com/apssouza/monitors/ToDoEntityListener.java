package com.apssouza.monitors;

import com.apssouza.entities.ToDo;
import com.apssouza.events.TodoCreatedEvent;
import com.apssouza.events.TodoUpdatedEvent;
import com.apssouza.helpers.AutowireHelper;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

/**
 * ToDo's entity listener
 *
 * @author apssouza
 */
public class ToDoEntityListener {

    @Autowired
    private ApplicationEventPublisher publisher;

    @PostPersist
    public void onPersist(ToDo todo) {
        //ToDoPersistenceMonitor is not a spring managed been, so we need to inject 
        //  publisher using this simple helper
        AutowireHelper.autowire(this, this.publisher);
        this.publisher.publishEvent(new TodoCreatedEvent(todo));
    }

    @PostUpdate
    public void onUpdate(ToDo todo) {
        AutowireHelper.autowire(this, this.publisher);
        this.publisher.publishEvent(new TodoUpdatedEvent(todo));
    }

}
