package com.apssouza.mailservice.integration.reminder;

import com.apssouza.infra.AbstractDomainEvent;

/**
 * To Do created event
 *
 * @author apssouza
 */
public class TodoCreatedEvent extends AbstractDomainEvent {

    private ToDoDto todo;

    public TodoCreatedEvent(ToDoDto todo) {
        this.todo = todo;
    }

    public TodoCreatedEvent() {
    }

    public ToDoDto getTodo() {
        return todo;
    }

    public void setTodo(ToDoDto todo) {
        this.todo = todo;
    }

    public int getPriority() {
        return this.todo.getPriority();
    }

    @Override
    public String toString() {
        return "TodoChangeEvent{" + "todo=" + todo.getDescription() + '}';
    }

}
