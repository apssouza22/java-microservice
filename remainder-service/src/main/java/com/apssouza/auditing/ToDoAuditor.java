package com.apssouza.auditing;

import com.apssouza.entities.ToDo;
import com.apssouza.events.TodoCreatedEvent;
import com.apssouza.events.TodoUpdatedEvent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;


public class ToDoAuditor {
   
    @Autowired
    private ApplicationEventPublisher publisher;

    @PostPersist
    public void onPersist(ToDo todo) {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
        //this.publisher.publishEvent(new TodoCreatedEvent(todo));
    }

    @PostUpdate
    public void onUpdate(ToDo todo) {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
        //this.publisher.publishEvent(new TodoUpdatedEvent(todo));
    }

}
