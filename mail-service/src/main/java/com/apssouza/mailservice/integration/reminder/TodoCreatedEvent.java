package com.apssouza.mailservice.integration.reminder;

import com.apssouza.infra.AbstractDomainEvent;

/**
 * To Do created event
 *
 * @author apssouza
 */
public class TodoCreatedEvent extends AbstractDomainEvent {

    private ToDo todo;

    public TodoCreatedEvent(ToDo todo) {
        this.todo = todo;
    }

    public TodoCreatedEvent() {
    }

    public ToDo getTodo() {
        return todo;
    }

    public void setTodo(ToDo todo) {
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
