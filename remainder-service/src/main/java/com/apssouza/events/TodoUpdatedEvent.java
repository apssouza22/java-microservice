package com.apssouza.events;

import com.apssouza.annotations.ChangeEvent;
import com.apssouza.entities.ToDo;
import com.apssouza.infra.AbstractDomainEvent;

/**
 * To Do updated event
 *
 * @author apssouza
 */
@ChangeEvent(ChangeEvent.Type.UPDATE)
public class TodoUpdatedEvent extends AbstractDomainEvent implements TodoChangedEvent {

    private ToDo todo;

    public TodoUpdatedEvent(ToDo todo) {
        this.todo = todo;
    }

    public TodoUpdatedEvent() {
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
